package org.firstinspires.ftc.teamcode.tasks;

/*
 * This sequence launches the leftmost, than the rightmost, then the middle artifacts
 */

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class LoadSequenceThree extends SeriesTask {

    public LoadSequenceThree(Intake intake, Launcher launcher) {
        super(new RotateServo(Launcher.blockerBlocking, launcher.blocker),
                new SeriesTask(new PowerTransfer(launcher, 1),
                        new SeriesTask(new PowerIntake(intake, 1),
                            new SeriesTask(new LoadLeftArtifact(intake, 500),
                                    new SeriesTask(new Timer(500),
                                            new SeriesTask(new LoadRightArtifact(intake, 500),
                                                    new SeriesTask(new Timer (500),
                                                            new SeriesTask(new RotateServo(Intake.middleDown, intake.middle),
                                                                    new PowerIntake(intake, -0.2)))))))));
    }

}
