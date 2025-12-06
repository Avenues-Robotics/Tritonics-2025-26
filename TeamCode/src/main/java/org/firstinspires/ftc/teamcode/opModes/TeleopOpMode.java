package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncher;
import org.firstinspires.ftc.teamcode.tasks.PowerTransfer;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.TeleopTask;

@TeleOp(name = "Tritonics Teleop")
public class TeleopOpMode extends LinearOpMode {

    DriveTrain driveTrain;
    Launcher launcher;
    Sensors sensors;
    Intake intake;

    Task powerIntake;
    Task powerTransfer;
    Task powerLauncher;
    Task drive;
    Task reverseLauncher;
    Task reverseTransfer;
    Task reverseIntake;
    Task launchSequenceClose;

    Task teleop;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        launcher = new Launcher(this);
        sensors = new Sensors(this);
        intake = new Intake(this);
        powerIntake = new TeleopTask(new PowerIntake(intake, 1), () -> gamepad1.right_bumper, false);
        powerTransfer =  new TeleopTask(new PowerTransfer(launcher, 1), () -> gamepad1.left_bumper, false);
        powerLauncher = new TeleopTask(new PowerLauncher(launcher, 1, FtcDashboard.getInstance().getTelemetry()), () -> gamepad1.left_trigger > 0.2, false);
        reverseLauncher = new TeleopTask(new PowerLauncher(launcher, -1, FtcDashboard.getInstance().getTelemetry()), () -> gamepad1.a, false);
        reverseTransfer = new TeleopTask(new PowerTransfer(launcher, -1), () -> gamepad1.a, false);
        reverseIntake = new TeleopTask(new PowerIntake(intake, -1), () -> gamepad1.right_trigger > 0.2, false);

        // Three launch sequences for different distances
        launchSequenceClose = new TeleopTask(new LaunchSequence(intake, launcher,1, FtcDashboard.getInstance().getTelemetry()), () -> gamepad1.dpad_up, false);

        drive = new DriveTeleop(driveTrain, this);

        teleop = new ParallelTask (
                new ParallelTask(
                        new ParallelTask(
                                new ParallelTask(powerIntake, powerTransfer),
                                new ParallelTask(powerLauncher, drive)
                        ),
                        new ParallelTask(reverseLauncher, reverseTransfer)
                ),
                launchSequenceClose
        );

        waitForStart();

        while (opModeIsActive()) {
            teleop.run();
        }
    }
}
