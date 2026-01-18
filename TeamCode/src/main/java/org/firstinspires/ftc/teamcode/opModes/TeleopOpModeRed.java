package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
import org.firstinspires.ftc.teamcode.tasks.LaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.PowerIntake;
import org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight;
import org.firstinspires.ftc.teamcode.tasks.PowerTransfer;
import org.firstinspires.ftc.teamcode.tasks.Task;

@TeleOp
public class TeleopOpModeRed extends LinearOpMode {

    DriveTrain driveTrain;
    Launcher launcher;
    Sensors sensors;
    Intake intake;

    Task drive;
    Task powerIntake;
    Task reverseIntake;
    Task FIRE;
    Task charge;
    Task orientLauncher;
    Task stopIntake;
    Task stopTransfer;
    Task reverseTransfer;

    enum MovementState{
        FORWARD,
        NEUTRAL,
        REVERSE
    }
    MovementState intakeState;
    MovementState transferState;
    MovementState launchSequenceState;
    MovementState launcherState;

    Gamepad prevGamepad;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, false);
        launcher = new Launcher(this);
        sensors = new Sensors(this, true);
        intake = new Intake(this);

        drive = new DriveTeleop(driveTrain, this);
        powerIntake = new PowerIntake(intake, 1);
        stopIntake = new PowerIntake(intake, 0.5);
        reverseIntake = new PowerIntake(intake,-1);
        FIRE = new LaunchSequence(intake, launcher);
        charge = new PowerLauncherLimelight(sensors, launcher);
        orientLauncher = new OrientLauncherLimelight(sensors, launcher);
        stopTransfer = new PowerTransfer(launcher, 0);
        reverseTransfer = new PowerTransfer(launcher, -1);

        intakeState = MovementState.NEUTRAL;
        transferState = MovementState.NEUTRAL;
        launchSequenceState = MovementState.NEUTRAL;
        launcherState = MovementState.NEUTRAL;

        prevGamepad = new Gamepad();

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.left_bumper){
                intakeState = MovementState.FORWARD;
                if(launchSequenceState == MovementState.FORWARD) {
                    intake.right.setPosition(0.85);
                    intake.left.setPosition(0.08);
                }
            } else if (!(gamepad1.left_trigger > 0.7)) {
                intakeState = MovementState.NEUTRAL;
                if(launchSequenceState != MovementState.FORWARD) {
                    intake.right.setPosition(0.76);
                    intake.left.setPosition(0);
                }
            } else {
                intakeState = MovementState.REVERSE;
                if(launchSequenceState == MovementState.FORWARD) {
                    intake.right.setPosition(0.76);
                    intake.left.setPosition(0);
                }
            }
            switch(intakeState){
                case FORWARD: powerIntake.run(); break;
                case NEUTRAL: stopIntake.run(); break;
                case REVERSE: reverseIntake.run(); break;
            }

            if(gamepad1.left_trigger > 0.7) {
                transferState = MovementState.REVERSE;
            } else {
                transferState = MovementState.NEUTRAL;
            }
            switch(transferState) {
                case NEUTRAL: stopTransfer.run(); break;
                case REVERSE: reverseTransfer.run(); break;
            }

            if(gamepad1.right_bumper) {
                launcherState = MovementState.FORWARD;
            } else {
                launcherState = MovementState.NEUTRAL;
            }
            switch(launcherState) {
                case FORWARD: charge.run(); break;
                case NEUTRAL: launcher.R.setPower(0); launcher.L.setPower(0); charge = charge.reset(); break;
            }

            if(gamepad1.right_trigger > 0.7) {
                launchSequenceState = MovementState.FORWARD;
            }
            switch(launchSequenceState) {
                case FORWARD:
                    if(FIRE.run()) {
                        launchSequenceState = MovementState.NEUTRAL;
                        FIRE = FIRE.reset();
                    }
            }

            drive.run();
            orientLauncher.run();

            prevGamepad = gamepad1;
        }
    }
}
