package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class OrientLauncher extends Task{

    double RA;
    double DEC;
    Launcher launcher;

    public OrientLauncher(Launcher launcher, double RA, double DEC) {
        this.RA = RA;
        this.DEC = DEC;
        this.launcher = launcher;
    }

    @Override
    public boolean run(){
        launcher.RA.setPosition(RA);
        //launcher.DEC.setPosition(DEC);
        return true;
    }

    @Override
    public Task reset() {
        return new OrientLauncher(launcher, RA, DEC);
    }

}
