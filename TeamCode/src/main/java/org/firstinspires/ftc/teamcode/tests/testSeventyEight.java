package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Localization;
import org.firstinspires.ftc.teamcode.utilities.RoboState;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous
public class testSeventyEight extends TritonicsOpMode {

    Localization localization;

    Telemetry telemetry;

    RoboState roboState;

    @Override
    public void runTritonicsOpMode() {

        roboState = new RoboState(0, 0, 0, 0, 0, 0);

        localization = new Localization(sensors, roboState);

        telemetry = FtcDashboard.getInstance().getTelemetry();

        telemetry.addData("roboState", roboState);
        telemetry.addData("roboStatX", roboState.x);
        telemetry.update();

        waitForStart();

        while(opModeIsActive()){
            localization.run();
            roboState = localization.getRoboState();
            telemetry.addData("roboStateX", roboState.x);
            telemetry.addData("roboStateY", roboState.y);
            telemetry.addData("roboStateT", roboState.theta);
            telemetry.update();
        }

    }

}
