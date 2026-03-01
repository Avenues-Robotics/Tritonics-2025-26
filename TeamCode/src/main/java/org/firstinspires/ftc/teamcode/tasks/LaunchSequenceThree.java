package org.firstinspires.ftc.teamcode.tasks;

/*
 * This sequence launches the leftmost, than the rightmost, then the middle artifacts
 */

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class LaunchSequenceThree extends SeriesTask {

    public LaunchSequenceThree(Intake intake, Launcher launcher) {
        super(new RotateServo(Launcher.blockerAway, launcher.blocker),
                new SeriesTask(new PowerTransfer(launcher, 1),
                        new SeriesTask(new PowerIntake(intake, 1),
                                new SeriesTask(new LoadLeftArtifact(intake, 300),
                                        new SeriesTask(new Timer(300),
                                                new SeriesTask(new LoadRightArtifact(intake, 300),
                                                        new SeriesTask(new Timer (300),
                                                                new SeriesTask(new RotateServo(Intake.middleDown, intake.middle),
                                                                        new SeriesTask(new Timer(300),
                                                                                new SeriesTask(new PowerIntake(intake, 1),
                                                                                        new PowerTransfer(launcher, 0)))))))))));
    }

}
