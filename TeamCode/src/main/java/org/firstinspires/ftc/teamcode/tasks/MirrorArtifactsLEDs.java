package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.teamcode.utilities.Prism;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
public class MirrorArtifactsLEDs extends Task{

    public static float greenScale = 1.5f;

    public static int brightness = 40;

    enum Colors {
        GREEN,
        PURPLE,
        EMPTY
    }

    TritonicsOpMode opMode;
    Prism prism;
    ColorSensor rightSensor;
    ColorSensor centerSensor;
    ColorSensor leftSensor;

    Colors right;
    Colors center;
    Colors left;

    public MirrorArtifactsLEDs(TritonicsOpMode opMode) {
        this.opMode = opMode;

        prism = opMode.sensors.prism;
        rightSensor = opMode.sensors.bottomRightCS;
        centerSensor = opMode.sensors.bottomCenterCS;
        leftSensor = opMode.sensors.bottomLeftCS;

        right = Colors.EMPTY;
        center = Colors.EMPTY;
        left = Colors.EMPTY;
    }

    @Override
    public boolean run() {

        opMode.telem.addData("right red", rightSensor.red());
        opMode.telem.addData("right green", rightSensor.green());
        opMode.telem.addData("right blue", rightSensor.blue());

        opMode.telem.addData("isn't artifact", rightSensor.red() + rightSensor.green() + rightSensor.blue() < 400);

        if (rightSensor.red() + rightSensor.green() + rightSensor.blue() < 400) {
            if(right != Colors.EMPTY) {
                prism.setSegment(0, 0, 7, 0, 0, 0, 0);
            }
            right = Colors.EMPTY;
        } else if (greenScale * rightSensor.green() > rightSensor.red() + rightSensor.blue()) {
            if(right != Colors.GREEN) {
                prism.setSegment(0, 0, 7, 0, 255, 0, brightness);
                right = Colors.GREEN;
            }
        } else {
            if(right != Colors.PURPLE) {
                prism.setSegment(0, 0, 7, 255, 0, 255, brightness);
                right = Colors.PURPLE;
            }
        }

        if (centerSensor.red() + centerSensor.green() + centerSensor.blue() < 400) {
            if(center != Colors.EMPTY) {
                prism.setSegment(1, 8, 15, 0, 0, 0, 0);
            }
            center = Colors.EMPTY;
        } else if (greenScale * centerSensor.green() > centerSensor.red() + centerSensor.blue()) {
            if(center != Colors.GREEN) {
                prism.setSegment(1, 8, 15, 0, 255, 0, brightness);
                center = Colors.GREEN;
            }
        } else {
            if(center != Colors.PURPLE) {
                prism.setSegment(1, 8, 15, 255, 0, 255, brightness);
                center = Colors.PURPLE;
            }
        }

        if (leftSensor.red() + leftSensor.green() + leftSensor.blue() < 400) {
            if(left != Colors.EMPTY) {
                prism.setSegment(2, 16, 233, 0, 0, 0, 0);
            }
            left = Colors.EMPTY;
        } else if (greenScale * leftSensor.green() > leftSensor.red() + leftSensor.blue()) {
            if(left != Colors.GREEN) {
                prism.setSegment(2, 16, 233, 0, 255, 0, brightness);
                left = Colors.GREEN;
            }
        } else {
            if(left != Colors.PURPLE) {
                prism.setSegment(2, 16, 233, 255, 0, 255, brightness);
                left = Colors.PURPLE;
            }
        }

        return false;
    }

    @Override
    public Task reset() {
        return new MirrorArtifactsLEDs(opMode);
    }

}
