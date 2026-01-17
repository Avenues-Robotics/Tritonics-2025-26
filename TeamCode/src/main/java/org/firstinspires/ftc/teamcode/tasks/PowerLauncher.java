package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.utilities.VelPID;

public class PowerLauncher extends Task{

    Launcher launcher;
    double speed;
    double origSpeed;

    public static double p = 2;
    public static double i = 0;
    public static double d = 0;
    public static double f = 0.75;

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

    Telemetry telem;

    public PowerLauncher(Launcher launcher, double speed, Telemetry telem) {
        this.launcher = launcher;
        this.speed = speed;
        this.origSpeed = speed;

        state = State.INIT;
        dt = new ElapsedTime();
        t = new ElapsedTime();

        this.telem = telem;

        velPID = new VelPID(p, i, d, f, () -> this.launcher.R.getCurrentPosition(), telem);
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

    @Override
    public Task reset(){
        return new PowerLauncher(launcher, origSpeed, telem);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
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
