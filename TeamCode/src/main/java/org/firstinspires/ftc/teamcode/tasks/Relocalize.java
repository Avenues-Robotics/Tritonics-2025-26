package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

public class Relocalize extends Task{

    Sensors sensors;
    Pose2D pose;
    TritonicsOpMode opMode;

    public Relocalize(Sensors sensors, Pose2D pose, TritonicsOpMode opMode) {
        this.sensors = sensors;
        this.pose = pose;
        this.opMode = opMode;
    }

    @Override
    public boolean run() {
        sensors.odo.setPosition(pose);
        opMode.offset = 0;
        return true;
    }

    @Override
    public Task reset() {
        return new Relocalize(sensors, pose, opMode);
    }

}
