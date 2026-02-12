package org.firstinspires.ftc.teamcode.tests;

import static org.firstinspires.ftc.teamcode.tasks.Localization.odomAngVelAcc;
import static org.firstinspires.ftc.teamcode.tasks.Localization.odomPosAcc;
import static org.firstinspires.ftc.teamcode.tasks.Localization.odomRotAcc;
import static org.firstinspires.ftc.teamcode.tasks.Localization.odomVelAcc;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.RoboState;

@Config
@Autonomous
public class testFivehundredEightySeven extends LinearOpMode {

    public static long delay = 2000;

    @Override
    public void runOpMode() {

        Sensors sensors = new Sensors(this, false);
        ElapsedTime dt = new ElapsedTime();

        Telemetry telemetry = FtcDashboard.getInstance().getTelemetry();

        waitForStart();

        dt.reset();

        while(opModeIsActive()) {
            sensors.odo.update();
            RoboState odomOutput = new RoboState();
            Pose2D pos = sensors.odo.getPosition();
            odomOutput.x = pos.getX(DistanceUnit.CM);
            odomOutput.y = pos.getY(DistanceUnit.CM);
            odomOutput.theta = pos.getHeading(AngleUnit.DEGREES);
            odomOutput.velX = sensors.odo.getVelX(DistanceUnit.CM);
            odomOutput.velY = sensors.odo.getVelY(DistanceUnit.CM);
            odomOutput.velTheta = sensors.odo.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
            odomOutput.sigmaX = odomPosAcc * dt.seconds();
            odomOutput.sigmaY = odomPosAcc * dt.seconds();
            odomOutput.sigmaTheta = odomRotAcc * dt.seconds();
            odomOutput.sigmaVelX = odomVelAcc * dt.seconds();
            odomOutput.sigmaVelY = odomVelAcc * dt.seconds();
            odomOutput.sigmaVelTheta = odomAngVelAcc * dt.seconds();
            //sensors.odo.setPosition(new Pose2D(DistanceUnit.CM, odomOutput.x, odomOutput.y, AngleUnit.DEGREES, odomOutput.theta));
            telemetry.addData("x", odomOutput.x);
            telemetry.addData("y", odomOutput.y);
            telemetry.addData("theta", odomOutput.theta);
            telemetry.update();
            dt.reset();
            //sleep(delay);
        }

    }

}
