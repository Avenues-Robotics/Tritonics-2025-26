package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

@Autonomous(name = "Tritonics Autonomous")
public class AutoOpMode extends LinearOpMode {

    DriveTrain driveTrain;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, true);

        waitForStart();

        driveTrain.drive(1, 1000, 0);
    }
}
