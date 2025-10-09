package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Robot Move FAST!!!")
public class MilindLearnsToCode extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotor FR = hardwareMap.get(DcMotor.class, "FR");
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotor BR = hardwareMap.get(DcMotor.class, "BR");
        BR.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FL.setPower(-1);
        FR.setPower(1);
        BL.setPower(1);
        BR.setPower(-1);

        sleep(1000);

    }

}