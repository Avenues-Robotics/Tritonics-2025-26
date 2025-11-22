package org.firstinspires.ftc.teamcode.opModes;

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

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        sensors = new Sensors(this);
        launcher = new Launcher(this);
        intake = new Intake(this);

        drive = new Drive(driveTrain, sensors, 1, 50, 0);
        powerLauncher = new PowerLauncher(launcher, 1);
        powerIntake = new PowerIntake(intake, 1);
        powerTransfer = new PowerTransfer(launcher, 1);

        timer = new Timer(1000);

        auto = new SeriesTask(drive, new SeriesTask(powerLauncher, new SeriesTask(powerIntake, new SeriesTask(new Task(), powerTransfer))));

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }
    }

}
