package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class PowerLauncher extends Task{

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
        INITONE,
        INITTWO,
        GO,
        STOP
    }

    State state;

    public PowerLauncher(Launcher launcher, int speed) {
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
        lastIntegral = speed*t.seconds() - launcher.R.getCurrentPosition();
        dt.reset();
        t.reset();

        state = State.INITTWO;
    }

    void initTwo() {
        integral = speed*t.seconds() - launcher.R.getCurrentPosition();
        proportional = speed - (lastIntegral - integral)/dt.seconds();

        state = State.GO;
    }

    void go() {
        integral = speed*t.seconds() - launcher.R.getCurrentPosition();
        proportional = (lastIntegral - integral)/dt.seconds();
        derivative = (lastProportional - proportional)/dt.seconds();
        dt.reset();

        launcher.R.setPower(integral*i+proportional*p+derivative*d);
        launcher.L.setPower(integral*i+proportional*p+derivative*d);

        lastIntegral = integral;
        lastProportional = proportional;
    }

    void stop() {
        launcher.R.setPower(0);

        state = State.STOP;
    }

}
