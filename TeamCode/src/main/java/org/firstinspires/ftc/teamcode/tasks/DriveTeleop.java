package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task is never finished because it always tranforms stick inputs into motor movements
 * as a teleop controller.
 */

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

@Config
public class DriveTeleop extends Task{

    public static int smoothing = 2;

    DriveTrain driveTrain;
    LinearOpMode opMode;

    double max;

    public DriveTeleop(DriveTrain driveTrain, LinearOpMode opMode) {
        this.driveTrain = driveTrain;
        this.opMode = opMode;
    }

    @Override
    public boolean run()
    {
        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        double fr = (opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/1.5) * Math.abs(Math.pow(opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/1.5, smoothing - 1));
        double fl = (opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/1.5) * Math.abs(Math.pow(opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/1.5, smoothing - 1));
        double br = (opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/1.5) * Math.abs(Math.pow(opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x/1.5, smoothing - 1));
        double bl = (opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/1.5) * Math.abs(Math.pow(opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x/1.5, smoothing - 1));

        // Normalize the values so no wheel power exceeds 100%
        max = Math.max(Math.max(Math.max(Math.abs(fl), Math.abs(fr)), Math.abs(bl)), Math.abs(br));

        if (max > 1.0) {
            fl /= max;
            fr /= max;
            bl /= max;
            br /= max;
        }

        driveTrain.telem.addData("fr", fr);
        driveTrain.telem.addData("bl", bl);
        driveTrain.telem.update();

        driveTrain.FL.setPower(fl);
        driveTrain.FR.setPower(fr);
        driveTrain.BL.setPower(bl);
        driveTrain.BR.setPower(br);

        return false;
    }

    @Override
    public Task reset(){
        return new DriveTeleop(driveTrain, opMode);
    }

}
