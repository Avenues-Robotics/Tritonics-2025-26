package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Sensors {

    public IMU imu; public static String imuConfig = "imu";
    public Limelight3A limelight; public static String limelightConfig = "limelight";

    public Telemetry telem;

    public boolean redSide;

    public Sensors(LinearOpMode opMode, boolean redSide) {
        this.redSide = redSide;
        imu = opMode.hardwareMap.get(IMU.class, imuConfig);
        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot (
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP)
        ));

        limelight = opMode.hardwareMap.get(Limelight3A.class, limelightConfig);

        if(this.redSide) {
            limelight.pipelineSwitch(0);
        } else {
            limelight.pipelineSwitch(1);
        }

        limelight.start();

        telem = FtcDashboard.getInstance().getTelemetry();
    }

}
