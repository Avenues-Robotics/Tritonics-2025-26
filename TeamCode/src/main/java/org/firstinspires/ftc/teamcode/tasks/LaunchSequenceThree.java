package org.firstinspires.ftc.teamcode.tasks;

/*
 * This sequence launches the leftmost, than the rightmost, then the middle artifacts
 */

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

@Config
public class LaunchSequenceThree extends SeriesTask {

    public static long wait = 300;

    public LaunchSequenceThree(Intake intake, Launcher launcher) {
        super(new RotateServo(Launcher.blockerAway, launcher.blocker),
                new SeriesTask(new PowerTransfer(launcher, 1),
                        new SeriesTask(new PowerIntake(intake, 1),
                                new SeriesTask(new LoadLeftArtifact(intake, 300),
                                        new SeriesTask(new Timer(300),
                                                new SeriesTask(new LoadRightArtifact(intake, 300),
                                                        new SeriesTask(new Timer (300),
                                                                new SeriesTask(new RotateServo(Intake.middleDown, intake.middle),
                                                                        new SeriesTask(new Timer(600),
                                                                                new SeriesTask(new RotateServo(Intake.rightBlocking, intake.right),
                                                                                        new SeriesTask(new RotateServo(Intake.middleUp, intake.middle),
                                                                                                new SeriesTask(new RotateServo(Intake.leftBlocking, intake.left),
                                                                                new SeriesTask(new PowerIntake(intake, 1),
                                                                                        new PowerTransfer(launcher, 1))))))))))))));
    }

}
