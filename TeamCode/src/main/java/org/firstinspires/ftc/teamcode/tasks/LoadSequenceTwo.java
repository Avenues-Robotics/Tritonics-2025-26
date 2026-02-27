package org.firstinspires.ftc.teamcode.tasks;

/*
 * This sequence launches the middle, than the leftmost, then the rightmost artifacts
 */

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class LoadSequenceTwo extends SeriesTask {

    public LoadSequenceTwo(Intake intake, Launcher launcher) {
        super(new RotateServo(Launcher.blockerBlocking, launcher.blocker),
                new SeriesTask(new PowerTransfer(launcher, 1),
                        new SeriesTask(new PowerIntake(intake, 1),
                            new SeriesTask(new RotateServo(Intake.middleDown, intake.middle),
                                    new SeriesTask(new Timer(300),
                                            new SeriesTask(new LoadLeftArtifact(intake, 300),
                                                    new SeriesTask(new Timer (300),
                                                            new SeriesTask(new LoadRightArtifact(intake, 300),
                                                                    new SeriesTask(new PowerIntake(intake, 0),
                                                                            new PowerTransfer(launcher, 0))))))))));
    }

}
