package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.Rotate;

@Autonomous
public class testThree extends LinearOpMode {

    DriveTrain driveTrain;
    Sensors sensors;
    Drive drive;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        sensors = new Sensors(this);
        drive = new Drive(driveTrain, sensors, 1, 100, 45);

        waitForStart();

        while(opModeIsActive()) {
            drive.run();
        }

    }
}
