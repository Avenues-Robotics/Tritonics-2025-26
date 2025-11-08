package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;

@Config
@Autonomous(name = "Tritonics Autonomous")
public class AutoOpMode extends LinearOpMode {

    DriveTrain driveTrain;

    public static double speedOne = 1;
    public static double distOne = 108;
    public static double degOne = 55;
    Drive driveOne;

    public static double speedTwo = 1;
    public static double distTwo = 60;
    public static double degTwo = 0;
    Drive driveTwo;

    Task auto;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, true);

        driveOne = new Drive(driveTrain, speedOne, distOne, degOne);
        driveTwo = new Drive(driveTrain, speedTwo, distTwo, degTwo);

        auto = new SeriesTask(driveOne, driveTwo);

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }
    }
}
