package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Timer extends Task{

    ElapsedTime time;

    double threshold;

    enum State {
        INIT,
        TIMING
    }

    State state;

    public Timer(double time) {
        state = State.INIT;
        threshold = time;
    }

    @Override
    public boolean run() {
        if(state == State.INIT) {
            init();
        }
        return time.milliseconds() >= threshold;
    }

    @Override
    public Task reset() {
        return new Timer(threshold);
    }

    void init() {
        time = new ElapsedTime();
        state = State.TIMING;
    }
}
