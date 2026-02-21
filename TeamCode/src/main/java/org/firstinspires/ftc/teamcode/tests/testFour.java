package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous
@Config
public class testFour extends TritonicsOpMode {

    public static String name = "launcherRA";

    public static double pos;

    Servo servo;

    @Override
    public void runTritonicsOpMode() {
        servo = hardwareMap.get(Servo.class, name);

        waitForStart();

        while(opModeIsActive()) {
            servo.setPosition(pos);
        }

    }
}
