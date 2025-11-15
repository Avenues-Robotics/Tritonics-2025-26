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

        switch(state){
            case STATIC:
                if(button.getAsBoolean()){
                    state = State.STARTING;
                }
                break;
            case STARTING:
                task.run();
                if(!button.getAsBoolean()) {
                    state = State.RUNNING;
                }
            case RUNNING:
                if(task.run()){
                    state = State.STATIC;
                    task.reset();
                }
                if(button.getAsBoolean() && toggle) {
                    state = State.SHUTTINGDOWN;
                    task.reset();
                }
                break;
            case SHUTTINGDOWN:
                if(!button.getAsBoolean()) {
                    state = State.STATIC;
                }
        }

        return false;
    }

}
