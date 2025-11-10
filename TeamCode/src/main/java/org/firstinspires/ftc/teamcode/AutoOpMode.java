package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Sensors;
import org.firstinspires.ftc.teamcode.tasks.Drive;
import org.firstinspires.ftc.teamcode.tasks.ParallelRaceTask;
import org.firstinspires.ftc.teamcode.tasks.SeriesTask;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.firstinspires.ftc.teamcode.tasks.Timer;

@Config
@Autonomous(name = "Tritonics Autonomous")
public class AutoOpMode extends LinearOpMode {

    DriveTrain driveTrain;
    Sensors sensors;

    public static double ASpeed = 0.8;
    public static double ADist = 120;
    public static double ADeg = 0;
    Drive ADrive;

    public static double BSpeed = 0.5;
    public static double BDist = 35;
    public static double BDeg = 90;
    Drive BDrive;

    public static double CSpeed = 0.8;
    public static double CDist = 120;
    public static double CDeg = -110;
    Drive CDrive;

    public static double DSpeed = 0.8;
    public static double DDist = 100;
    public static double DDeg = 60;
    Drive DDrive;

    public static double ESpeed = 0.8;
    public static double EDist = 40;
    public static double EDeg = 0;
    Drive EDrive;

    public static double FSpeed = 1;
    public static double FDist = 60;
    public static double FDeg = 0;
    Drive FDrive;

    public static double GSpeed = 1;
    public static double GDist = 60;
    public static double GDeg = 0;
    Drive GDrive;

    public static double HSpeed = 1;
    public static double HDist = 60;
    public static double HDeg = 0;
    Drive HDrive;

    public static double ISpeed = 1;
    public static double IDist = 60;
    public static double IDeg = 0;
    Drive IDrive;

    public static double JSpeed = 1;
    public static double JDist = 60;
    public static double JDeg = 0;
    Drive JDrive;

    Task auto;

    @Override
    public void runOpMode() {

        driveTrain = new DriveTrain(this, true);
        sensors = new Sensors(this);

        ADrive = new Drive(driveTrain, sensors, ASpeed, ADist, ADeg);
        BDrive = new Drive(driveTrain, sensors, BSpeed, BDist, BDeg);
        CDrive = new Drive(driveTrain, sensors, CSpeed, CDist, CDeg);
        DDrive = new Drive(driveTrain, sensors, DSpeed, DDist, DDeg);
        EDrive = new Drive(driveTrain, sensors, ESpeed, EDist, EDeg);
        FDrive = new Drive(driveTrain, sensors, FSpeed, FDist, FDeg);
        GDrive = new Drive(driveTrain, sensors, GSpeed, GDist, GDeg);
        HDrive = new Drive(driveTrain, sensors, HSpeed, HDist, HDeg);
        IDrive = new Drive(driveTrain, sensors, ISpeed, IDist, IDeg);
        JDrive = new Drive(driveTrain, sensors, JSpeed, JDist, JDeg);

        auto = new SeriesTask(
                new SeriesTask(new SeriesTask(new SeriesTask(ADrive, new ParallelRaceTask(BDrive,
                new Timer(600))), new SeriesTask(CDrive, DDrive)), EDrive), new SeriesTask(
                new SeriesTask(new SeriesTask(FDrive, GDrive), new SeriesTask(HDrive, IDrive)),
                JDrive)
        );

        waitForStart();

        while(opModeIsActive()) {
            auto.run();
        }
    }
}
