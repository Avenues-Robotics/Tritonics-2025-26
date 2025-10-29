package org.firstinspires.ftc.teamcode.hardware;

/*
 * Manages launcher hardware including motors and servos
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class launcher {

    //The two motors driving the launcher
    DcMotor R;
    DcMotor L;

    //Direction of each launcher motor, dashboard configurable
    public static boolean rForward = false;
    public static boolean lForward = true;

    //The servos angling the launcher
    Servo RA;
    Servo DEC;

    //Launcher constructor, initiates launcher hardware
    public launcher(LinearOpMode opMode) {
        //Grabs motors
        R = opMode.hardwareMap.get(DcMotor.class, "launcherR");
        L = opMode.hardwareMap.get(DcMotor.class, "launcherL");

        //Grabs servo
        RA = opMode.hardwareMap.get(Servo.class, "launcherRA");
        DEC = opMode.hardwareMap.get(Servo.class, "launcherDEC");

        //Sets the direction for each motor depending on the setting
        if(rForward){R.setDirection(DcMotor.Direction.FORWARD);}
        else{R.setDirection(DcMotor.Direction.REVERSE);}

        if(lForward){L.setDirection(DcMotor.Direction.FORWARD);}
        else{L.setDirection(DcMotor.Direction.REVERSE);}

        //Sets the runmode for each motor
        R.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        L.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}