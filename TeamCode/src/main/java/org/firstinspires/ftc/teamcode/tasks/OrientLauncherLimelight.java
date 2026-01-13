package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.PosPID;

@Config
public class OrientLauncherLimelight extends Task{

    Sensors sensors;
    Launcher launcher;

    PosPID pid;

    public static double pCoeff = 0.02;
    public static double iCoeff = 0.00000000001;
    public static double dCoeff = 0.008;

    public static double a = 0.00333;

    public OrientLauncherLimelight(Sensors sensors, Launcher launcher) {
        this.sensors = sensors;
        this.launcher = launcher;
        pid = new PosPID(pCoeff, iCoeff, dCoeff, () -> sensors.limelight.getLatestResult().getTx(), sensors.telem);
    }

    public boolean run() {
        if(Math.abs(sensors.limelight.getLatestResult().getTx()) < 2){launcher.DEC.setPower(0);return false;}
        launcher.DEC.setPower(pid.findPower(0));
        return false;
    }

    public Task reset() {
        return new OrientLauncherLimelight(sensors, launcher);
    }

}
