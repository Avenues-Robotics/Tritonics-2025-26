package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.tasks.FindLaunchSequence;
import org.firstinspires.ftc.teamcode.tasks.ParallelTask;
import org.firstinspires.ftc.teamcode.tasks.ReadMotif;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testMotif extends TritonicsOpMode {

    Task readMotif;
    Task timer;
    Task launchSequenceFinder;

    Task auto;

    public static Motif inputMotif;

    @Override
    public void runTritonicsOpMode() {

        readMotif = new ReadMotif(this);
        timer = new Timer(5000);
        launchSequenceFinder = new FindLaunchSequence(this, inputMotif);

        auto = new ParallelTask(readMotif, new SeriesTask(timer, launchSequenceFinder));

        waitForStart();

        while(opModeIsActive()) {
            auto.run();

            telem.addData("motif", motif);
            telem.update();
        }

    }

}
