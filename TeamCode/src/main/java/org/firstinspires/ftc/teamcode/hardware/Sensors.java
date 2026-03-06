package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.utilities.Prism;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

@Config
public class Sensors {

    public IMU imu; public static String imuConfig = "imu";
    public Limelight3A limelight; public static String limelightConfig = "limelight";
    public GoBildaPinpointDriver odo; public static String odoConfig = "odo";
    public Prism prism; public static int prismConfig = 1;

    public ColorSensor bottomRightCS; public static String bottomRightCSConfig = "bottomRightCS";
    public ColorSensor bottomCenterCS; public static String bottomCenterCSConfig = "bottomCenterCS";
    public ColorSensor bottomLeftCS; public static String bottomLeftCSConfig = "bottomLeftCS";
    public ColorSensor topRightCS; public static String topRightCSConfig = "topRightCS";
    public ColorSensor topLeftCS; public static String topLeftCSConfig = "topLeftCS";

    public Telemetry telem;

    public static double xOffset = 8.57;
    public static double yOffset = 8.57;

    public Sensors(TritonicsOpMode opMode) {
//        imu = opMode.hardwareMap.get(IMU.class, imuConfig);
//        imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot (
//                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
//                RevHubOrientationOnRobot.UsbFacingDirection.UP)
//        ));
        limelight = opMode.hardwareMap.get(Limelight3A.class, limelightConfig);

        limelight.pipelineSwitch(0);

        limelight.start();

        odo = opMode.hardwareMap.get(GoBildaPinpointDriver.class, odoConfig);
        odo.setOffsets(xOffset, yOffset, DistanceUnit.CM);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        prism = new Prism(opMode.hardwareMap, prismConfig);
        prism.off();

        bottomRightCS = opMode.hardwareMap.get(ColorSensor.class, bottomRightCSConfig);
        bottomCenterCS = opMode.hardwareMap.get(ColorSensor.class, bottomCenterCSConfig);
        bottomLeftCS = opMode.hardwareMap.get(ColorSensor.class, bottomLeftCSConfig);
        topRightCS = opMode.hardwareMap.get(ColorSensor.class, topRightCSConfig);
        topLeftCS = opMode.hardwareMap.get(ColorSensor.class, topLeftCSConfig);

        telem = FtcDashboard.getInstance().getTelemetry();
    }

}
