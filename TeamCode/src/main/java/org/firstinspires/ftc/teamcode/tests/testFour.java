package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

@Autonomous
public class testFour extends LinearOpMode {
    DriveTrain driveTrain;

    @Override
    public void runOpMode() {
        driveTrain = new DriveTrain(this);

        waitForStart();

        driveTrain.FR.setPower(1);

        sleep(10000);
    }
}
