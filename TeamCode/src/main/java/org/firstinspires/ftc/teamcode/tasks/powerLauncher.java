package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class powerLauncher extends Task{

    Launcher launcher;
    int speed;
    int error;

    double derivative;
    double proportional;
    double integral;

    double lastIntegral;
    double lastProportional;

    public static double p;
    public static double i;
    public static double d;

    ElapsedTime dt;

    enum State {
        INITONE,
        INITTWO,
        GO,
        STOP
    }

    State state;

    public powerLauncher(Launcher launcher, int speed) {
        this.launcher = launcher;
        this.speed = speed;

        state = State.INITONE;
    }

    @Override
    public boolean run() {
        switch(state){
            case INITONE: initOne(); break;
            case INITTWO: initTwo(); break;
            case GO: go(); break;
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
        lastIntegral = launcher.R.getCurrentPosition();
        dt.reset();

        state = State.INITTWO;
    }

    void initTwo() {
        integral = launcher.R.getCurrentPosition();
        proportional = (lastIntegral - integral)/dt.seconds();

        state = State.GO;
    }

    void go() {
        integral = launcher.R.getCurrentPosition();
        proportional = (lastIntegral - integral)/dt.seconds();
        derivative = (lastProportional - proportional)/dt.seconds();
        dt.reset();

        launcher.R.setPower(integral*i+proportional*p+derivative*d);

        lastIntegral = integral;
        lastProportional = proportional;
    }

    void stop() {
        launcher.R.setPower(0);

        state = State.STOP;
    }

}
