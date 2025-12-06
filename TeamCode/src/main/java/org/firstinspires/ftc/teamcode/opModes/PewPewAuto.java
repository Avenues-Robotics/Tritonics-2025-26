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
import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncher;
import org.firstinspires.ftc.teamcode.tasks.PowerTransfer;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;

@Config
@Autonomous()
public class PewPewAuto extends LinearOpMode {

    DriveTrain driveTrain;
    Sensors sensors;
    Launcher launcher;
    Intake intake;

    Drive drive;
    PowerLauncher powerLauncher;
    PowerIntake powerIntake;
    PowerTransfer powerTransfer;

    Timer timer;

    Task auto;

    public static double speed = 1.65;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        sensors = new Sensors(this);
        launcher = new Launcher(this);
        intake = new Intake(this);

        drive = new Drive(driveTrain, sensors, 0, 0, 0);
        powerLauncher = new PowerLauncher(launcher, speed, FtcDashboard.getInstance().getTelemetry());

        timer = new Timer(1000);

        auto = new SeriesTask(drive, powerLauncher);

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }
    }

}
