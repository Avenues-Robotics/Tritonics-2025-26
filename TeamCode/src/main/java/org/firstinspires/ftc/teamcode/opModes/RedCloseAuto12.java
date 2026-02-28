package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.FindLoadSequence;
import org.firstinspires.ftc.teamcode.tasks.Launch;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceOne;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.PIDDrive;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.ReadMotif;
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
    Task readMotif;
    Task orientPowerLauncher;

    Task startToShoot;
    Task loadOne;
    Task launchOne;
    Task timerOne;
    Task shootToFirst;
    Task firstPickup;
    Task firstToGate;
    Task gateWait;
    Task loadTwo;
    Task gateToShoot;
    Task launchTwo;
    Task timerTwo;
    Task shootToSecond;
    Task secondPickup;
    Task secondToShoot;
    Task loadThree;
    Task launchThree;
    Task timerThree;
    Task shootToThird;
    Task thirdPickup;
    Task thirdToShoot;
    Task loadFour;
    Task launchFour;
    Task timerFour;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = true;

        localization = new Localization(sensors, new RoboState(-108.169,137.044,90,0,0,0), this);
        readMotif = new ReadMotif(this);
        orientPowerLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);

        startToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        loadOne = new FindLoadSequence(this, Motif.PGP);
        launchOne = new Launch(intake, launcher);
        timerOne = new Timer(wait);
        shootToFirst = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -71.6, 119.028, AngleUnit.DEGREES, 90), 5, 5);
        firstPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -51.6, 119.028, AngleUnit.DEGREES, 90), 5, 5);
        firstToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -20, 136.028, AngleUnit.DEGREES, 90), 5, 10);
        gateWait = new Timer(wait);
        loadTwo = new FindLoadSequence(this, Motif.PPG);
        gateToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        launchTwo = new Launch(intake, launcher);
        timerTwo = new Timer(wait);
        shootToSecond = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -1.09, 117.735, AngleUnit.DEGREES, 90), 5, 5);
        secondPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 18.910, 117.735, AngleUnit.DEGREES, 90), 5, 5);
        secondToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 10, 20);
        loadThree = new FindLoadSequence(this, Motif.PGP);
        launchThree = new Launch(intake, launcher);
        timerThree = new Timer(wait);
        shootToThird = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 56.806, 115.897, AngleUnit.DEGREES, 90), 5, 5);
        thirdPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 76.806, 115.897, AngleUnit.DEGREES, 90), 5, 5);
        thirdToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, 42.197, AngleUnit.DEGREES, 90), 5, 3);
        loadFour = new FindLoadSequence(this, Motif.GPP);
        launchFour = new Launch(intake, launcher);
        timerFour = new Timer(wait);

        auto = new ParallelTask(new Task[]{localization, orientPowerLauncher, readMotif, new SeriesTask(new Task[]{
                new ParallelTask(startToShoot, loadOne),
                launchOne,
                timerOne,
                shootToFirst,
                firstPickup,
                new ParallelTask(new SeriesTask(new Task[]{firstToGate, gateToShoot, gateWait}), loadTwo),
                launchTwo,
                timerTwo,
                shootToSecond,
                secondPickup,
                new ParallelTask(secondToShoot, loadThree),
                launchThree,
                timerThree,
                shootToThird,
                thirdPickup,
                new ParallelTask(thirdToShoot, loadFour),
                launchFour,
                timerFour
        })});

        intake.left.setPosition(Intake.leftBlocking);
        intake.middle.setPosition(Intake.middleUp);
        intake.right.setPosition(Intake.rightBlocking);

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }

    }

}
