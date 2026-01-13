package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.OrientLauncherLimelight;

@Autonomous
public class testNine extends LinearOpMode {

    Sensors sensors;
    Launcher launcher;

    OrientLauncherLimelight orientLauncherLimelight;

    @Override
    public void runOpMode() {

        sensors = new Sensors(this, true);
        launcher = new Launcher(this);

        orientLauncherLimelight = new OrientLauncherLimelight(sensors, launcher);

        waitForStart();

        while(opModeIsActive()) {
            orientLauncherLimelight.run();
        }

    }

}
