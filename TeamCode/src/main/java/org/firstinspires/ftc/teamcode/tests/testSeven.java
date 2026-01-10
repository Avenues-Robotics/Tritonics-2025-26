package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;

@Autonomous
public class testSeven extends LinearOpMode {

    DriveTrain driveTrain;
    Sensors sensors;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this);
        sensors = new Sensors(this);

        waitForStart();

        while(opModeIsActive()) {
            driveTrain.telem.addData("L", driveTrain.odomL.getCurrentPosition());
            driveTrain.telem.addData("R", driveTrain.odomR.getCurrentPosition());
            driveTrain.telem.addData("H", driveTrain.odomH.getCurrentPosition());
            driveTrain.telem.update();
        }

    }

}
