package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.tasks.Launch;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceThree;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.PIDDrive;
import org.firstinspires.ftc.teamcode.tasks.ParallelRaceTask;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class RedDemonAuto extends TritonicsOpMode {

    Task orientPowerLauncher;

    Task startToShoot;
    Task loadOne;
    Task launchOne;
    Task timerOne;
    Task shootToFirst;
    Task firstPickup;
    Task firstToGate;
    Task gateWaitOne;
    Task firstToShoot;
    Task loadTwo;
    Task launchTwo;
    Task timerTwo;
    Task shootToSecond;
    Task secondPickup;
    Task gatePrepOne;
    Task secondToGate;
    Task gateWaitTwo;
    Task secondToShoot;
    Task loadThree;
    Task launchThree;
    Task timerThree;
    Task shootToThird;
    Task thirdPickup;
    Task gatePrepTwo;
    Task thirdToGate;
    Task gateWaitThree;
    Task thirdToShoot;
    Task loadFour;
    Task launchFour;
    Task timerFour;
    Task park;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = true;

        localization = new Localization(sensors, new RoboState(-108.501,136.941,90,0,0,0), this);
        orientPowerLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);

        startToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20, this);
        loadOne = new LoadSequenceThree(intake, launcher);
        launchOne = new Launch(intake, launcher);
        timerOne = new Timer(1000);
        shootToFirst = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -84.710, 116.023, AngleUnit.DEGREES, 90), 5, 5, this);
        firstPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -32.710, 116.023, AngleUnit.DEGREES, 90), 5, 5, this);
        firstToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -12.457, 138.902, AngleUnit.DEGREES, 90), 5, 10, this);
        gateWaitOne = new Timer(1000);
        firstToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, 77.846, AngleUnit.DEGREES, 90), 10, 20, this);
        loadTwo = new LoadSequenceThree(intake, launcher);
        launchTwo = new Launch(intake, launcher);
        timerTwo = new Timer(1000);
        shootToSecond = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -20.640, 120.023, AngleUnit.DEGREES, 90), 5, 5, this);
        secondPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 19.360, 120.023, AngleUnit.DEGREES, 90), 5, 5, this);
        gatePrepOne = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -32.710, 116.023, AngleUnit.DEGREES, 90), 5, 5, this);
        secondToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -12.457, 138.902, AngleUnit.DEGREES, 90), 5, 10, this);
        secondToShoot =  new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, 77.846, AngleUnit.DEGREES, 90), 10, 20, this);
        loadThree = new LoadSequenceThree(intake, launcher);
        launchThree = new Launch(intake, launcher);
        shootToThird = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 41.433, 120.023, AngleUnit.DEGREES, 90), 5, 5, this);
        thirdPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 81.433, 120.023, AngleUnit.DEGREES, 90), 5, 5, this);
        gatePrepTwo = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -32.710, 116.023, AngleUnit.DEGREES, 90), 5, 5, this);
        thirdToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -12.457, 138.902, AngleUnit.DEGREES, 90), 5, 10, this);
        gateWaitThree = new Timer(1000);
        thirdToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, 77.846, AngleUnit.DEGREES, 90), 10, 20, this);
        loadFour = new LoadSequenceThree(intake, launcher);
        launchFour = new Launch(intake, launcher);
        timerFour = new Timer(1000);
        park = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -54.710, 118.023, AngleUnit.DEGREES, 90), 5, 3, this);

        auto = new ParallelTask(new Task[]{localization, orientPowerLauncher, new SeriesTask(new Task[]{
                new ParallelTask(startToShoot, loadOne),
                launchOne,
                timerOne,
                shootToFirst,
                firstPickup,
                new ParallelTask(loadTwo, new SeriesTask(new ParallelRaceTask(firstToGate, gateWaitOne), firstToShoot)),
                launchTwo,
                timerTwo,
                shootToSecond,
                secondPickup,
                new ParallelTask(loadThree, new SeriesTask(new Task[]{gatePrepOne, new ParallelRaceTask(secondToGate, gateWaitTwo), secondToShoot})),
                launchThree,
                timerThree,
                shootToThird,
                thirdPickup,
                new ParallelTask(loadFour, new SeriesTask(new Task[]{gatePrepTwo, new ParallelRaceTask(thirdToGate, gateWaitThree), thirdToShoot})),
                launchFour,
                timerFour,
                park
        })});

    }

}
