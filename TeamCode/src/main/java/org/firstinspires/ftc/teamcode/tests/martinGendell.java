package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class martinGendell extends TritonicsOpMode {

    @Override
    public void runTritonicsOpMode() {

        waitForStart();

        sensors.prism.setSegment(0, 0, 255, 255, 255, 255, 40);

    }

}
