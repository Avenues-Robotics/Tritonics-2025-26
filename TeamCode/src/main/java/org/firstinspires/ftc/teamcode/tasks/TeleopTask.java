package org.firstinspires.ftc.teamcode.tasks;

import java.util.function.BooleanSupplier;

public class TeleopTask extends Task{

    Task task;
    BooleanSupplier button;
    boolean toggle;

    enum State {
        RUNNING,
        STARTING,
        STATIC,
        SHUTTINGDOWN
    }

    State state;

    public TeleopTask(Task task, BooleanSupplier button, boolean toggle) {
        this.task = task;
        this.button = button;
        this.toggle = toggle;

        state = State.STATIC;
    }

    @Override
    public boolean run() {
        if(button.getAsBoolean() && state == State.STATIC){
            task.run();
            state = State.RUNNING;
        }

        if(!button.getAsBoolean()) {
            state = State.STATIC;
        }
        return false;
    }

}
