package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

public class Odometry extends Task{

    DriveTrain driveTrain;

    double ticksToCm;

    double dOne;
    double dTwo;
    double dThree;

    double odomLCurr;
    double odomRCurr;
    double odomHCurr;

    double odomLSpeed;
    double odomRSpeed;
    double odomHSpeed;

    double odomLPrev;
    double odomRPrev;
    double odomHPrev;

    double omega;
    double xDot;
    double yDot;

    double xDotGlobal;
    double yDotGlobal;

    double theta;
    double x;
    double y;

    ElapsedTime dt;

    public Odometry(DriveTrain driveTrain, double startingTheta, double startingX, double startingY) {
        this.driveTrain = driveTrain;

        theta = startingTheta;
        x = startingX;
        y = startingY;

        ticksToCm = 10; /// Fix these variables
        dOne = 23; /// Fix these variables
        dTwo = 5; /// Fix these variables
        dThree = 41; /// Fix these variables

        dt = new ElapsedTime();
    }

    @Override
    public boolean run() {

        odomLCurr = ticksToCm * driveTrain.odomL.getCurrentPosition();
        odomRCurr = ticksToCm * driveTrain.odomR.getCurrentPosition();
        odomHCurr = ticksToCm * driveTrain.odomH.getCurrentPosition();

        try {

            odomLSpeed = (odomLPrev - odomLCurr) / dt.seconds();
            odomRSpeed = (odomRPrev - odomRCurr) / dt.seconds();
            odomHSpeed = (odomHPrev - odomHCurr) / dt.seconds();

        } catch (NullPointerException e) {

            odomLSpeed = 0; odomRSpeed = 0; odomHSpeed = 0;
            driveTrain.telem.addData("odomHPrev", odomHPrev);

        }

        omega = -(odomLSpeed - odomRSpeed)/(dOne + dTwo);
        yDot = -odomRSpeed + dTwo * (odomLSpeed - odomRSpeed)/(dOne + dTwo);
        xDot = -odomHSpeed + dThree * (odomLSpeed - odomRSpeed)/(dOne + dTwo);

        theta = theta + omega * dt.seconds();

        xDotGlobal = xDot * Math.cos(theta) - yDot * Math.sin(theta);
        yDotGlobal = xDot * Math.sin(theta) + yDot * Math.cos(theta);

        x = x + xDotGlobal * dt.seconds();
        y = y + yDotGlobal * dt.seconds();

        odomLPrev = odomLCurr;
        odomRPrev = odomRCurr;
        odomHPrev = odomHCurr;

        dt.reset();

        return false;

    }

    // Returns (x, y, theta) in world frame and degrees
    public double[] getPosition() {
        return new double[]{x, y, theta * 180 / Math.PI};
    }

    // Returns (xDot, yDot, omega) in world frame and degrees per second
    public double[] getVelocity() {
        return new double[]{xDotGlobal, yDotGlobal, omega * 180 / Math.PI};
    }

}
