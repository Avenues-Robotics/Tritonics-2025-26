package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.ParallelRaceTask;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.Rotate;
import org.firstinspires.ftc.teamcode.tasks.RotateServo;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;

@Config
@Autonomous
public class AutoOpModeFarBlue extends LinearOpMode {

    DriveTrain driveTrain;
    Sensors sensors;
    Launcher launcher;
    Intake intake;
    Task FarLaunch;
    RotateServo Aim;

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

    public static double Aspeed = 0.5;
    public static double Adist = 160;
    public static double Adir = -1;

    public static double Bspeed = 0.5;
    public static double Bdist = 140;
    public static double Bdir = 10;


    Task ADrive;
    Task BDrive;
    Task AOrientLauncherLimelight;
    Task APowerLauncherLimelight;
    Task ALaunchSequence;
    Task BLaunchSequence;
    Task powerIntake;
    Task BOrientLauncherLimelight;
    Task BPowerLauncherLimelight;
    Task ATimer;
    Task BTimer;
    Task WTimer;

    Task auto;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, true);
        sensors = new Sensors(this, false);
        intake = new Intake(this);
        launcher = new Launcher(this);

        powerIntake = new PowerIntake(intake, 1);
        AOrientLauncherLimelight = new OrientLauncherLimelight(sensors, launcher);
        APowerLauncherLimelight = new PowerLauncherLimelight(sensors, launcher);
        ATimer = new Timer(3000);
        ALaunchSequence = new LaunchSequence(intake, launcher);
        ADrive = new Drive(driveTrain, sensors, Aspeed, Adist, Adir);
        WTimer = new Timer(1000);
        BDrive = new Drive(driveTrain, sensors, Bspeed, Bdist, Bdir);
        BOrientLauncherLimelight = new OrientLauncherLimelight(sensors, launcher);
        BPowerLauncherLimelight = new PowerLauncherLimelight(sensors, launcher);
        BTimer = new Timer(3000);
        BLaunchSequence = new LaunchSequence(intake, launcher);

        auto = new ParallelTask(powerIntake,
                new SeriesTask(new ParallelRaceTask(AOrientLauncherLimelight, new ParallelRaceTask(APowerLauncherLimelight, new SeriesTask(ATimer, ALaunchSequence))),
                        new SeriesTask(ADrive,
                                new SeriesTask(WTimer,
                                        new SeriesTask(BDrive,
                                                new ParallelRaceTask(BOrientLauncherLimelight, new ParallelRaceTask(BPowerLauncherLimelight, new SeriesTask(BTimer, BLaunchSequence))))))));

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }
    }
}
