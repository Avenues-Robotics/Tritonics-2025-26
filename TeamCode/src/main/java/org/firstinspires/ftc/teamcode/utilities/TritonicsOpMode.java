package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

public abstract class TritonicsOpMode extends LinearOpMode {

    public GlobalVariables globalVariables;

    public DriveTrain driveTrain;
    public Intake intake;
    public Launcher launcher;
    public Sensors sensors;

    @Override
    public void runOpMode() {
        globalVariables = new GlobalVariables();
        driveTrain = new DriveTrain(this);
        intake = new Intake(this);
        launcher = new Launcher(this);
        sensors = new Sensors(this);
        runTritonicsOpMode();
    }

    public abstract void runTritonicsOpMode();

}
