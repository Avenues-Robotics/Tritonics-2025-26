package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.PosPID;

@Config
public class OrientLauncherLimelight extends Task{

    Sensors sensors;
    Launcher launcher;

    PosPID pid;

    public static double pCoeff = 0.022;
    public static double iCoeff = 0.0032;
    public static double dCoeff = -0.06;

    public static double limelightAngle = 21.152967;
    public static double height = 35; //cm

    public static double a = 0.00333;

    public static double x = -0.000014916;
    public static double y = 0.0115268;
    public static double z = -0.736144;

    public static double u = 0.000377973;
    public static double v = -0.0676735;
    public static double w = 3.98874;

    double d;

    LLResult result;

    PowerLauncher powerLauncher;

    public OrientLauncherLimelight(Sensors sensors, Launcher launcher) {
        this.sensors = sensors;
        this.launcher = launcher;
        pid = new PosPID(pCoeff, iCoeff, dCoeff, () -> sensors.limelight.getLatestResult().getTx(), sensors.telem);
        powerLauncher = new PowerLauncher(launcher, 1.1, sensors.telem);
    }

    public boolean run() {
        result = sensors.limelight.getLatestResult();
        sensors.telem.addData("d", d);
        sensors.telem.update();
        if(result.isValid() && result != null) {
            launcher.DEC.setPower(pid.findPower(0));
            d = height / Math.sin(Math.toRadians(sensors.limelight.getLatestResult().getTy() + limelightAngle));
            launcher.RA.setPosition(x * d * d + y * d + z);
            powerLauncher.setSpeed(u * d * d + v * d + w);
            powerLauncher.run();
            launcher.ramp.setPower(1);
        } else {
            launcher.DEC.setPower(0);
            launcher.R.setPower(0); launcher.L.setPower(0);
            pid.resetIntegral();
        }
        return false;
    }

    public Task reset() {
        return new OrientLauncherLimelight(sensors, launcher);
    }

}
