package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.RotateServo;
import org.firstinspires.ftc.teamcode.tasks.Task;


@Config
@Autonomous
public class testFive extends LinearOpMode {

    Intake intake;
    Task rotateServo;
    public static double servotest = .7;

    @Override
    public void runOpMode() {
        intake = new Intake(this);
        rotateServo = new RotateServo(servotest, intake.right);

        waitForStart();

        while(opModeIsActive()) {
            rotateServo.run();
        }
    }

}
