package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Rotate;
import org.firstinspires.ftc.teamcode.tasks.Task;

@Autonomous()
public class TestFour extends LinearOpMode {

    Task rotate;
    Task drive;

    DriveTrain driveTrain;
    Sensors sensors;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);

        waitForStart();

        driveTrain.FL.setPower(1);
        driveTrain.FR.setPower(1);
        driveTrain.BL.setPower(1);
        driveTrain.BR.setPower(1);

        sleep(1000);

        driveTrain.FL.setPower(0);
        driveTrain.FR.setPower(0);
        driveTrain.BL.setPower(0);
        driveTrain.BR.setPower(0);

    }

}
