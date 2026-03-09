package org.firstinspires.ftc.teamcode.utilities;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Localization;

public abstract class TritonicsOpMode extends LinearOpMode {

    public enum Motif{
        UNREAD,
        GPP,
        PGP,
        PPG
    }

    public Motif motif;

    public boolean isRedSide;

    public int offset;

    public DriveTrain driveTrain;
    public Intake intake;
    public Launcher launcher;
    public Sensors sensors;

    public Telemetry telem;

    public Localization localization;

    @Override
    public void runOpMode() {
        driveTrain = new DriveTrain(this, false);
        intake = new Intake(this);
        launcher = new Launcher(this);
        sensors = new Sensors(this);
        telem = FtcDashboard.getInstance().getTelemetry();
        motif = Motif.UNREAD;
        runTritonicsOpMode();
    }

    public abstract void runTritonicsOpMode();

     protected void saveState() {
        DataHandoff.roboState = localization.getRoboState();
        DataHandoff.isRedSide = isRedSide;
        DataHandoff.motif = motif;
        DataHandoff.hasData = true;
    }

    protected void readState() {
         if(DataHandoff.hasData) {
             localization.setOdom(DataHandoff.roboState);
             isRedSide = DataHandoff.isRedSide;
             motif = DataHandoff.motif;
             localization.resetOffsets();
         } else {
             telemetry.addLine("Failed to retrieve handoff data from auto");
             telemetry.addLine("Using defaults");
             telemetry.addLine("Relocalization likely required");
             telemetry.update();
         }
    }

}
