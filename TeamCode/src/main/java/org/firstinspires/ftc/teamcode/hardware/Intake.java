package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    public DcMotor motor;

    public Servo right;
    public Servo left;

    public static boolean motorForward = true;

    public Intake(LinearOpMode opMode){
        motor = opMode.hardwareMap.get(DcMotor.class, "intakeMotor");

        right = opMode.hardwareMap.get(Servo.class, "intakeRight");
        left = opMode.hardwareMap.get(Servo.class, "intakeLeft");

        if(motorForward){motor.setDirection(DcMotor.Direction.REVERSE);}

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
