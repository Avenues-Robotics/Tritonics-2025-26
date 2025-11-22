package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.utilities.VelPID;

public class PowerLauncher extends Task{

    Launcher launcher;
    double speed;

    public static double p = 1;
    public static double i = 0;
    public static double d = 0;

    ElapsedTime dt;
    ElapsedTime t;

    enum State {
        INIT,
        INITTWO,
        GO,
        STOP
    }

    State state;

    VelPID velPID;

    public PowerLauncher(Launcher launcher, double speed) {
        this.launcher = launcher;
        this.speed = speed;

        state = State.INIT;
        dt = new ElapsedTime();
        t = new ElapsedTime();

        velPID = new VelPID(p, i, d, () -> this.launcher.R.getCurrentPosition());
    }

    @Override
    public boolean run() {
        switch(state){
            case INIT: initOne(); break;
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
        velPID.init(speed);

        state = State.INITTWO;
    }

    void initTwo() {
        velPID.initTwo(speed);

        state = State.GO;
    }

    void go() {
        launcher.R.setPower(velPID.findPower(speed));
        launcher.L.setPower(launcher.R.getPower());
    }

    void stop() {
        launcher.R.setPower(0);
        launcher.L.setPower(0);

        state = State.STOP;
    }

}
