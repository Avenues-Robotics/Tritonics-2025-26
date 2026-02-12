package org.firstinspires.ftc.teamcode.utilities;

public class RoboState {
    public double x; public double sigmaX;
    public double y; public double sigmaY;
    public double theta; public double sigmaTheta;

    public double velX; public double sigmaVelX;
    public double velY; public double sigmaVelY;
    public double velTheta; public double sigmaVelTheta;

    public RoboState(){
        x = 0;
        y = 0;
        theta = 0;
        velX = 0;
        velY = 0;
        velTheta = 0;

        sigmaX = 100000;
        sigmaY = 100000;
        sigmaTheta = 100000;
        sigmaVelX = 100000;
        sigmaVelY = 100000;
        sigmaVelTheta = 100000;
    }

    public RoboState(double x, double y, double theta, double velX, double velY, double velTheta){
        this.x = x;
        this.y = y;
        this.theta = theta;
        this.velX = velX;
        this.velY = velY;
        this.velTheta = velTheta;

        sigmaX = 0.001;
        sigmaY = 0.001;
        sigmaTheta = 0.001;
        sigmaVelX = 0.001;
        sigmaVelY = 0.001;
        sigmaVelTheta = 0.001;
    }
}
