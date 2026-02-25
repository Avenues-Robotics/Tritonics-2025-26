package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
@Autonomous
public class testfourtytwothousandtwentyfive extends TritonicsOpMode {

    Pose2D pose;

    @Override
    public void runTritonicsOpMode() {

        waitForStart();

        while(opModeIsActive()) {
            sensors.odo.update();
            pose = sensors.odo.getPosition();
            telemetry.addData("x", pose.getX(DistanceUnit.CM));
            telemetry.addData("y", pose.getY(DistanceUnit.CM));
            telemetry.addData("theta", pose.getHeading(AngleUnit.DEGREES));
            telemetry.update();
        }

    }

}
