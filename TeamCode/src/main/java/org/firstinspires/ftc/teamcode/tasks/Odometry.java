package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

public class Odometry extends Task{

    int TotalTicksL;
    int TotalTicksR;
    int TotalTicksB;

    int currentTicksL;
    int currentTicksR;
    int currentTicksB;

    int storedTicksL;
    int storedTicksR;
    int storedTicksB;

    double some_constant;

    ElapsedTime dt;

    double distanceL;
    double distanceR;
    double distanceB;

    double theta;

    double posDegrees;

    double angle;

    double velocity_X;
    double velocity_Y;

    double global_X;
    double global_Y;

    double pos_X;
    double pos_Y;

    double distBet;

    DriveTrain driveTrain;

    public Odometry() {
        some_constant = 50.52;
    }

    @Override
    public boolean run() {
        int TotalTicksL = driveTrain.odomL.getCurrentPosition();
        int TotalTicksR = driveTrain.odomR.getCurrentPosition();
        int TotalTicksB = driveTrain.odomH.getCurrentPosition();


        currentTicksL = (TotalTicksL - storedTicksL);
        currentTicksR = (TotalTicksR - storedTicksR);
        currentTicksB = (TotalTicksB - storedTicksB);

        distanceL = (currentTicksL * some_constant)/dt.seconds();
        distanceR = (currentTicksR * some_constant)/dt.seconds();
        distanceB = (currentTicksB * some_constant)/dt.seconds();

        theta = ((distanceL + distanceR)/distBet);

        posDegrees = theta * dt.seconds() + posDegrees;
        angle = posDegrees*(180/Math.PI);

        velocity_X = ((distanceL + distanceR)/2.0) * Math.cos(theta) - (distanceB - theta) * (2 * Math.sin(theta)); // solves for x distance (contingent on theta being accurate)
        velocity_Y =((distanceL + distanceR)/2.0) * Math.sin(theta) - (distanceB - theta) * (2 * Math.cos(theta)); // solves for y disance (contingent on theta being accurate)

        global_X = velocity_X * Math.cos(posDegrees) - velocity_Y * Math.sin(posDegrees);
        global_Y = velocity_X * Math.sin(posDegrees) + velocity_Y * Math.cos(posDegrees);

        pos_X = velocity_X * dt.seconds() + pos_X;
        pos_Y = velocity_Y * dt.seconds() + pos_Y;

        dt.reset();

        return false;
    }

}
