package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.ParallelRaceTask;
import org.firstinspires.ftc.teamcode.tasks.Rotate;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;

@Config
@Autonomous(name = "Tritonics Autonomous")
public class AutoOpMode extends LinearOpMode {

    DriveTrain driveTrain;
    Sensors sensors;
    Launcher launcher;
    Intake intake;
    LaunchSequence CloseLaunch;

    // This looks like a good opportunity to define a class like
    // DriveSegment(speed, distance, angle)
    // and then define these inside of an array like
    // DriveSegment[] segments
    // Then when you run this you can just loop through the
    // array using for (DriveSegment s : segments){}
    // This has the advantage of being able to add new segments
    // in one place with one line of code, rather than adding 4 lines up here
    // and another line inside runOpMode()
    // -Mr. Carpenter

    public static double ASpeed = 0.5;
    public static double ADist = 60;
    public static double ADeg = 90;
    Drive ADrive;

    public static double ARotateDeg = -10;
    public static double ARotateSpeed = .3;
    Rotate ARotate;

    public static double BSpeed = 0.5;
    public static double BDist = 100;
    public static double BDeg = -10;
    Drive BDrive;

    public static double CSpeed = 0.5;
    public static double CDist = 80;
    public static double CDeg = 180;
    Drive CDrive;

    public static double BRotateDeg = -10;
    public static double BRotateSpeed = .3;
    Rotate BRotate;

    public static double DSpeed = 0;
    public static double DDist = 0;
    public static double DDeg = 0;
    Drive DDrive;

    public static double ESpeed = 0;
    public static double EDist = 0;
    public static double EDeg = 0;
    Drive EDrive;

    public static double FSpeed = 0;
    public static double FDist = 0;
    public static double FDeg = 0;
    Drive FDrive;

    public static double GSpeed = 0;
    public static double GDist = 0;
    public static double GDeg = 0;
    Drive GDrive;

    public static double HSpeed = 0;
    public static double HDist = 0;
    public static double HDeg = 0;
    Drive HDrive;

    public static double ISpeed = 0;
    public static double IDist = 0;
    public static double IDeg = 0;
    Drive IDrive;

    Task auto;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, true);
        sensors = new Sensors(this);
        intake = new Intake(this);
        launcher = new Launcher(this);

        ADrive = new Drive(driveTrain, sensors, ASpeed, ADist, ADeg);
        ARotate = new Rotate(driveTrain, sensors, ARotateSpeed, ARotateDeg);
        BDrive = new Drive(driveTrain, sensors, BSpeed, BDist, BDeg, 600);
        CDrive = new Drive(driveTrain, sensors, CSpeed, CDist, CDeg);
        BRotate = new Rotate(driveTrain, sensors, BRotateSpeed, BRotateDeg);
        DDrive = new Drive(driveTrain, sensors, DSpeed, DDist, DDeg);
        EDrive = new Drive(driveTrain, sensors, ESpeed, EDist, EDeg);
        FDrive = new Drive(driveTrain, sensors, FSpeed, FDist, FDeg);
        GDrive = new Drive(driveTrain, sensors, GSpeed, GDist, GDeg);
        HDrive = new Drive(driveTrain, sensors, HSpeed, HDist, HDeg);
        IDrive = new Drive(driveTrain, sensors, ISpeed, IDist, IDeg);
        CloseLaunch = new LaunchSequence(intake, launcher, 1.1, telemetry);

        // This will be fun to figure out how to impelement using a list
        // Think recursive function...
        // - Mr. Carpenter
        auto = new SeriesTask(
                new SeriesTask(new SeriesTask(new SeriesTask(new SeriesTask(ADrive, new SeriesTask(ARotate, CloseLaunch)), BDrive), new SeriesTask(CDrive,
                BRotate)), DDrive), new SeriesTask(new SeriesTask(new SeriesTask(EDrive, FDrive),
                new SeriesTask(GDrive, HDrive)), IDrive)
        );

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }
    }
}
