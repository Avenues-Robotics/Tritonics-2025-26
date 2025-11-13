package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class PowerLauncherAssembly extends ParallelTask{

    public PowerLauncherAssembly(Launcher launcher, int speedLauncher, int speedTransfer) {
        super(
                new PowerLauncher(launcher, speedLauncher),
                new PowerTransfer(launcher, speedTransfer)
        );
    }

}
