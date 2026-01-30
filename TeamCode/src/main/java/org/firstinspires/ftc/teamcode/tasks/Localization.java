package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.hardware.limelightvision.LLResult;

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

    public Localization(Sensors sensors){
        this.sensors = sensors;
    }

    @Override
    public boolean run() {
        return false;
    }

    @Override
    public boolean end() {
        return true;
    }

    @Override
    public Task reset() {
        return new Localization(sensors);
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

    private void setOdom(RoboState roboState) {
        sensors.odo.setPosition(new Pose2D(DistanceUnit.CM, roboState.x, roboState.y, AngleUnit.DEGREES, roboState.theta));
    }

}
