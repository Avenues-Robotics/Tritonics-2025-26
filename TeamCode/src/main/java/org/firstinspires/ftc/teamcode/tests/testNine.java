package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceOne;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testNine extends TritonicsOpMode {

    LoadSequenceOne loadSequenceOne;

    @Override
    public void runTritonicsOpMode() {

        intake.right.setPosition(Intake.rightBlocking);
        intake.left.setPosition(Intake.leftBlocking);
        intake.middle.setPosition(Intake.middleUp);

        loadSequenceOne = new LoadSequenceOne(intake, launcher);

        waitForStart();

        while(opModeIsActive()){
            loadSequenceOne.run();
        }

    }

}
