package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous(name = "Test")
public class test extends TritonicsOpMode {

    DcMotor launcherLeft;
    DcMotor launcherRight;

    int lastTicks;

    ElapsedTime dt;

    Telemetry telem;

    @Override
    public void runTritonicsOpMode() {

        waitForStart();

        while(opModeIsActive()){
            intake.motor.setPower(1);
        }

    }
}
