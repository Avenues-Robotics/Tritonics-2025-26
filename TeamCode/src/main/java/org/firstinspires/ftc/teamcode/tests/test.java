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
    public void runOpMode()
    {
        telem = FtcDashboard.getInstance().getTelemetry();

        launcherLeft = hardwareMap.get(DcMotor.class, "BR");
        launcherRight = hardwareMap.get(DcMotor.class, "BL");

        launcherLeft.setDirection(DcMotor.Direction.FORWARD);
        launcherRight.setDirection(DcMotor.Direction.REVERSE);

        launcherLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcherRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        launcherLeft.setPower(1);
        launcherRight.setPower(1);

        dt = new ElapsedTime();

        while(opModeIsActive())
        {
            telem.addData("right", launcherRight.getCurrentPosition());
            telem.addData("left", launcherLeft.getCurrentPosition());
            telem.addData("speed", (launcherLeft.getCurrentPosition() + launcherRight.getCurrentPosition())*(lastTicks)/(2*dt.seconds()));
            telem.update();

            lastTicks = (launcherLeft.getCurrentPosition() + launcherRight.getCurrentPosition())/2;

            dt.reset();

            sleep(1);
        }

    }

}
