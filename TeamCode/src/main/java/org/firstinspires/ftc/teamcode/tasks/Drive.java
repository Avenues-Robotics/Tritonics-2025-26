package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task can drive the robot in a given direction, at a given speed, for a given distance
 */

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

@Config
public class Drive extends Task{

    double radians;

    double frblMultiplier;
    double flbrMultiplier;

    int ticks;

    int frblTicks;
    int flbrTicks;

    DriveTrain driveTrain;
    Sensors sensors;

    Rotate rotate;

    double speed;
    double cm;
    double degrees;

    public static int tolerance = 30;
    public static double rotateCorrectionSpeed = 0.5;

    enum State {
        STEPONE,
        STEPTWO,
        STEPTHREE,
        STEPFOUR,
        DONE
    }

    State state;

    public Drive(DriveTrain driveTrain, Sensors sensors, double speed, double cm, double degrees) {
        this.driveTrain = driveTrain;
        this.sensors = sensors;
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

        // Reset the imu
        sensors.imu.resetYaw();

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
        rotate = new Rotate(driveTrain, sensors, rotateCorrectionSpeed, sensors.imu.getRobotYawPitchRollAngles().getYaw());
        state = State.STEPFOUR;
    }

    void stepFour() {
        if(rotate.run()) {
            state = State.DONE;
        }
    }
}
