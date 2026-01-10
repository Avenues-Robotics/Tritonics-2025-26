package org.firstinspires.ftc.teamcode.hardware;

/*
 * Manages drive train hardware including motors and odometry wheels
 */

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class DriveTrain {

    //Four drive train motors
    public DcMotor FR;
    public DcMotor FL;
    public DcMotor BR;
    public DcMotor BL;

    //Direction of each drive train motor, dashboard configurable
    public static boolean frForward = true;
    public static boolean flForward = false;
    public static boolean brForward = true;
    public static boolean blForward = false;

    //Scaling constants between cm/deg and ticks
    public static double cmToTicks = 11;
    public static double degToTicks = 5.4;

    //Three odometry wheels (Right, Left, and Horizontal respectively) and their motor slots
    public DcMotor odomR; public static String odomRConfig = "FR"; public static double rDistance = 938457;
    public DcMotor odomL; public static String odomLConfig = "FL"; public static double lDistance = 393475;
    public DcMotor odomH; public static String odomHConfig = "BR"; public static double hDistance = 584393;

    //Dashboard telemetry instance
    public Telemetry telem;

    //Drive Train constructor initiates drive train hardware, giving the option to run without encoder
    public DriveTrain(LinearOpMode opMode, boolean runWithEncoder){
        //Grabs four drive train motors
        FR = opMode.hardwareMap.get(DcMotor.class, "FR");
        FL = opMode.hardwareMap.get(DcMotor.class, "FL");
        BR = opMode.hardwareMap.get(DcMotor.class, "BR");
        BL = opMode.hardwareMap.get(DcMotor.class, "BL");

        //Sets the direction for each motor depending on the setting
        if(frForward){FR.setDirection(DcMotor.Direction.FORWARD);}
        else{FR.setDirection(DcMotor.Direction.REVERSE);}

        if(flForward){FL.setDirection(DcMotor.Direction.FORWARD);}
        else{FL.setDirection(DcMotor.Direction.REVERSE);}

        if(brForward){BR.setDirection(DcMotor.Direction.FORWARD);}
        else{BR.setDirection(DcMotor.Direction.REVERSE);}

        if(blForward){BL.setDirection(DcMotor.Direction.FORWARD);}
        else{BL.setDirection(DcMotor.Direction.REVERSE);}

        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Sets the runmode of the motors
        if(runWithEncoder) {
            FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        //Sets the odometry wheels equal to the proper encoders
        odomR = opMode.hardwareMap.get(DcMotor.class, odomRConfig);
        odomL = opMode.hardwareMap.get(DcMotor.class, odomLConfig);
        odomH = opMode.hardwareMap.get(DcMotor.class, odomHConfig);

        odomR.setDirection(DcMotor.Direction.REVERSE);
        odomL.setDirection(DcMotor.Direction.REVERSE);

        //grabs a dashboard telemetry instance
        telem = FtcDashboard.getInstance().getTelemetry();
    }

    //Same as above without runmode option
    //Drive Train constructor initiates drive train hardware, not giving the option to run without encoder
    public DriveTrain(LinearOpMode opMode){
        //Grabs four drive train motors
        FR = opMode.hardwareMap.get(DcMotor.class, "FR");
        FL = opMode.hardwareMap.get(DcMotor.class, "FL");
        BR = opMode.hardwareMap.get(DcMotor.class, "BR");
        BL = opMode.hardwareMap.get(DcMotor.class, "BL");

        //Sets the direction for each motor depending on the setting
        if(frForward){FR.setDirection(DcMotor.Direction.FORWARD);}
        else{FR.setDirection(DcMotor.Direction.REVERSE);}

        if(flForward){FL.setDirection(DcMotor.Direction.FORWARD);}
        else{FL.setDirection(DcMotor.Direction.REVERSE);}

        if(brForward){BR.setDirection(DcMotor.Direction.FORWARD);}
        else{BR.setDirection(DcMotor.Direction.REVERSE);}

        if(blForward){BL.setDirection(DcMotor.Direction.FORWARD);}
        else{BL.setDirection(DcMotor.Direction.REVERSE);}

        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Sets the runmode of the motors
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Sets the odometry wheels equal to the proper encoders
        odomR = opMode.hardwareMap.get(DcMotor.class, odomRConfig);
        odomL = opMode.hardwareMap.get(DcMotor.class, odomLConfig);
        odomH = opMode.hardwareMap.get(DcMotor.class, odomHConfig);

        //grabs a dashboard telemetry instance
        telem = FtcDashboard.getInstance().getTelemetry();
    }
}