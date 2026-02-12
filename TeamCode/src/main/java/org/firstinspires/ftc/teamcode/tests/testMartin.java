package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous
public class testMartin extends LinearOpMode {

    public static double transferPower = 1;
    public static double launcherPower = 1;
    public static double intakePower = 1;
    public static double servoPosition = 0.5;

    @Override
    public void runOpMode() {

        DcMotor transfer  = hardwareMap.get(DcMotor.class, "transfer");
        DcMotor launcherR = hardwareMap.get(DcMotor.class, "launcherR");
        DcMotor launcherL = hardwareMap.get(DcMotor.class, "launcherL");
        DcMotor intake = hardwareMap.get(DcMotor.class, "intake");
        Servo servo = hardwareMap.get(Servo.class, "servo");

        transfer.setDirection(DcMotor.Direction.REVERSE);
        launcherR.setDirection(DcMotor.Direction.FORWARD);
        launcherL.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {
            transfer.setPower(transferPower);
            launcherR.setPower(launcherPower);
            launcherL.setPower(launcherPower);
            intake.setPower(intakePower);
            servo.setPosition(servoPosition);
        }

    }

}
