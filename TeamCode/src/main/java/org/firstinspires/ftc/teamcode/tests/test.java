package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "Test")
public class test extends LinearOpMode {

    DcMotor launcherLeft;
    DcMotor launcherRight;

    int lastTicks;

    ElapsedTime dt;

    Telemetry telem;

    @Override
    public void runOpMode() {

        launcherLeft = hardwareMap.get(DcMotor.class, "launcherL");
        launcherRight = hardwareMap.get(DcMotor.class, "launcherR");

        launcherLeft.setDirection(DcMotor.Direction.FORWARD);
        launcherRight.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while(opModeIsActive()){
            launcherLeft.setPower(1);
            launcherRight.setPower(1);
        }

    }
}
