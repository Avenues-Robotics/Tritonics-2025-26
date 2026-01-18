package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.utilities.VelPID;


public class LaunchSequence extends SeriesTask {

    public static double rightServoUp = 0.967;
    public static double rightServoDown = 0.76;
    public static double leftServoUp = 0.19;
    public static double leftServoDown = 0;

    public LaunchSequence(Intake intake, Launcher launcher) {
        super(new SeriesTask(new ParallelTask(new PowerIntake(intake, 1),
              new PowerTransfer(launcher, 1)),
                    new SeriesTask(new Timer(1500),
                            new SeriesTask(new RotateServo(rightServoUp, intake.right),
                                    new SeriesTask(new Timer(1500),
                                            new SeriesTask(new RotateServo(leftServoUp, intake.left),
                                                    new SeriesTask(new Timer(1500),
                                                            new ParallelTask(new PowerIntake(intake, 0),
                                                            new ParallelTask(new PowerTransfer(launcher, 0),
                                                            new ParallelTask(new RotateServo(rightServoDown, intake.right),
                                                            new RotateServo(leftServoDown, intake.left)
        ))))))))), new Task());
    }
}
