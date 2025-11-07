package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

@Config
@Autonomous(name = "Tritonics Autonomous")
public class AutoOpMode extends LinearOpMode {

    DriveTrain driveTrain;

    public static double speedOne = 1;
    public static double distOne = 108;
    public static double degOne = 55;

    public static double speedTwo = 1;
    public static double distTwo = 60;
    public static double degTwo = 0;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, true);

        waitForStart();

        driveTrain.drive(speedOne, distOne, degOne);
        driveTrain.drive(speedTwo, distTwo, degTwo);
        sleep(1000);
    }
}
