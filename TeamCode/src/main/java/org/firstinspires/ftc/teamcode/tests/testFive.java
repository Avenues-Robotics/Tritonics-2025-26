package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.tasks.RotateServo;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;


@Config
@Autonomous
public class testFive extends TritonicsOpMode {

    Task rotateServo;
    public static double servotest = .7;

    @Override
    public void runTritonicsOpMode() {
        rotateServo = new RotateServo(servotest, launcher.RA);

        waitForStart();

        while(opModeIsActive()) {
            rotateServo.run();
        }
    }

}
