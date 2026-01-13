package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@Config
@Autonomous
public class testTen extends LinearOpMode {

    public static double power = 0.5;
    CRServo servo;

    @Override
    public void runOpMode() {

        servo = hardwareMap.get(CRServo.class, "launcherDEC");

        waitForStart();

        servo.setPower(power);

        sleep(1100000);

    }

}
