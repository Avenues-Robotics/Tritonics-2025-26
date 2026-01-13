package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Odometry;

@Autonomous
public class testSix extends LinearOpMode {

    Sensors sensors;
    DriveTrain driveTrain;

    Odometry odometry;

    Telemetry telemetry;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        sensors = new Sensors(this, true);

        odometry = new Odometry(driveTrain, 0, 0, 0);

        telemetry = FtcDashboard.getInstance().getTelemetry();

        waitForStart();

        while(opModeIsActive()) {
            odometry.run();
            telemetry.addData("x", odometry.getPosition()[0]);
            telemetry.addData("y", odometry.getPosition()[1]);
            telemetry.addData("theta", odometry.getPosition()[2]);
            telemetry.update();
        }

    }

}
