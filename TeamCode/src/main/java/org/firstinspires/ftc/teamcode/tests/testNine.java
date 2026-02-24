package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.tasks.Launch;
import org.firstinspires.ftc.teamcode.tasks.LoadSequenceOne;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.tasks.OrientPowerLauncherLocalization;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testNine extends TritonicsOpMode {

    LoadSequenceOne loadSequenceOne;
    Task timer;
    Task launch;
    Localization localization;
    Task aim;
    Task otherTimer;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        isRedSide = true;

        intake.right.setPosition(Intake.rightBlocking);
        intake.left.setPosition(Intake.leftBlocking);
        intake.middle.setPosition(Intake.middleUp);

        intake.motor.setPower(1);

        loadSequenceOne = new LoadSequenceOne(intake, launcher);
        timer = new Timer(1000);
        otherTimer = new Timer(10000);
        launch = new Launch(intake, launcher);
        localization = new Localization(sensors, new RoboState(0,0,0,0,0,0), this);
        aim = new OrientPowerLauncherLocalization(launcher, localization, this);

        auto = new ParallelTask(new Task[]{new SeriesTask(new Task[]{otherTimer, loadSequenceOne, timer, launch}), localization, aim});

        waitForStart();

        while(opModeIsActive()){
            auto.run();
            telemetry.update();
        }

    }

}
