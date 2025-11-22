package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class PowerTransfer extends Task{

    Launcher launcher;
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

    State state;

    public PowerTransfer(Launcher launcher, int speed) {
        this.launcher = launcher;
        this.speed = speed;

        state = State.INIT;
    }

    @Override
    public boolean run() {
//        switch(state){
//            case INIT: initOne(); break;
//            case INITTWO: initTwo(); break;
//            case GO: go(); break;
//        }
        if(launcher.ramp.getPower() == 0){
            launcher.ramp.setPower(speed);
        } else {
            launcher.ramp.setPower(0);
        }
        return false;
    }

    @Override
    public boolean end() {
        if (state != State.STOP) {
            stop();
        }
        return true;
    }

    void initOne() {
        lastIntegral = speed*t.seconds() - launcher.ramp.getCurrentPosition();
        dt.reset();
        t.reset();

        state = State.INITTWO;
    }

    void initTwo() {
        integral = speed*t.seconds() - launcher.ramp.getCurrentPosition();
        proportional = speed - (lastIntegral - integral)/dt.seconds();

        state = State.GO;
    }

    void go() {
        integral = speed*t.seconds() - launcher.ramp.getCurrentPosition();
        proportional = (lastIntegral - integral)/dt.seconds();
        derivative = (lastProportional - proportional)/dt.seconds();
        dt.reset();

        launcher.ramp.setPower(integral*i+proportional*p+derivative*d);

        lastIntegral = integral;
        lastProportional = proportional;
    }

    void stop() {
        launcher.ramp.setPower(0);

        state = State.STOP;
    }

}
