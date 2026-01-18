package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.PosPID;

@Config
public class PowerLauncherLimelight extends Task{

    Sensors sensors;
    Launcher launcher;

    public static double limelightAngle = 21.152967;
    public static double height = 35; //cm

    public static double u = 0.00000673972;
    public static double v = 0.00301059;
    public static double w = 0.802864;

    public static double division = -1;

    double d;

    LLResult result;

    PowerLauncher powerLauncher;

    public PowerLauncherLimelight(Sensors sensors, Launcher launcher) {
        this.sensors = sensors;
        this.launcher = launcher;
        powerLauncher = new PowerLauncher(launcher, 1.1, sensors.telem);
    }

    public boolean run() {
        result = sensors.limelight.getLatestResult();
        if(result.isValid() && result != null) {
            d = height / Math.sin(Math.toRadians(sensors.limelight.getLatestResult().getTy() + limelightAngle));
            powerLauncher.setSpeed((u * d * d + v * d + w)/division);
            powerLauncher.run();
        } else {
            launcher.R.setPower(0); launcher.L.setPower(0);
        }
        return false;
    }

    public boolean end() {
        launcher.R.setPower(0); launcher.L.setPower(0);
        return true;
    }

    public Task reset() {
        return new PowerLauncherLimelight(sensors, launcher);
    }

}
