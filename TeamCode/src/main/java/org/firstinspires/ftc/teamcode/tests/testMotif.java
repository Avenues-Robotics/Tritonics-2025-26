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
    Task launchSequenceFinder1;
    Task launchSequenceFinder2;
    Task launchSequenceFinder3;

    Task auto;

    @Override
    public void runTritonicsOpMode() {

        readMotif = new ReadMotif(this);
        timer = new Timer(5000);
        launchSequenceFinder1 = new FindLaunchSequence(this, Motif.GPP);
        launchSequenceFinder2 = new FindLaunchSequence(this, Motif.PGP);
        launchSequenceFinder3 = new FindLaunchSequence(this, Motif.PPG);

        auto = new ParallelTask(readMotif, new SeriesTask(new Task[]{timer, launchSequenceFinder1, launchSequenceFinder2, launchSequenceFinder3}));

        waitForStart();

        while(opModeIsActive()) {
            auto.run();

            telem.addData("motif", motif);
            telem.update();
        }

    }

}
