package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Test")
public class test extends LinearOpMode {

    DcMotor launcherLeft;
    DcMotor launcherRight;

    int lastTicks;

    ElapsedTime dt;

    @Override
    public void runOpMode()
    {

        launcherLeft = hardwareMap.get(DcMotor.class, "BR");
        launcherRight = hardwareMap.get(DcMotor.class, "BL");

        launcherLeft.setDirection(DcMotor.Direction.FORWARD);
        launcherRight.setDirection(DcMotor.Direction.REVERSE);

        launcherLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcherRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        launcherLeft.setPower(1);
        launcherRight.setPower(1);

        sleep(100000000);

        dt.reset();

        while(true)
        {
            telemetry.addData("right", launcherRight.getCurrentPosition());
            telemetry.addData("left", launcherLeft.getCurrentPosition());
            telemetry.addData("speed", (launcherLeft.getCurrentPosition() + launcherRight.getCurrentPosition())*(lastTicks)/(2*dt.seconds()));
            telemetry.update();

            lastTicks = (launcherLeft.getCurrentPosition() + launcherRight.getCurrentPosition())/2;

            dt.reset();
        }

    }

}
