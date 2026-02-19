package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.utilities.PosPID;
import org.firstinspires.ftc.teamcode.utilities.RoboState;

@Config
public class PIDDrive extends Task{

    DriveTrain driveTrain;
    Localization localization;

    PosPID xPID;
    PosPID yPID;
    PosPID thetaPID;

    public static double horizP = 1;
    public static double horizI = 0;
    public static double horizD = 0;

    public static double thetaP = 0.05;
    public static double thetaI = 0;
    public static double thetaD = 0;

    public static double horizToler = 4;
    public static double thetaToler = 2;

    RoboState roboState;

    Pose2D destination;

    public PIDDrive(DriveTrain driveTrain, Localization localization, Pose2D destination) {
        this.driveTrain = driveTrain;
        this.localization = localization;
        this.destination = destination;

        xPID = new PosPID(horizP, horizI, horizD, () -> findError(destination).getX(DistanceUnit.CM), driveTrain.telem);
        yPID = new PosPID(horizP, horizI, horizD, () -> findError(destination).getY(DistanceUnit.CM), driveTrain.telem);
        thetaPID = new PosPID(thetaP, thetaI, thetaD, () -> findError(destination).getHeading(AngleUnit.DEGREES), driveTrain.telem);
    }

    public Pose2D findError(Pose2D destination) {
        roboState = localization.getRoboState();
        return new Pose2D(DistanceUnit.CM,
                (roboState.x - destination.getX(DistanceUnit.CM))*Math.cos(Math.toRadians(-roboState.theta)) - (roboState.y - destination.getY(DistanceUnit.CM))*Math.sin(Math.toRadians(-roboState.theta)),
                (roboState.x - destination.getX(DistanceUnit.CM))*Math.sin(Math.toRadians(-roboState.theta)) + (roboState.y - destination.getY(DistanceUnit.CM))*Math.cos(Math.toRadians(-roboState.theta)),
                AngleUnit.DEGREES,
                - destination.getHeading(AngleUnit.DEGREES) + roboState.theta);
    }

    @Override
    public boolean run() {

        double forward = xPID.findPower();
        double lateral = yPID.findPower();
        double angular = thetaPID.findPower();

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        double fr = (-forward - lateral - angular/1.5) * Math.abs(-forward - lateral - angular/1.5);
        double fl = (-forward + lateral + angular/1.5) * Math.abs(-forward + lateral + angular/1.5);
        double br = (-forward + lateral - angular/1.5) * Math.abs(-forward + lateral - angular/1.5);
        double bl = (-forward - lateral + angular/1.5) * Math.abs(-forward - lateral + angular/1.5);

        // Normalize the values so no wheel power exceeds 100%
        double max = Math.max(Math.max(Math.max(Math.abs(fl), Math.abs(fr)), Math.abs(bl)), Math.abs(br));

        if (max > 1.0) {
            fl /= max;
            fr /= max;
            bl /= max;
            br /= max;
        }

        driveTrain.FL.setPower(fl);
        driveTrain.FR.setPower(fr);
        driveTrain.BL.setPower(bl);
        driveTrain.BR.setPower(br);

        return Math.sqrt(Math.pow(destination.getX(DistanceUnit.CM) - localization.getRoboState().x, 2) + Math.pow(destination.getY(DistanceUnit.CM) - localization.getRoboState().y, 2)) < horizToler && destination.getHeading(AngleUnit.DEGREES) - localization.getRoboState().theta < thetaToler;
    }

    @Override
    public Task reset() {
        return new PIDDrive(driveTrain, localization, destination);
    }

}
