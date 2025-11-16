package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    public DcMotor motor;

    public static boolean motorForward = true;

    public Servo thwacker;

    public Intake(LinearOpMode opMode){
        motor = opMode.hardwareMap.get(DcMotor.class, "intakeMotor");

        thwacker = opMode.hardwareMap.get(Servo.class, "thwacker");

        if(motorForward){motor.setDirection(DcMotor.Direction.REVERSE);}

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
