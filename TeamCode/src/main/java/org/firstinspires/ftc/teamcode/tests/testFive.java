package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.RotateServo;
import org.firstinspires.ftc.teamcode.tasks.Task;

@Autonomous
public class testFive extends LinearOpMode {

    Intake intake;
    Task rotateServo;

    @Override
    public void runOpMode() {
        intake = new Intake(this);
        rotateServo = new RotateServo(1, intake.right);

        waitForStart();

        while(opModeIsActive()) {
            rotateServo.run();
        }
    }

}
