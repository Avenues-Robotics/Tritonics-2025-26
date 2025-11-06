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

        while (opModeIsActive()) {
            driveTrain.driveTeleop(gamepad1);
        }
    }
}
