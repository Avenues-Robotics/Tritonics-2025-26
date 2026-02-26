package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
public class OrientPowerLauncherLocalization extends Task {

    Launcher launcher;
    Localization localization;
    TritonicsOpMode opMode;

    RoboState robotPose;
    double velocity;
    double hoodAngle;
    double turrAngle;

    double goalX;
    double goalY;

    public static double value = -105;

    public OrientPowerLauncherLocalization(Launcher launcher, Localization localization, TritonicsOpMode opMode) {
        this.launcher = launcher;
        this.localization = localization;
        this.opMode = opMode;
        if(opMode.isRedSide) {
            goalX = -152;
            goalY = 152;
        } else {
            goalX = -152;
            goalY = -152;
        }
    }

    @Override
    public boolean run() {
        velocity = 2.64*distance() + 1013;
        launcher.R.setVelocity(velocity);
        launcher.L.setVelocity(velocity);
        hoodAngle = -0.0000126984*Math.pow(distance(),2)+0.00890476*distance()-0.516                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ;
        opMode.telemetry.addData("RA Angle", hoodAngle);
        launcher.RA.setPosition(hoodAngle);
        turrAngle = modulo((Math.toDegrees(Math.atan2(goalY-robotPose.y, goalX-robotPose.x))-robotPose.theta + value), 360)/315;
        launcher.DEC1.setPosition(turrAngle);
        launcher.DEC2.setPosition(turrAngle);
        opMode.telemetry.addData("d", distance());
        return false;
    }

    @Override
    public Task reset() {
        return new OrientPowerLauncherLocalization(launcher, localization, opMode);
    }

    private double distance() {
        robotPose = localization.getRoboState();
        return Math.sqrt(Math.pow(goalX - robotPose.x, 2) + Math.pow(goalY - robotPose.y, 2));
    }

    private double modulo(double dividend, int divisor){
        dividend %= divisor;
        if(dividend<0){dividend += divisor;}
        return dividend;
    }

}
