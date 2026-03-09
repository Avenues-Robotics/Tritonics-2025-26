package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.MirrorArtifactsLEDs;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testPrism extends TritonicsOpMode {

    public static long milliseconds = 1000;

    Task mirror;

    @Override
    public void runTritonicsOpMode() {

        mirror = new MirrorArtifactsLEDs(this);

        waitForStart();

//        intake.motor.setPower(1);
        intake.right.setPosition(Intake.rightBlocking);
        intake.middle.setPosition(Intake.middleUp);
        intake.left.setPosition(Intake.leftBlocking);

        while(opModeIsActive()) {
            mirror.run();
            telem.update();
        }

    }

}
