package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous
public class testThree extends TritonicsOpMode {

    Drive drive;

    @Override
    public void runTritonicsOpMode() {

        drive = new Drive(driveTrain, sensors, 1, 100, 45);

        waitForStart();

        while(opModeIsActive()) {
            drive.run();
        }

    }
}
