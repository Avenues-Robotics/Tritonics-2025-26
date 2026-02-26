package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

public class Relocalize extends Task{

    Sensors sensors;
    Pose2D pose;

    public Relocalize(Sensors sensors, Pose2D pose) {
        this.sensors = sensors;
        this.pose = pose;
    }

    @Override
    public boolean run() {
        sensors.odo.setPosition(pose);
        return true;
    }

    @Override
    public Task reset() {
        return new Relocalize(sensors, pose);
    }

}
