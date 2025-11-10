package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task can rotate the robot given some speed and some number of degrees to rotate
 */

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

@Config
public class Rotate extends Task{

    DriveTrain driveTrain;
    Sensors sensors;

    double speed;
    double degrees;

    int ticks;

    public static int tolerance = 15;

    enum State {
        RESETIMU,
        INIT,
        MOVE,
        STOP,
        DONE
    }

    State state;

    public Rotate(DriveTrain driveTrain, Sensors sensors, double speed, double degrees) {
        this.driveTrain = driveTrain;
        this.sensors = sensors;
        this.speed = speed;
        this.degrees = degrees;

        state = State.RESETIMU;
    }

    @Override
    public boolean run() {
        if(state == State.RESETIMU) {
            resetImu();
        }
        if(state == State.INIT) {
            init();
        }
        if(state == State.MOVE) {
            move();
        }
        if(state == State.STOP) {
            stop();
        }

        return state == State.DONE;
    }

    void resetImu() {
        // Reset the imu
        sensors.imu.resetYaw();

        state = State.INIT;
    }

    void init() {
        degrees = degrees + sensors.imu.getRobotYawPitchRollAngles().getYaw();

        // Reset the tick encoders to zero
        driveTrain.FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Convert from degrees to ticks
        ticks = (int) (degrees * DriveTrain.degToTicks);

        // set the target position
        driveTrain.FR.setTargetPosition(-ticks);
        driveTrain.FL.setTargetPosition(ticks);
        driveTrain.BR.setTargetPosition(-ticks);
        driveTrain.BL.setTargetPosition(ticks);

        // Change the mode to spin until reaching the position
        driveTrain.FR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveTrain.FL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveTrain.BR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveTrain.BL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // make the motors run at the given speed
        driveTrain.FR.setPower(-speed);
        driveTrain.FL.setPower(-speed);
        driveTrain.BR.setPower(-speed);
        driveTrain.BL.setPower(-speed);

        state = State.MOVE;
    }
    void move() {
        // Update the telem data
        driveTrain.telem.addData("Running to", "Left " + ticks + " | Right: " + -ticks);
        driveTrain.telem.addData("Current pos", "Front Right: " + driveTrain.FR.getCurrentPosition() + " | Front Left: " + driveTrain.FL.getCurrentPosition() + " | Back Right: " + driveTrain.BR.getCurrentPosition() + " | Back Left: " + driveTrain.BL.getCurrentPosition());
        driveTrain.telem.update();

        if(Math.abs(driveTrain.FR.getTargetPosition()-driveTrain.FR.getCurrentPosition()) <= tolerance &&
                Math.abs(driveTrain.FL.getTargetPosition()-driveTrain.FL.getCurrentPosition()) <= tolerance) {
            state = State.STOP;
        }
    }

    void stop() {
        // Stop the motors
        driveTrain.FR.setPower(0);
        driveTrain.FL.setPower(0);
        driveTrain.BR.setPower(0);
        driveTrain.BL.setPower(0);

        state = State.DONE;
    }

}
