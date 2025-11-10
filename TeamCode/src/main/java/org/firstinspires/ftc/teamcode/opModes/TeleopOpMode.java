package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.tasks.DriveTeleop;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.Task;

@TeleOp(name = "Tritonics Teleop")
public class TeleopOpMode extends LinearOpMode {

    DriveTrain driveTrain;

    DriveTeleop driveTeleop;

    Task teleop;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);

        driveTeleop = new DriveTeleop(driveTrain, this);

        teleop = new ParallelTask(driveTeleop, new Task());

        waitForStart();

        while (opModeIsActive()) {
            teleop.run();
        }
    }
}
