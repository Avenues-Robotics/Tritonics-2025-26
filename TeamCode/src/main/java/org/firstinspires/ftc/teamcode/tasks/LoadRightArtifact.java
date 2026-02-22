package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.Intake;

public class LoadRightArtifact extends SeriesTask{

    public LoadRightArtifact(Intake intake, long milliseconds) {
        super(
                new SeriesTask(new RotateServo(Intake.rightOpening, intake.right),
                        new SeriesTask(new Timer(milliseconds),
                                new RotateServo(Intake.rightPushing, intake.right))),
                new Task()
        );
    }
}
