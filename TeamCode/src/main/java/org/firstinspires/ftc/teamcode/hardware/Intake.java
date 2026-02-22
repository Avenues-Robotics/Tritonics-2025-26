package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    public DcMotor motor;

    public Servo right;
    public Servo left;
    public Servo middle;

    public static boolean motorForward = true;

    public static double leftPushing = 0.367;
    public static double leftBlocking = 0.246;
    public static double leftOpening = 0.16;

    public static double rightPushing = 0.3;
    public static double rightBlocking = 0.4;
    public static double rightOpening = 0.5;

    public static double middleUp = 0.5;
    public static double middleDown = 0.564;

    public Intake(LinearOpMode opMode){
        motor = opMode.hardwareMap.get(DcMotor.class, "intakeMotor");

        right = opMode.hardwareMap.get(Servo.class, "intakeRight");
        left = opMode.hardwareMap.get(Servo.class, "intakeLeft");
        middle = opMode.hardwareMap.get(Servo.class, "intakeMiddle");

        if(motorForward){motor.setDirection(DcMotor.Direction.REVERSE);}

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
