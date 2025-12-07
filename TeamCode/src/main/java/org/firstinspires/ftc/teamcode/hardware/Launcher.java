package org.firstinspires.ftc.teamcode.hardware;

/*
 * Manages launcher hardware including motors and servos
 */

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Launcher {

    //The two motors driving the launcher
    public DcMotorEx R;
    public DcMotorEx L;

    //Motor driving the ramp
    public DcMotor ramp;

    //Direction of each motor, dashboard configurable
    public static boolean rForward = true;
    public static boolean lForward = false;
    public static boolean rampForward = true;

    //The servos angling the launcher
    public Servo RA;
    public Servo DEC;

    //Launcher constructor, initiates launcher hardware
    public Launcher(LinearOpMode opMode) {
        //Grabs motors
        R = opMode.hardwareMap.get(DcMotorEx.class, "launcherR");
        L = opMode.hardwareMap.get(DcMotorEx.class, "launcherL");
        ramp = opMode.hardwareMap.get(DcMotor.class, "transfer");

        //Grabs servo
        RA = opMode.hardwareMap.get(Servo.class, "launcherRA");
        DEC = opMode.hardwareMap.get(Servo.class, "launcherDEC");

        //Sets the direction for each motor depending on the setting
        if(rForward){R.setDirection(DcMotor.Direction.FORWARD);}
        else{R.setDirection(DcMotor.Direction.REVERSE);}

        if(lForward){L.setDirection(DcMotor.Direction.FORWARD);}
        else{L.setDirection(DcMotor.Direction.REVERSE);}

        if(rampForward){ramp.setDirection(DcMotor.Direction.FORWARD);}
        else{ramp.setDirection(DcMotor.Direction.REVERSE);}

        //Sets the runmode for each motor
        R.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        L.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // Looks good so far.  I know this is still a work in progress.  
    // One thing I would definitely add is a PID for velocity control.
    // Even if you are not changing shooter motor speeds, the power = 1
    // will change over the course of the match as your battery drains.
    // Measure a reasonable maximum low battery ticks/sec and use that.
    // - Mr. Carpenter

}
