//package org.firstinspires.ftc.teamcode.opModes;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
//import org.firstinspires.ftc.teamcode.hardware.Intake;
//import org.firstinspires.ftc.teamcode.hardware.Launcher;
//import org.firstinspires.ftc.teamcode.hardware.Sensors;
//import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
//import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
//import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
//import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
//import org.firstinspires.ftc.teamcode.tasks.PowerLauncher;
//import org.firstinspires.ftc.teamcode.tasks.PowerTransfer;
//import org.firstinspires.ftc.teamcode.tasks.RotateServo;
//import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
//import org.firstinspires.ftc.teamcode.tasks.Task;
//import org.firstinspires.ftc.teamcode.tasks.TeleopTask;
//
//@TeleOp
//public class TeleopOpModeBlue extends LinearOpMode {
//
//    DriveTrain driveTrain;
//    Launcher launcher;
//    Sensors sensors;
//    Intake intake;
//
//    Task powerIntake;
//    Task powerTransfer;
//    Task powerLauncher;
//    Task drive;
//    Task reverseLauncher;
//    Task reverseTransfer;
//    Task reverseIntake;
//    Task launchSequenceClose;
//    Task launchSequenceMiddle;
//    Task launchSequenceFar;
//
//    Task teleop;
//
//    @Override
//    public void runOpMode() {
//
//        driveTrain = new DriveTrain(this);
//        launcher = new Launcher(this);
//        sensors = new Sensors(this, false);
//        intake = new Intake(this);
//        powerIntake = new TeleopTask(new PowerIntake(intake, 1), () -> gamepad1.right_bumper);
//        powerTransfer =  new TeleopTask(new PowerTransfer(launcher, 1), () -> gamepad1.left_bumper);
//        powerLauncher = new TeleopTask(new PowerLauncher(launcher, 1, FtcDashboard.getInstance().getTelemetry()), () -> gamepad1.left_trigger > 0.2);
//        reverseLauncher = new TeleopTask(new PowerLauncher(launcher, -1, FtcDashboard.getInstance().getTelemetry()), () -> gamepad1.a);
//        reverseTransfer = new TeleopTask(new PowerTransfer(launcher, -1), () -> gamepad1.a);
//        reverseIntake = new TeleopTask(new PowerIntake(intake, -1), () -> gamepad1.right_trigger > 0.2);
//
//        // Three launch sequences for different distances
//        launchSequenceClose = new TeleopTask(new SeriesTask(new SeriesTask(new RotateServo(0, launcher.RA), new RotateServo(0.76, launcher.DEC)), new LaunchSequence(intake, launcher,1.1, FtcDashboard.getInstance().getTelemetry())), () -> gamepad1.dpad_up);
//        launchSequenceMiddle = new TeleopTask(new SeriesTask(new SeriesTask(new RotateServo(0.55, launcher.RA), new RotateServo(0.76, launcher.DEC)), new LaunchSequence(intake, launcher, 1.4, FtcDashboard.getInstance().getTelemetry())), () -> gamepad1.dpad_left);
//        launchSequenceFar = new TeleopTask(new SeriesTask(new SeriesTask(new RotateServo(1, launcher.RA), new RotateServo(0.67, launcher.DEC)), new LaunchSequence(intake, launcher, 1.9, FtcDashboard.getInstance().getTelemetry())), () -> gamepad1.dpad_down);
//
//        drive = new DriveTeleop(driveTrain, this);
//
//        teleop = new ParallelTask (
//                new ParallelTask(
//                        new ParallelTask(
//                                new ParallelTask(powerIntake, powerTransfer),
//                                new ParallelTask(powerLauncher, drive)
//                        ),
//                        new ParallelTask(reverseLauncher, reverseTransfer)
//                ),
//                new ParallelTask(
//                        launchSequenceClose,
//                        new ParallelTask(
//                                launchSequenceMiddle,
//                                launchSequenceFar
//                        )
//                )
//        );
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//            teleop.run();
//        }
//    }
//}
