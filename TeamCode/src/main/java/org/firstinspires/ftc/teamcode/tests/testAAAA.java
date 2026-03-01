package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.tasks.LaunchSequenceThree;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testAAAA extends TritonicsOpMode {

    Task launch;

    @Override
    public void runTritonicsOpMode() {

        launch = new LaunchSequenceThree(intake, launcher);

        waitForStart();

        while(opModeIsActive()) {
            launch.run();
        }

    }

}
