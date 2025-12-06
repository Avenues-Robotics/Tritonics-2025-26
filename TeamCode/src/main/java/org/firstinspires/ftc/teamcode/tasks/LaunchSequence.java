package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.Launcher;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.utilities.VelPID;


public class LaunchSequence extends Task {

    Launcher launcher;
    Intake intake;
    int range;
    public static double closeSpeed = 0.1;
    public static double middleSpeed = 0.1;
    public static double farSpeed = 0.1;
    public static double rightServoUp = .5;
    public static double rightServoDown = 0;
    public static double leftServoUp = .23;
    public static double leftServoDown = 0;
    public static double servoWait = 1;
    private ElapsedTime servoWaitET = new ElapsedTime();

    enum State {
        INIT,
        SPINUP,
        SERVO_1,
        SERVO_2,
        RESET,
        STOP
    }

    State state;
    VelPID velPID;

    public LaunchSequence(Intake intake, Launcher launcher, int range, Telemetry telem) {
        this.launcher = launcher;
        this.intake = intake;
        this.range = range;

        state = State.INIT;

        velPID = new VelPID(PowerLauncher.p, PowerLauncher.i, PowerLauncher.d, PowerLauncher.f, () -> this.launcher.R.getCurrentPosition(), telem);
    }

    @Override
    public boolean run() {
        switch(state){
            case INIT: init(); break;
            case SPINUP: spinup(); break;
            case SERVO_1: servo_1(); break;
            case SERVO_2: servo_2();break;
            case RESET: reset(); break;
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
    void init() {
        velPID.init(middleSpeed);

        state = State.SPINUP;
    }
    void spinup() {
        if (range == 1) {
            launcher.R.setPower(velPID.findPower(closeSpeed));
            launcher.L.setPower(launcher.R.getPower());
        }
        if (range == 2) {
            launcher.R.setPower(velPID.findPower(middleSpeed));
            launcher.L.setPower(launcher.R.getPower());
        }
        if (range == 3) {
            launcher.R.setPower(velPID.findPower(farSpeed));
            launcher.L.setPower(launcher.R.getPower());
        }
        launcher.ramp.setPower(-1);
        intake.motor.setPower(.5);
        servoWaitET.reset();
        state = State.SERVO_1;
    }
    void servo_1() {
        intake.right.setPosition(rightServoUp);

        if (servoWaitET.seconds() > servoWait) {
            servoWaitET.reset();
            state = State.SERVO_2;
        }
    }
    void servo_2() {
        intake.left.setPosition(leftServoUp);

        if (servoWaitET.seconds() > servoWait) {
            state = State.RESET;
        }

    }
    void stop() {
        launcher.R.setPower(0);
        launcher.L.setPower(0);

        state = State.STOP;
    }

}
