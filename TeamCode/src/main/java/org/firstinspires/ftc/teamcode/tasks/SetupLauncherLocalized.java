package org.firstinspires.ftc.teamcode.tasks;

import static org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight.x;
import static org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight.y;
import static org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight.z;
import static org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight.division;
import static org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight.u;
import static org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight.v;
import static org.firstinspires.ftc.teamcode.tasks.PowerLauncherLimelight.w;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.utilities.RoboState;

public class SetupLauncherLocalized extends Task{

    Launcher launcher;
    RoboState roboState;

    double goalX;
    double goalY;

    double d;

    PowerLauncher powerLauncher;

    public SetupLauncherLocalized(Launcher launcher, RoboState roboState){
        this.launcher = launcher;
        this.roboState = roboState;
        powerLauncher = new PowerLauncher(launcher, 1, launcher.telem);
    }

    public boolean run() {
        d = Math.sqrt(Math.pow(roboState.x-goalX, 2) + Math.pow(roboState.y-goalY, 2));
        powerLauncher.setSpeed((u * d * d + v * d + w)/division);
        powerLauncher.run();
        launcher.RA.setPosition(x * d * d + y * d + z);
        launcher.DEC.setPosition();
        return false;
    }

    public Task reset() {
        return new SetupLauncherLocalized(launcher, roboState);
    }
}
