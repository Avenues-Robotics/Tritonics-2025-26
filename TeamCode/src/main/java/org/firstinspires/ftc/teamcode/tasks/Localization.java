package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
public class Localization extends Task{

    Sensors sensors;

    RoboState roboState;
    RoboState odom;
    RoboState correctedOdom;
    RoboState tags;
    RoboState offsets;

    LLResult result;

    ElapsedTime dt;
    ElapsedTime timer;

    public static long delay = 50;

    public static double odomPosAcc = 0.1;
    public static double odomRotAcc = 0.1;
    public static double odomVelAcc = 0.1;
    public static double odomAngVelAcc = 0.1;

    public static double limePosAcc = 20;
    public static double limeRotAcc = 5;

    public static double predAcc = 0.1;

    TritonicsOpMode opMode;

    public Localization(Sensors sensors, RoboState roboState, TritonicsOpMode opMode){
        this.roboState = roboState;
        this.sensors = sensors;
        this.opMode = opMode;
        int i = 0;
        sensors.odo.resetPosAndIMU();
        while(sensors.odo.getDeviceStatus() != GoBildaPinpointDriver.DeviceStatus.READY){
            sensors.odo.update();
            opMode.telem.addData("Status", sensors.odo.getDeviceStatus());
            opMode.telem.addData("loop", i);
            opMode.telem.update();
            opMode.telemetry.addData("Status", sensors.odo.getDeviceStatus());
            opMode.telemetry.addData("Loop number", i);
            opMode.telemetry.update();
            i++;
        }
        setOdom(roboState);
        timer = new ElapsedTime();
        correctedOdom = new RoboState();
        offsets = new RoboState();
    }

    public Localization(Sensors sensors, TritonicsOpMode opMode){
        roboState = null;
        this.sensors = sensors;
        this.opMode = opMode;
        timer = new ElapsedTime();
        correctedOdom = new RoboState();
        offsets = new RoboState();
    }

    @Override
    public boolean run() {
        if (dt == null) {
            dt = new ElapsedTime();
        }

        odom = odom();
        tags = tags();

        opMode.telem.addData("tags x", tags.x);
        opMode.telem.addData("tags y", tags.y);
        opMode.telem.addData("tags theta", tags.theta);
        opMode.telem.update();

        correctedOdom.x = odom.x + offsets.x;
        correctedOdom.y = odom.y + offsets.y;
        correctedOdom.theta = odom.theta + offsets.theta;
        correctedOdom.sigmaX = odom.sigmaX;
        correctedOdom.sigmaY = odom.sigmaY;
        correctedOdom.sigmaTheta = odom.sigmaTheta;
        correctedOdom.velX = odom.velX;
        correctedOdom.velY = odom.velY;
        correctedOdom.velTheta = odom.velTheta;
        correctedOdom.sigmaVelX = odom.sigmaVelX;
        correctedOdom.sigmaVelY = odom.sigmaVelY;
        correctedOdom.sigmaVelTheta = odom.sigmaVelTheta;

        roboState = odom;

        offsets.x = roboState.x - odom.x;
        offsets.y = roboState.y - odom.y;
        offsets.theta = roboState.theta - odom.theta;

        if((Math.sqrt(Math.pow(correctedOdom.velX, 2) + Math.pow(correctedOdom.velY, 2)) < 20 && correctedOdom.velTheta < 5) && timer.milliseconds() > 5000) {
            setOdom(roboState);
            timer.reset();
        }

        opMode.telem.addData("x", roboState.x);
        opMode.telem.addData("y", roboState.y);
        opMode.telem.addData("theta", Math.floorMod((int) (roboState.theta), 360));
        dt.reset();
        return false;
    }

    @Override
    public Task reset() {
        if(roboState == null) {
            return new Localization(sensors, opMode);
        }
        return new Localization(sensors, roboState, opMode);
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
        odomOutput.sigmaX = odomPosAcc * dt.seconds();
        odomOutput.sigmaY = odomPosAcc * dt.seconds();
        odomOutput.sigmaTheta = odomRotAcc * dt.seconds();
        odomOutput.sigmaVelX = odomVelAcc * dt.seconds();
        odomOutput.sigmaVelY = odomVelAcc * dt.seconds();
        odomOutput.sigmaVelTheta = odomAngVelAcc * dt.seconds();
        return odomOutput;
    }

    private RoboState tags() {
        RoboState state = new RoboState();
        result = sensors.limelight.getLatestResult();
        if(result.isValid()) {
            Pose3D pose = result.getBotpose();
            state.x = pose.getPosition().x * 100;
            state.y = pose.getPosition().y * 100;
            state.theta = pose.getOrientation().getYaw() - 90;
            state.sigmaX = limePosAcc;
            state.sigmaY = limePosAcc;
            state.sigmaTheta = limeRotAcc;
        }
        return state;
    }

    private RoboState prediction() {
        RoboState state = new RoboState();

        state.x = roboState.x + roboState.velX * dt.seconds();
        state.sigmaX = dt.seconds() * predAcc * Math.sqrt(Math.pow(roboState.x, 2) + Math.pow(roboState.sigmaVelX * dt.seconds(), 2));

        state.y = roboState.y + roboState.velY * dt.seconds();
        state.sigmaY = dt.seconds() * predAcc * Math.sqrt(Math.pow(roboState.y, 2) + Math.pow(roboState.sigmaVelY * dt.seconds(), 2));

        state.theta = roboState.theta + roboState.velTheta * dt.seconds();
        state.sigmaTheta = dt.seconds() * predAcc * Math.sqrt(Math.pow(roboState.theta, 2) + Math.pow(roboState.sigmaVelTheta * dt.seconds(), 2));

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

    private RoboState filter(RoboState odom, RoboState tags) {
        RoboState state = new RoboState();

        state.x = (odom.x*tags.sigmaX + tags.x*odom.sigmaX) / (tags.sigmaX + odom.sigmaX);
        state.sigmaX = 1/((1/tags.sigmaX)+(1/odom.sigmaX));

        state.y = (odom.y*tags.sigmaY + tags.y*odom.sigmaY) / (tags.sigmaY + odom.sigmaY);
        state.sigmaY = 1/((1/tags.sigmaY)+(1/odom.sigmaY));

        double y = (Math.sin(Math.toRadians(odom.theta)) * tags.sigmaTheta + Math.sin(Math.toRadians(tags.theta)) * odom.sigmaTheta) / (tags.sigmaTheta + odom.sigmaTheta);
        double x = (Math.cos(Math.toRadians(odom.theta)) * tags.sigmaTheta + Math.cos(Math.toRadians(tags.theta)) * odom.sigmaTheta) / (tags.sigmaTheta + odom.sigmaTheta);
        state.theta = Math.toDegrees(Math.atan2(y, x));
        state.sigmaTheta = 1/((1/tags.sigmaTheta)+(1/odom.sigmaTheta));


        state.velX = odom.velX;
        state.sigmaVelX = 0.0001;

        state.velY = odom.velY;
        state.sigmaVelY = 0.0001;

        state.velTheta = odom.velTheta;
        state.sigmaVelTheta = 0.0001;

        return state;
    }

    public void setOdom(RoboState roboState) {
        sensors.odo.setPosition(new Pose2D(DistanceUnit.CM, roboState.x, roboState.y, AngleUnit.DEGREES, roboState.theta));
    }

    public void resetOffsets() {
        offsets = new RoboState();
    }
}
