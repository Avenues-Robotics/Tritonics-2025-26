package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class PowerIntake extends Task{

    Intake intake;
    int speed;

    double derivative;
    double proportional;
    double integral;

    double lastIntegral;
    double lastProportional;

    public static double p;
    public static double i;
    public static double d;

    ElapsedTime dt;
    ElapsedTime t;

    enum State {
        INIT,
        INITTWO,
        GO,
        STOP
    }

    PowerTransfer.State state;

    public PowerIntake(Intake intake, int speed) {
        this.intake = intake;
        this.speed = speed;

        state = PowerTransfer.State.INIT;
    }

    @Override
    public boolean run() {
//        switch(state){
//            case INIT: initOne(); break;
//            case INITTWO: initTwo(); break;
//            case GO: go(); break;
//        }
        if(intake.motor.getPower() == 0){
            intake.motor.setPower(1);
        } else {
            intake.motor.setPower(0);
        }
        return false;
    }

}
