package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.Intake;

public class LoadLeftArtifact extends SeriesTask{

    public LoadLeftArtifact(Intake intake, long milliseconds) {
        super(
                new SeriesTask(new RotateServo(Intake.leftOpening, intake.left),
                        new SeriesTask(new Timer(milliseconds),
                                new RotateServo(Intake.leftPushing, intake.left))),
                new Task()
        );
    }
}
