package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

public class Odometry extends Task{

    DriveTrain driveTrain;

    double odomLCurr;
    double odomRCurr;
    double odomHCurr;

    double odomLSpeed;
    double odomRSpeed;
    double odomHSpeed;

    double odomLPrev;
    double odomRPrev;
    double odomHPrev;

    ElapsedTime dt;

    public Odometry(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    @Override
    public boolean run() {

        odomLCurr = driveTrain.odomL.getCurrentPosition();
        odomRCurr = driveTrain.odomR.getCurrentPosition();
        odomHCurr = driveTrain.odomH.getCurrentPosition();

        odomLSpeed = (odomLPrev - odomLCurr)/dt.seconds();
        odomRSpeed = (odomRPrev - odomRCurr)/dt.seconds();

        return false;
    }

}
