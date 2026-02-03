package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.RoboState;

public class Localization extends Task{

    Sensors sensors;

    RoboState roboState;

    ElapsedTime dt;

    public Localization(Sensors sensors){
        this.sensors = sensors;
    }

    @Override
    public boolean run() {
        if (dt == null) {
            dt = new ElapsedTime();
        }
        roboState = filter(odom(), tags(), prediction());
        setOdom(roboState);
        dt.reset();
        return false;
    }

    @Override
    public Task reset() {
        return new Localization(sensors);
    }

    public RoboState getRoboState(){
        return roboState;
    }

    private RoboState odom() {
        sensors.odo.update();
        RoboState odomOutput = new RoboState();
        Pose2D pos = sensors.odo.getPosition();
        odomOutput.x = pos.getX(DistanceUnit.CM);
        odomOutput.y = pos.getY(DistanceUnit.CM);
        odomOutput.theta = pos.getHeading(AngleUnit.DEGREES);
        odomOutput.velX = sensors.odo.getVelX(DistanceUnit.CM);
        odomOutput.velY = sensors.odo.getVelY(DistanceUnit.CM);
        odomOutput.velTheta = sensors.odo.getHeadingVelocity(UnnormalizedAngleUnit.DEGREES);
        return odomOutput;
    }

    private RoboState tags() {
        RoboState state = new RoboState();
        LLResult result = sensors.limelight.getLatestResult();
        if(result.isValid()) {
            Pose3D pose = result.getBotpose();
            state.x = pose.getPosition().x;
            state.y = pose.getPosition().y;
            state.theta = pose.getOrientation().getYaw();
        }
        return state;
    }

    private RoboState prediction() {
        RoboState state = new RoboState();

        state.x = roboState.x + roboState.velX * dt.seconds();
        state.sigmaX = Math.sqrt(Math.pow(roboState.x, 2) + Math.pow(roboState.sigmaVelX * dt.seconds(), 2));

        state.y = roboState.y + roboState.velY * dt.seconds();
        state.sigmaY = Math.sqrt(Math.pow(roboState.y, 2) + Math.pow(roboState.sigmaVelY * dt.seconds(), 2));

        state.theta = roboState.theta + roboState.velTheta * dt.seconds();
        state.sigmaTheta = Math.sqrt(Math.pow(roboState.theta, 2) + Math.pow(roboState.sigmaVelTheta * dt.seconds(), 2));

        state.velX = roboState.velX;
        state.velY = roboState.velY;
        state.velTheta = roboState.velTheta;
        state.sigmaVelX = 100000000;
        state.sigmaVelY = 100000000;
        state.sigmaVelTheta = 100000000;

        return state;
    }

    private RoboState filter(RoboState odom, RoboState tags, RoboState prediction) {
        RoboState state = new RoboState();

        state.x = (odom.x*tags.sigmaX*prediction.sigmaX + tags.x*odom.sigmaX*prediction.sigmaX + prediction.x*odom.sigmaX*tags.sigmaX)/(tags.sigmaX*prediction.sigmaX + odom.sigmaX*prediction.sigmaX + odom.sigmaX*tags.sigmaX);
        state.sigmaX = 1/((1/odom.sigmaX) + (1/tags.sigmaX) + (1/prediction.sigmaX));

        state.y = (odom.y*tags.sigmaY*prediction.sigmaY + tags.y*odom.sigmaY*prediction.sigmaY + prediction.y*odom.sigmaY*tags.sigmaY)/(tags.sigmaY*prediction.sigmaY + odom.sigmaY*prediction.sigmaY + odom.sigmaY*tags.sigmaY);
        state.sigmaY = 1/((1/odom.sigmaY) + (1/tags.sigmaY) + (1/prediction.sigmaY));

        state.theta = (odom.theta*tags.sigmaTheta*prediction.sigmaTheta + tags.theta*odom.sigmaTheta*prediction.sigmaTheta + prediction.theta*odom.sigmaTheta*tags.sigmaTheta)/(tags.sigmaTheta*prediction.sigmaTheta + odom.sigmaTheta*prediction.sigmaTheta + odom.sigmaTheta*tags.sigmaTheta);
        state.sigmaTheta = 1/((1/odom.sigmaTheta) + (1/tags.sigmaTheta) + (1/prediction.sigmaTheta));

        state.velX = (odom.velX*tags.sigmaVelX*prediction.sigmaVelX + tags.velX*odom.sigmaVelX*prediction.sigmaVelX + prediction.velX*odom.sigmaVelX*tags.sigmaVelX)/(tags.sigmaVelX*prediction.sigmaVelX + odom.sigmaVelX*prediction.sigmaVelX + odom.sigmaVelX*tags.sigmaVelX);
        state.sigmaVelX = 1/((1/odom.sigmaVelX) + (1/tags.sigmaVelX) + (1/prediction.sigmaVelX));

        state.velY = (odom.velY*tags.sigmaVelY*prediction.sigmaVelY + tags.velY*odom.sigmaVelY*prediction.sigmaVelY + prediction.velY*odom.sigmaVelY*tags.sigmaVelY)/(tags.sigmaVelY*prediction.sigmaVelY + odom.sigmaVelY*prediction.sigmaVelY + odom.sigmaVelY*tags.sigmaVelY);
        state.sigmaVelY = 1/((1/odom.sigmaVelY) + (1/tags.sigmaVelY) + (1/prediction.sigmaVelY));

        state.velTheta = (odom.velTheta*tags.sigmaVelTheta*prediction.sigmaVelTheta + tags.velTheta*odom.sigmaVelTheta*prediction.sigmaVelTheta + prediction.velTheta*odom.sigmaVelTheta*tags.sigmaVelTheta)/(tags.sigmaVelTheta*prediction.sigmaVelTheta + odom.sigmaVelTheta*prediction.sigmaVelTheta + odom.sigmaVelTheta*tags.sigmaVelTheta);
        state.sigmaVelTheta = 1/((1/odom.sigmaVelTheta) + (1/tags.sigmaVelTheta) + (1/prediction.sigmaVelTheta));

        return state;
    }

    private void setOdom(RoboState roboState) {
        sensors.odo.setPosition(new Pose2D(DistanceUnit.CM, roboState.x, roboState.y, AngleUnit.DEGREES, roboState.theta));
    }

}
