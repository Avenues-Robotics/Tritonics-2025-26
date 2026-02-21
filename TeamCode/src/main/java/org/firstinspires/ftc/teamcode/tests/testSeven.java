package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Autonomous
public class testSeven extends TritonicsOpMode {

    @Override
    public void runTritonicsOpMode() {
        waitForStart();

        while(opModeIsActive()) {
            driveTrain.telem.addData("L", driveTrain.odomL.getCurrentPosition());
            driveTrain.telem.addData("R", driveTrain.odomR.getCurrentPosition());
            driveTrain.telem.addData("H", driveTrain.odomH.getCurrentPosition());
            driveTrain.telem.update();
        }

    }

}
