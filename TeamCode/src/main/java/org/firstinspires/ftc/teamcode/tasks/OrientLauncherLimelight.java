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

    public static double pCoeff = 0.02;
    public static double iCoeff = 0;
    public static double dCoeff = -0.005;

    public static double limelightAngle = 21.152967;
    public static double height = 35; //cm

    public static double x = -0.0000361952;
    public static double y = 0.0158997;
    public static double z = -0.938325;

    public static double dashboardVariable = 0.000000005;

    double d;

    LLResult result;

    public OrientLauncherLimelight(Sensors sensors, Launcher launcher) {
        this.sensors = sensors;
        this.launcher = launcher;
        pid = new PosPID(pCoeff, iCoeff, dCoeff, () -> sensors.limelight.getLatestResult().getTx(), sensors.telem);
    }

    public boolean run() {
        result = sensors.limelight.getLatestResult();
        sensors.telem.addData("d", d);
        sensors.telem.update();
        if(result.isValid() && result != null) {
            if(sensors.redSide) {
                launcher.DEC.setPower(pid.findPower(dashboardVariable));
                sensors.telem.addLine("Red");
            } else {
                launcher.DEC.setPower(pid.findPower(-dashboardVariable));
                sensors.telem.addLine("Blue");
            }
            d = height / Math.sin(Math.toRadians(sensors.limelight.getLatestResult().getTy() + limelightAngle));
            launcher.RA.setPosition(x * d * d + y * d + z);
        } else {
            launcher.DEC.setPower(0);
            pid.resetIntegral();
        }
        return false;
    }

    public boolean end() {
        launcher.DEC.setPower(0);
        return true;
    }

    public Task reset() {
        return new OrientLauncherLimelight(sensors, launcher);
    }

}
