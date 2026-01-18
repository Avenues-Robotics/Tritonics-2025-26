package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncher;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.PowerTransfer;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.TeleopTask;

@TeleOp
public class TeleopOpModeBlue extends LinearOpMode {

    DriveTrain driveTrain;
    Launcher launcher;
    Sensors sensors;
    Intake intake;

    Task drive;
    Task powerIntake;
    Task reverse;
    Task FIRE;
    Task charge;
    Task orientLauncher;

    Task teleop;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, false);
        launcher = new Launcher(this);
        sensors = new Sensors(this, false);
        intake = new Intake(this);

        drive = new DriveTeleop(driveTrain, this);
        powerIntake = new TeleopTask(new PowerIntake(intake, 1), () -> gamepad1.left_bumper, true);
        reverse = new TeleopTask(new PowerIntake(intake,-1), () -> gamepad1.left_trigger > 0.7, true);
        FIRE = new TeleopTask(new LaunchSequence(intake, launcher), () -> gamepad1.right_trigger > 0.7, true);
        charge = new TeleopTask(new PowerLauncherLimelight(sensors, launcher), () -> gamepad1.right_bumper, true);
        orientLauncher = new OrientLauncherLimelight(sensors, launcher);

        teleop = new ParallelTask(orientLauncher,
                new ParallelTask(charge,
                        new ParallelTask(FIRE,
                                new ParallelTask(reverse,
                                        new ParallelTask(powerIntake,
                                                drive)))));

        waitForStart();

        while (opModeIsActive()) {
            teleop.run();
        }
    }
}
