package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;

@Config
@Autonomous
public class testEleven extends LinearOpMode {

    Sensors sensors;
    Intake intake;
    Launcher launcher;

    Task powerIntake;
    Task orientLauncherLimelight;
    Task powerLauncherLimelight;
    Task timer;
    Task launchSequence;

    Task test;

    @Override
    public void runOpMode() {

        sensors = new Sensors(this, true);
        intake = new Intake(this);
        launcher = new Launcher(this);

        powerIntake = new PowerIntake(intake, 0.5);
        orientLauncherLimelight = new OrientLauncherLimelight(sensors, launcher);
        powerLauncherLimelight = new PowerLauncherLimelight(sensors, launcher);
        timer = new Timer(3000);
        launchSequence = new LaunchSequence(intake, launcher);

        test =
                new SeriesTask(powerIntake,
                    new ParallelTask(orientLauncherLimelight,
                    new ParallelTask(powerLauncherLimelight,
                    new SeriesTask(timer, launchSequence))));

        waitForStart();

        intake.motor.setPower(1);

        while(opModeIsActive()) {
           test.run();
        }

    }

}
