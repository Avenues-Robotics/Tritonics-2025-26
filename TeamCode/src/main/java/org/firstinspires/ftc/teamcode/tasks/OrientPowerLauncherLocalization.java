package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

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

    public OrientPowerLauncherLocalization(Launcher launcher, Localization localization, TritonicsOpMode opMode) {
        this.launcher = launcher;
        this.localization = localization;
        this.opMode = opMode;
        if(opMode.globalVariables.isRedSide) {
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
        hoodAngle = Math.log10(0.0304062*distance()+1.67857)*2.16635+1.19756;
        launcher.RA.setPosition(hoodAngle);
        turrAngle = ((Math.toDegrees(Math.atan((goalY-robotPose.y)/(goalX-robotPose.x))) + 58) % 360)/315;
        launcher.DEC1.setPosition(turrAngle);
        launcher.DEC2.setPosition(turrAngle);
        return false;
    }

    @Override
    public Task reset() {
        return new OrientPowerLauncherLocalization(launcher, localization, opMode);
    }

    private double distance() {
        robotPose = localization.getRoboState();
        return Math.sqrt(Math.pow(robotPose.x, 2) + Math.pow(robotPose.y, 2));
    }



}
