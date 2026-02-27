package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.tasks.Launch;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceOne;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.PIDDrive;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class RedCloseAuto12 extends TritonicsOpMode {

    public static long wait = 1000;

    Localization localization;

    Task orientPowerLauncher;

    Task startToShoot;
    Task loadOne;
    Task launchOne;
    Task timerOne;
    Task shootToFirst;
    Task firstToGate;
    Task loadTwo;
    Task gateToShoot;
    Task launchTwo;
    Task timerTwo;
    Task shootToSecond;
    Task secondToShoot;
    Task loadThree;
    Task launchThree;
    Task timerThree;
    Task shootToThird;
    Task thirdToShoot;
    Task loadFour;
    Task launchFour;
    Task timerFour;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = true;

        localization = new Localization(sensors, new RoboState(-108.169,137.044,90,0,0,0), this);

        orientPowerLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);

        startToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        loadOne = new LoadSequenceOne(intake, launcher);
        launchOne = new Launch(intake, launcher);
        timerOne = new Timer(wait);
        shootToFirst = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -51.6, 119.028, AngleUnit.DEGREES, 90), 5, 5);
        firstToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 2.302, 136.028, AngleUnit.DEGREES, 90), 5, 10);
        loadTwo = new LoadSequenceOne(intake, launcher);
        gateToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        launchTwo = new Launch(intake, launcher);
        timerTwo = new Timer(wait);
        shootToSecond = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 18.910, 117.735, AngleUnit.DEGREES, 90), 5, 5);
        secondToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        loadThree = new LoadSequenceOne(intake, launcher);
        launchThree = new Launch(intake, launcher);
        timerThree = new Timer(wait);
        shootToThird = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 76.806, 115.897, AngleUnit.DEGREES, 90), 5, 5);
        thirdToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        loadFour = new LoadSequenceOne(intake, launcher);
        launchFour = new Launch(intake, launcher);
        timerFour = new Timer(wait);

        auto = new ParallelTask(new Task[]{localization, orientPowerLauncher, new SeriesTask(new Task[]{
                new ParallelTask(startToShoot, loadOne),
                launchOne,
                timerOne,
                shootToFirst,
                new ParallelTask(new SeriesTask(firstToGate, gateToShoot), loadTwo),
                launchTwo,
                timerTwo,
                shootToSecond,
                new ParallelTask(secondToShoot, loadThree),
                launchThree,
                timerThree,
                shootToThird,
                new ParallelTask(thirdToShoot, loadFour),
                launchFour,
                timerFour
        })});

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }

    }

}
