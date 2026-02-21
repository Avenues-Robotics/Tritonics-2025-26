package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.utilities.GlobalVariables;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous
public class testTwothousandFourhundredSixtyEight extends TritonicsOpMode {

    public Localization localization;
    public OrientPowerLauncherLocalization orientPowerLauncherLocalization;

    public Task auto;

    @Override
    public void runTritonicsOpMode() {
        globalVariables.isRedSide = true;

        localization = new Localization(sensors, new RoboState(0,0,0,0,0,0));
        orientPowerLauncherLocalization = new OrientPowerLauncherLocalization(launcher, localization, this);

        waitForStart();

        auto = new ParallelTask(localization, orientPowerLauncherLocalization);

        while(opModeIsActive()) {
            auto.run();
        }
    }

}
