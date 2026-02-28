package org.firstinspires.ftc.teamcode.utilities;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

public abstract class TritonicsOpMode extends LinearOpMode {

    public enum Motif{
        UNREAD,
        GPP,
        PGP,
        PPG
    }

    public Motif motif;

    public boolean isRedSide;

    public DriveTrain driveTrain;
    public Intake intake;
    public Launcher launcher;
    public Sensors sensors;

    public Telemetry telem;

    @Override
    public void runOpMode() {
        driveTrain = new DriveTrain(this);
        intake = new Intake(this);
        launcher = new Launcher(this);
        sensors = new Sensors(this);
        telem = FtcDashboard.getInstance().getTelemetry();
        motif = Motif.UNREAD;
        runTritonicsOpMode();
    }

    public abstract void runTritonicsOpMode();

}
