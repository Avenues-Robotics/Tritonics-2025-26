package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;

@TeleOp
public class BasicTeleopGreat extends LinearOpMode {

    DriveTrain driveTrain;
    DriveTeleop driveTeleop;

    public static double speed = 0.01;

    DcMotor BL;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, false);
        driveTeleop = new DriveTeleop(driveTrain, this);

        BL = hardwareMap.get(DcMotor.class, "BL");

        waitForStart();

        while(opModeIsActive()) {
            driveTeleop.run();
        }

    }

}
