package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task is never finished because it always tranforms stick inputs into motor movements
 * as a teleop controller.
 */

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
public class DriveTeleop extends Task{

    public static int smoothing = 2;

    DriveTrain driveTrain;
    TritonicsOpMode opMode;

    double actualMax;
    double desiredMax;

    RoboState roboState;

    public DriveTeleop(DriveTrain driveTrain, TritonicsOpMode opMode) {
        this.driveTrain = driveTrain;
        this.opMode = opMode;
    }

    @Override
    public boolean run()
    {

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        double fr = (-opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/2) * Math.abs(Math.pow(-opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/2, smoothing - 1));
        double fl = (-opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/2) * Math.abs(Math.pow(-opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/2, smoothing - 1));
        double br = (-opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/2) * Math.abs(Math.pow(-opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/2, smoothing - 1));
        double bl = (-opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/2) * Math.abs(Math.pow(-opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/2, smoothing - 1));

        // Normalize the values so no wheel power exceeds 100%
        actualMax = Math.max(Math.max(Math.max(Math.abs(fl), Math.abs(fr)), Math.abs(bl)), Math.abs(br));

        roboState = opMode.localization.getRoboState();
        desiredMax = 0.0657104*Math.sqrt(Math.pow(roboState.x, 2) + Math.pow(roboState.y, 2))+0.434783;
        if(desiredMax > 1) {desiredMax = 1;}

        if (actualMax > 1) {
            fl /= actualMax;
            fr /= actualMax;
            bl /= actualMax;
            br /= actualMax;
        }

        driveTrain.telem.addData("fr", fr);
        driveTrain.telem.addData("bl", bl);
        driveTrain.telem.update();

        driveTrain.FL.setPower(fl);
        driveTrain.FR.setPower(fr);
        driveTrain.BL.setPower(bl);
        driveTrain.BR.setPower(br);

        return false;
    }

    @Override
    public Task reset(){
        return new DriveTeleop(driveTrain, opMode);
    }

}
