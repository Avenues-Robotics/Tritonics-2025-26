package org.firstinspires.ftc.teamcode.opModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
import org.firstinspires.ftc.teamcode.tasks.Launch;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceOne;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceThree;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.Relocalize;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.TeleopTask;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@TeleOp
@Config
public class RedTeleop extends TritonicsOpMode {

    Localization localization;

    Task driveTeleop;
    Task loadSequence;
    Task launch;
    Task powerLauncher;
    Task relocalize;

    Task teleop;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = true;

        driveTeleop = new DriveTeleop(driveTrain, this);
        loadSequence = new TeleopTask(new LoadSequenceOne(intake, launcher), ()->gamepad1.right_bumper, true);
        launch = new TeleopTask(new Launch(intake, launcher), ()->gamepad1.right_trigger > 0.7, true);
        localization = new Localization(sensors, new RoboState(0,0,0,0,0,0), this);
        powerLauncher = new OrientPowerLauncherLocalization(launcher, localization, this);
        relocalize = new Relocalize(sensors, new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0)); /// Get the right coordinates, these change for blue

        teleop = new ParallelTask(new Task[]{loadSequence, launch, localization, powerLauncher, driveTeleop});

        waitForStart();

        intake.left.setPosition(Intake.leftBlocking);
        intake.right.setPosition(Intake.rightBlocking);
        intake.middle.setPosition(Intake.middleUp);
        intake.motor.setPower(1);

        while(opModeIsActive()){
            teleop.run();
        }

    }

}
