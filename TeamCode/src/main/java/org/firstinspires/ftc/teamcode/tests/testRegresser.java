package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testRegresser extends TritonicsOpMode {

    public static double launcherVel = 2000;
    public static double hoodAngle = 0.5;

    RoboState roboState;

    @Override
    public void runTritonicsOpMode() {

        localization = new Localization(sensors, new RoboState(0, 0, 0, 0, 0, 0), this);

        waitForStart();

        intake.motor.setPower(1);
        launcher.ramp.setPower(1);
        intake.middle.setPosition(Intake.middleDown);

        while(opModeIsActive()) {
            launcher.L.setVelocity(launcherVel);
            launcher.R.setVelocity(launcherVel);
            launcher.RA.setPosition(hoodAngle);
            roboState = localization.getRoboState();
            telemetry.addData("distance", Math.sqrt(Math.pow(-152 - roboState.x, 2) + Math.pow(140 - roboState.y, 2)));
            telemetry.update();
        }

    }

}
