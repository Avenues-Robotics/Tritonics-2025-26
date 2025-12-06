package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
@Config
public class testFour extends LinearOpMode {

    public static String name;

    public static double pos;

    Servo servo;

    @Override
    public void runOpMode() {
        servo = hardwareMap.get(Servo.class, name);

        waitForStart();

        while(opModeIsActive()) {
            servo.setPosition(pos);
        }

    }
}
