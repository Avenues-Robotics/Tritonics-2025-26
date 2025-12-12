package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

public class OdometryLocalization extends Task {

    DriveTrain driveTrain;

    public OdometryLocalization(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    @Override
    public boolean run() {

        /// Milind, write your actual code here

        return false;
    }

    @Override
    public Task reset() {
        return new OdometryLocalization(driveTrain);
    }

}
