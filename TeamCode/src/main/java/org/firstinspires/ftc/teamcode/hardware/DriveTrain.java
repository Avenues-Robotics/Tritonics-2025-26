package org.firstinspires.ftc.teamcode.hardware;

/*
 * Manages drive train hardware including motors and odometry wheels
 */

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class DriveTrain {

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

    //Dashboard telemetry instance
    Telemetry telem;

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

    //drive function, input speed, distance in cm, and degree angle of the movement
    public void drive(double speed, double cm, double degrees) {

        //Instantiate the multipliers that will control the speed of each wheel
        double frblMultiplier;
        double flbrMultiplier;

        double radians = degrees * PI / 180;

        frblMultiplier = cos(radians)-sin(radians);
        flbrMultiplier = cos(radians)+sin(radians);

        // Reset the tick encoders to zero
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Calculate the number of ticks based on the distance and a conversion constant
        int ticks = (int) (cm * 17.467);

        //Calculate the number of ticks required for each motor to move
        int frblTicks = (int) (ticks * frblMultiplier);
        int flbrTicks = (int) (ticks * flbrMultiplier);

        // set the target position
        FR.setTargetPosition(frblTicks);
        FL.setTargetPosition(flbrTicks);
        BR.setTargetPosition(flbrTicks);
        BL.setTargetPosition(frblTicks);

        // Change the mode to spin until reaching the position
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // make the motors run at the given speed
        FR.setPower(speed * frblMultiplier);
        FL.setPower(speed * flbrMultiplier);
        BR.setPower(speed * flbrMultiplier);
        BL.setPower(speed * frblMultiplier);

        // Run while the motors are moving
        while (Math.abs(FR.getTargetPosition()-FR.getCurrentPosition()) >= 30 ||
                Math.abs(FR.getTargetPosition()-FR.getCurrentPosition()) >= 30) {

            // Update the telem data
            if (speed*frblMultiplier > 1 || speed*flbrMultiplier > 1) {
                telem.addData("Drive Status", "The set speed and direction has maxed out the speed of one of the wheels. Direction and speed may not be accurate");
            }
            telem.addData("Running to", "Font Right and Back Left: " + frblTicks + " | Front Left and Back Right: " + flbrTicks);
            telem.addData("Current pos", "Front Right: " + FR.getCurrentPosition() + " | Front Left: " + FL.getCurrentPosition() + " | Back Right: " + BR.getCurrentPosition() + " | Back Left: " + BL.getCurrentPosition());
            telem.update();
        }

        // Stop the motors
        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);

    }

    //rotate function, input speed, and degree angle to rotate
    public void rotate(double speed, double degrees){

        // Reset the tick encoders to zero
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Convert from degrees to ticks
        int ticks = (int) (degrees*12.4);

        // set the target position
        FR.setTargetPosition(-ticks);
        FL.setTargetPosition(ticks);
        BR.setTargetPosition(-ticks);
        BL.setTargetPosition(ticks);

        // Change the mode to spin until reaching the position
        FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // make the motors run at the given speed
        FR.setPower(-speed);
        FL.setPower(-speed);
        BR.setPower(-speed);
        BL.setPower(-speed);

        // Run while both motors are moving
        while (Math.abs(FR.getTargetPosition()-FR.getCurrentPosition()) >= 50 ||
                Math.abs(FL.getTargetPosition()-FL.getCurrentPosition()) >= 50) {
            // Update the telem data
            telem.addData("Running to", "Left " + ticks + " | Right: " + -ticks);
            telem.addData("Current pos", "Front Right: " + FR.getCurrentPosition() + " | Front Left: " + FL.getCurrentPosition() + " | Back Right: " + BR.getCurrentPosition() + " | Back Left: " + BL.getCurrentPosition());
            telem.update();
        }

        // Stop the motors
        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);
    }
}