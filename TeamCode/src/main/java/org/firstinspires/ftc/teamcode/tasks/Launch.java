package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class Launch extends SeriesTask{

    public Launch(Intake intake, Launcher launcher) {
        super(new RotateServo(Launcher.blockerAway, launcher.blocker),
                new SeriesTask(new Timer(200),
                        new SeriesTask(new RotateServo(Intake.leftBlocking, intake.left),
                                new SeriesTask(new RotateServo(Intake.rightBlocking, intake.right),
                                        new SeriesTask(new RotateServo(Intake.middleUp, intake.middle),
                                                new PowerIntake(intake, 1))))));
    }

}
