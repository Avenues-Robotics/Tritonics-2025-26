package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.PIDDrive;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testThirteen extends TritonicsOpMode {

    Localization localization;
    PIDDrive drive;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        localization = new Localization(sensors, new RoboState(0,0,0,0,0,0), this);
        drive = new PIDDrive(driveTrain, localization, new Pose2D(
                DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0
        ), 4, 2);

        auto = new ParallelTask(localization, drive);

        telem.addData("x", 0);
        telem.addData("theta", 0);
        telem.update();

        waitForStart();

        while(opModeIsActive()){
            auto.run();
            telem.update();
        }

    }

}
