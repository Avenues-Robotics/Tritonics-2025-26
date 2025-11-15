package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncher;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.TeleopTask;

@TeleOp(name = "Tritonics Teleop")
public class TeleopOpMode extends LinearOpMode {

    DriveTrain driveTrain;

    DriveTeleop driveTeleop;

    PowerLauncher powerLauncher;

    TeleopTask teleopTask;

    Launcher launcher;
    Sensors sensors;

    Task teleop;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        launcher = new Launcher(this);
        sensors = new Sensors(this);

        powerLauncher = new PowerLauncher(launcher, 5000);
        teleopTask = new TeleopTask(powerLauncher, () -> gamepad1.dpad_left, true);

        driveTeleop = new DriveTeleop(driveTrain, this);

        waitForStart();

        while (opModeIsActive()) {
            driveTeleop.run();
            teleopTask.run();
        }
    }
}
