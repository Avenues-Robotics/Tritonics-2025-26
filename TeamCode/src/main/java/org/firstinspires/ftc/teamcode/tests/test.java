package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

@Autonomous(name = "Test")
public class test extends LinearOpMode {

    DcMotor launcherLeft;
    DcMotor launcherRight;

    int lastTicks;

    ElapsedTime dt;

    Telemetry telem;

    Launcher launcher;

    @Override
    public void runOpMode() {

        launcher = new Launcher(this);

        waitForStart();

        while(opModeIsActive()){
            launcher.L.setPower(1);
            launcher.R.setPower(1);
        }

    }
}
