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
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class BlueCloseAuto12 extends TritonicsOpMode {

    public static long wait = 0;

    Localization localization;

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

        isRedSide = false;

        localization = new Localization(sensors, new RoboState(-111.148,-141.285,90,0,0,0), this);

        orientPowerLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);

        startToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -96.323, -42.197, AngleUnit.DEGREES, 90), 10, 20);
        loadOne = new FindLoadSequence(this, Motif.GPP);
        launchOne = new Launch(intake, launcher);
        timerOne = new Timer(wait);
        shootToFirst = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -79.412, -125.148, AngleUnit.DEGREES, 90), 5, 5);
        firstPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -54.412, -125.148, AngleUnit.DEGREES, 90), 5, 5);
        firstToGate = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -11.133, -141.002, AngleUnit.DEGREES, 90), 5, 10);
        gateWait = new Timer(3000);
        loadTwo = new FindLoadSequence(this, Motif.PPG);
        gateToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, -77.846, AngleUnit.DEGREES, 90), 10, 20);
        launchTwo = new Launch(intake, launcher);
        timerTwo = new Timer(wait);
        shootToSecond = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -30.088, -126.597, AngleUnit.DEGREES, 90), 5, 5);
        secondPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 4.912, -126.597, AngleUnit.DEGREES, 90), 5, 5);
        secondToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, -77.846, AngleUnit.DEGREES, 90), 10, 20);
        loadThree = new FindLoadSequence(this, Motif.PGP);
        launchThree = new Launch(intake, launcher);
        timerThree = new Timer(wait);
        shootToThird = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 21.948, -127.206, AngleUnit.DEGREES, 90), 5, 5);
        thirdPickup = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, 66.948, -127.206, AngleUnit.DEGREES, 90), 5, 5);
        thirdToShoot = new PIDDrive(driveTrain, localization, new Pose2D(DistanceUnit.CM, -74.277, -77.846, AngleUnit.DEGREES, 90), 5, 5);
        loadFour = new FindLoadSequence(this, Motif.GPP);
        launchFour = new Launch(intake, launcher);
        timerFour = new Timer(wait);

        auto = new ParallelTask(new Task[]{localization, orientPowerLauncher, new SeriesTask(new Task[]{
                new ParallelTask(startToShoot, loadOne),
                launchOne,
                timerOne,
                shootToFirst,
                firstPickup,
                new ParallelTask(new SeriesTask(new Task[]{firstToGate, gateWait, gateToShoot}), loadTwo),
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
                timerFour, // TODO: add the parking for the blue auto
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
