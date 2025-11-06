package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

@TeleOp(name = "Tritonics Teleop")
public class TeleopOpMode extends LinearOpMode {

    DriveTrain driveTrain;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);

        waitForStart();

<<<<<<< HEAD
        while (opModeIsActive()) {
            driveTrain.driveTeleop(gamepad1);
        }
=======
//        while (opModeIsActive()) {
//            driveTrain.drive();
//        }
>>>>>>> af9bd8b5fb00c0d9267cf01e90c567424a989481
    }
}
