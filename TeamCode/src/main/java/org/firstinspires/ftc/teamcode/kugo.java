package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

@TeleOp(name = "sdoeri")
@Config
public class kugo extends LinearOpMode {

    @Override
    public void runOpMode(){

        DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
        DcMotor FR = hardwareMap.get(DcMotor.class, "FR");
        DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
        DcMotor BR = hardwareMap.get(DcMotor.class, "BR");

        FL.setDirection(Direction.REVERSE);
        FR.setDirection(Direction.FORWARD);
        BL.setDirection(Direction.REVERSE);
        BR.setDirection(Direction.FORWARD);

        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        FL.setPower(gamepad1.left_stick_x);
        FR.setPower(gamepad1.left_stick_y);
        BL.setPower(gamepad1.left_stick_x);
        BR.setPower(gamepad1.left_stick_y);
        sleep(2000);

    }

}
