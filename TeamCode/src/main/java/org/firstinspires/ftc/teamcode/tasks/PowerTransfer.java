package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class PowerTransfer extends Task{

    Launcher launcher;
    double speed;

    public PowerTransfer(Launcher launcher, double speed) {
        this.launcher = launcher;
        this.speed = speed;
    }

    @Override
    public boolean run() {
        launcher.ramp.setPower(speed);
        return true;
    }

    @Override
    public boolean end() {
        launcher.ramp.setPower(0);
        return true;
    }

}
