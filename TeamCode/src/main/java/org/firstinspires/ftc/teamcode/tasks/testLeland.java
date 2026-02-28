package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testLeland extends TritonicsOpMode {

    public static double rPower = 1;
    public static double lPower = 1;

    @Override
    public void runTritonicsOpMode() {

        waitForStart();

        launcher.R.setPower(rPower);
        launcher.L.setPower(lPower);

    }

}
