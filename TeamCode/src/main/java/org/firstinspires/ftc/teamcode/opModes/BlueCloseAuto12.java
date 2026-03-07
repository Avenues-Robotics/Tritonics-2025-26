package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.FindLaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.Launch;
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
public class BlueCloseAuto12 extends TritonicsOpMode {

    public static long wait = 0;

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
    Task park;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = false;

        localization = new Localization(sensors, new RoboState(-108.326,-138.633,90,0,0,0), this);

        orientPowerLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);

        startToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, -42.197, AngleUnit.DEGREES, 90), 10, 20, this);
        loadOne = new FindLaunchSequence(this, Motif.GPP);
        launchOne = new Launch(intake, launcher);
        timerOne = new Timer(wait);
        shootToFirst = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -82.522, -124.475, AngleUnit.DEGREES, 90), 5, 5, this);
        firstPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -42.522, -124.475, AngleUnit.DEGREES, 90), 5, 5, this);
        firstToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -17.437, -139.891, AngleUnit.DEGREES, 90), 5, 10, this);
        gateWait = new Timer(1500);
        loadTwo = new FindLaunchSequence(this, Motif.PPG);
        gateToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, -77.846, AngleUnit.DEGREES, 90), 10, 20, this);
        launchTwo = new Launch(intake, launcher);
        timerTwo = new Timer(wait);
        shootToSecond = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -22.640, -124.207, AngleUnit.DEGREES, 90), 5, 5, this);
        secondPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 17.360, -124.207, AngleUnit.DEGREES, 90), 5, 5, this);
        secondToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, -77.846, AngleUnit.DEGREES, 90), 10, 20, this);
        loadThree = new FindLaunchSequence(this, Motif.PGP);
        launchThree = new Launch(intake, launcher);
        timerThree = new Timer(wait);
        shootToThird = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 39.005, -125.891, AngleUnit.DEGREES, 90), 5, 5, this);
        thirdPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 79.005, -125.891, AngleUnit.DEGREES, 90), 5, 5, this);
        thirdToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, -77.846, AngleUnit.DEGREES, 90), 5, 5, this);
        loadFour = new FindLaunchSequence(this, Motif.GPP);
        launchFour = new Launch(intake, launcher);
        timerFour = new Timer(wait);
        park = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -79.412, -125.148, AngleUnit.DEGREES, 90), 5, 5, this);

        auto = new ParallelTask(new Task[]{localization, orientPowerLauncher, new SeriesTask(new Task[]{
                startToShoot,
                loadOne,
                timerOne,
                shootToFirst,
                firstPickup,
                new ParallelRaceTask(firstToGate, new Timer(2000)),
                gateWait,
                gateToShoot,
                loadTwo,
                timerTwo,
                shootToSecond,
                secondPickup,
                secondToShoot,
                loadThree,
                timerThree,
                shootToThird,
                thirdPickup,
                thirdToShoot,
                loadFour,
                timerFour,
                park
        })});

        intake.left.setPosition(Intake.leftBlocking);
        intake.middle.setPosition(Intake.middleUp);
        intake.right.setPosition(Intake.rightBlocking);

        waitForStart();

        intake.motor.setPower(1);

        while(opModeIsActive()) {
            auto.run();
        }

        saveState();

    }

}
