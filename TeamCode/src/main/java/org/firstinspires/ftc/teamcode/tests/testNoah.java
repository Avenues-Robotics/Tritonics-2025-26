package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testNoah extends TritonicsOpMode {

    public static double velocity = 2000;

    @Override
    public void runTritonicsOpMode() {

        telem.addData("Velocity", 0);
        telem.update();

        waitForStart();

        while(opModeIsActive()) {
            launcher.R.setVelocity(velocity);
            launcher.L.setVelocity(velocity);
            telem.addData("Velocity", launcher.R.getVelocity());
            telem.update();
        }

    }

}
