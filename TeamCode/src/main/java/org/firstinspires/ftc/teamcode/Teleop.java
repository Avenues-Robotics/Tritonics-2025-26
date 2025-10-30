package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Teleop(name = "Tritonics Teleop")
public class Teleop extends LinearOpMode {

    @Override
    public void runOpMode() {
        init();
        waitForStart();
        init
        while (opModeIsActive()) {
            drive();
            buttons();
        }
    }
}
