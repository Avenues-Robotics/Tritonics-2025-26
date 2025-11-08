package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task is never finished because it always tranforms stick inputs into motor movements
 * as a teleop controller.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

public class DriveTeleop extends Task{

    DriveTrain driveTrain;
    LinearOpMode opMode;

    double max;

    public DriveTeleop(DriveTrain driveTrain, LinearOpMode opMode) {
        this.driveTrain = driveTrain;
        this.opMode = opMode;
    }

    public boolean run()
    {
        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        double fr = (opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x) * Math.abs(opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x);
        double fl = (opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x) * Math.abs(opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x);
        double br = (opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x) * Math.abs(opMode.gamepad1.left_stick_y + opMode.gamepad1.left_stick_x - opMode.gamepad1.right_stick_x);
        double bl = (opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x) * Math.abs(opMode.gamepad1.left_stick_y - opMode.gamepad1.left_stick_x + opMode.gamepad1.right_stick_x);

        // Normalize the values so no wheel power exceeds 100%
        max = Math.max(Math.max(Math.max(Math.abs(fl), Math.abs(fr)), Math.abs(bl)), Math.abs(br));

        if (max > 1.0) {
            fl /= max;
            fr /= max;
            bl /= max;
            br /= max;
        }

        driveTrain.FL.setPower(fl);
        driveTrain.FR.setPower(fr);
        driveTrain.BL.setPower(bl);
        driveTrain.BR.setPower(br);

        return false;
    }

}
