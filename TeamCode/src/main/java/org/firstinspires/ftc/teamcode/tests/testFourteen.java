package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous
@Config
public class testFourteen extends TritonicsOpMode {

    Localization localization;

    @Override
    public void runTritonicsOpMode() {

        localization = new Localization(sensors, new RoboState(0,0,0,0,0,0), this);

        waitForStart();

        while(opModeIsActive()) {
            localization.run();
            telemetry.update();
        }

    }

}
