package org.firstinspires.ftc.teamcode.tasks;

/*
 * This sequence launches the rightmost, than the middle, then the leftmost artifacts
 */

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class LaunchSequenceOne extends SeriesTask {

    public LaunchSequenceOne(Intake intake, Launcher launcher) {
        super(new RotateServo(Launcher.blockerAway, launcher.blocker),
                new SeriesTask(new PowerTransfer(launcher, 1),
                        new SeriesTask(new PowerIntake(intake, 1),
                            new SeriesTask(new LoadRightArtifact(intake, 300),
                                    new SeriesTask(new Timer(300),
                                            new SeriesTask(new RotateServo(Intake.middleDown, intake.middle),
                                                    new SeriesTask(new Timer (300),
                                                            new SeriesTask(new LoadLeftArtifact(intake, 300),
                                                                    new SeriesTask(new Timer(300),
                                                                        new SeriesTask(new PowerIntake(intake, 1),
                                                                                new PowerTransfer(launcher, 0)))))))))));
    }

}
