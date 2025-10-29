package org.firstinspires.ftc.teamcode.hardware;

/*
 * Manages drive train hardware including motors and odometry wheels
 */

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
public class driveTrain {

    //Four drive train motors
    DcMotor FR;
    DcMotor FL;
    DcMotor BR;
    DcMotor BL;

    //Direction of each drive train motor, dashboard configurable
    public static boolean frForward = true;
    public static boolean flForward = true;
    public static boolean brForward = true;
    public static boolean blForward = true;

    //Three odometry wheels (Right, Left, and Horizontal respectively) and their motor slots
    DcMotor odomR; public static String odomRConfig = "FR";
    DcMotor odomL; public static String odomLConfig = "FL";
    DcMotor odomH; public static String odomHConfig = "BR";

    //Drive Train constructor initiates drive train hardware, giving the option to run without encoder
    public driveTrain(LinearOpMode opMode, boolean runWithEncoder){
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
    }

    //Same as above without runmode option
    //Drive Train constructor initiates drive train hardware, not giving the option to run without encoder
    public driveTrain(LinearOpMode opMode){
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

        //Sets the runmode of the motors
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Sets the odometry wheels equal to the proper encoders
        odomR = opMode.hardwareMap.get(DcMotor.class, odomRConfig);
        odomL = opMode.hardwareMap.get(DcMotor.class, odomLConfig);
        odomH = opMode.hardwareMap.get(DcMotor.class, odomHConfig);
    }

}