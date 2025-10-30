package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Tritonics Autonomous")
public class AutoOpMode extends LinearOpMode {

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
