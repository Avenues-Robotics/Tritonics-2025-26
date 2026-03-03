package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.Launch;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequenceThree;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceOne;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceThree;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.PIDDrive;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Config
@Autonomous
public class RedFarAuto extends TritonicsOpMode{

    Task orientLauncher;

    Task startTimer;
    Task loadOne;
    Task launchOne;
    Task timerOne;
    Task driveThereOne;
    Task driveBackOne;
    Task loadTwo;
    Task launchTwo;
    Task timerTwo;
    Task driveThereTwo;
    Task driveBackTwo;
    Task loadThree;
    Task launchThree;
    Task timerThree;
    Task driveThereThree;
    Task driveBackThree;
    Task loadFour;
    Task launchFour;
    Task timerFour;
    Task driveThereFour;
    Task driveBackFour;
    Task loadFive;
    Task launchFive;
    Task timerFive;
    Task driveThereFive;
    Task driveBackFive;
    Task loadSix;
    Task launchSix;
    Task park;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = true;

        localization = new Localization(sensors, new RoboState(159.989, 64.611, 180, 0, 0, 0), this);

        orientLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);

        startTimer = new Timer(2000);
        loadOne = new LaunchSequenceThree(intake, launcher);
        launchOne = new Launch(intake, launcher);
        timerOne = new Timer(1000);
        driveThereOne = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 158.816, 142.783, AngleUnit.DEGREES, 180), 20, 10);
        driveBackOne = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 159.989, 34.611, AngleUnit.DEGREES, 180), 10, 10);
        loadTwo = new LaunchSequenceThree(intake, launcher);
        launchTwo = new Launch(intake, launcher);
        timerTwo = new Timer(1000);
        driveThereTwo = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 158.816, 142.783, AngleUnit.DEGREES, 180), 20, 10);
        driveBackTwo = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 159.989, 34.611, AngleUnit.DEGREES, 180), 10, 10);
        loadThree = new LaunchSequenceThree(intake, launcher);
        launchThree = new Launch(intake, launcher);
        timerThree = new Timer(1000);
        driveThereThree = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 158.816, 142.783, AngleUnit.DEGREES, 180), 20, 10);
        driveBackThree = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 159.989, 34.611, AngleUnit.DEGREES, 180), 10, 10);
        loadFour = new LaunchSequenceThree(intake, launcher);
        launchFour = new Launch(intake, launcher);
        timerFour = new Timer(1000);
        driveThereFour = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 158.816, 142.783, AngleUnit.DEGREES, 180), 20, 10);
        driveBackFour = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 159.989, 34.611, AngleUnit.DEGREES, 180), 10, 10);
        loadFive = new LaunchSequenceThree(intake, launcher);
        launchFive = new Launch(intake, launcher);
        timerFive = new Timer(1000);
        driveThereFive = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 158.816, 142.783, AngleUnit.DEGREES, 180), 20, 10);
        driveBackFive = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 159.989, 34.611, AngleUnit.DEGREES, 180), 10, 10);
        loadSix = new LaunchSequenceThree(intake, launcher);
        launchSix = new Launch(intake, launcher);
        park = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 157.424, 77.624, AngleUnit.DEGREES, 180), 5, 5);

        auto = new ParallelTask(new Task[] {localization, orientLauncher, new SeriesTask(new Task[]{
                startTimer,
                loadOne,
                timerOne,
                driveThereOne,
                driveBackOne,
                loadTwo,
                timerTwo,
                driveThereTwo,
                driveBackTwo,
                loadThree,
                timerThree,
                driveThereThree,
                driveBackThree,
                loadFour,
                timerFour,
                driveThereFour,
                driveBackFour,
                loadFive,
                timerFive,
                driveThereFive,
                driveBackFive,
                loadSix,
                park
        })});

        intake.left.setPosition(Intake.leftBlocking);
        intake.middle.setPosition(Intake.middleUp);
        intake.right.setPosition(Intake.rightBlocking);

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }

        saveState();

    }

}
