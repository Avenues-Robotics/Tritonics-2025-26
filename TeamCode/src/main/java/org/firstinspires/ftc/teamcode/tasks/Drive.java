package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task can drive the robot in a given direction, at a given speed, for a given distance
 */

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

public class Drive extends Task{

    double radians;

    double frblMultiplier;
    double flbrMultiplier;

    int ticks;

    int frblTicks;
    int flbrTicks;

    DriveTrain driveTrain;

    double speed;
    double cm;
    double degrees;

    public static int tolerance = 30;

    enum State {
        STEPONE,
        STEPTWO,
        STEPTHREE,
        STEPFOUR,
        DONE
    }

    State state;

    public Drive(DriveTrain driveTrain, double speed, double cm, double degrees) {
        this.driveTrain = driveTrain;
        this.speed = speed;
        this.cm = cm;
        this.degrees = degrees;

        state = State.STEPONE;
    }

    @Override
    public boolean run() {
        if(state == State.STEPONE) {
            stepOne();
        }
        if (state == State.STEPTWO) {
            stepTwo();
        }
        if (state == State.STEPTHREE) {
            stepThree();
        }
        if (state == State.STEPFOUR) {
            stepFour();
        }
        return state == State.DONE;
    }

    void stepOne() {
        radians = degrees * PI / 180;

        frblMultiplier = cos(radians)-sin(radians);
        flbrMultiplier = cos(radians)+sin(radians);

        // Reset the tick encoders to zero
        driveTrain.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Calculate the number of ticks based on the distance and a conversion constant
        ticks = (int) (cm * DriveTrain.cmToTicks);

        //Calculate the number of ticks required for each motor to move
        frblTicks = (int) (ticks * frblMultiplier);
        flbrTicks = (int) (ticks * flbrMultiplier);

        // set the target position
        driveTrain.FR.setTargetPosition(frblTicks);
        driveTrain.FL.setTargetPosition(flbrTicks);
        driveTrain.BR.setTargetPosition(flbrTicks);
        driveTrain.BL.setTargetPosition(frblTicks);

        // Change the mode to spin until reaching the position
        driveTrain.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveTrain.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveTrain.BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveTrain.BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // make the motors run at the given speed
        driveTrain.FR.setPower(speed * frblMultiplier);
        driveTrain.FL.setPower(speed * flbrMultiplier);
        driveTrain.BR.setPower(speed * flbrMultiplier);
        driveTrain.BL.setPower(speed * frblMultiplier);

        state = State.STEPTWO;
    }

    void stepTwo() {
        // Update the telem data
        if (speed*frblMultiplier > 1 || speed*flbrMultiplier > 1) {
            driveTrain.telem.addData("Drive Status", "The set speed and direction has maxed out the speed of one of the wheels. Direction and speed may not be accurate");
        }
        driveTrain.telem.addData("Running to", "Font Right and Back Left: " + frblTicks + " | Front Left and Back Right: " + flbrTicks);
        driveTrain.telem.addData("Current pos", "Front Right: " + driveTrain.FR.getCurrentPosition() + " | Front Left: " + driveTrain.FL.getCurrentPosition() + " | Back Right: " + driveTrain.BR.getCurrentPosition() + " | Back Left: " + driveTrain.BL.getCurrentPosition());
        driveTrain.telem.update();

        if(Math.abs(driveTrain.FR.getTargetPosition()-driveTrain.FR.getCurrentPosition()) <= tolerance &&
                Math.abs(driveTrain.FL.getTargetPosition()-driveTrain.FL.getCurrentPosition()) <= tolerance) {
            state = State.STEPTHREE;
        }
    }

    void stepThree() {
        //realign rotation with imu (for later)
        state = State.STEPFOUR;
    }

    void stepFour() {
        //Stop motor power
        driveTrain.FR.setPower(0);
        driveTrain.FL.setPower(0);
        driveTrain.BR.setPower(0);
        driveTrain.BL.setPower(0);

        state = State.DONE;
    }
}
