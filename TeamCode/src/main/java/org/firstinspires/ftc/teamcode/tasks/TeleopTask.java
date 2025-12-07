package org.firstinspires.ftc.teamcode.tasks;

import java.util.function.BooleanSupplier;

public class TeleopTask extends Task{

    Task task;
    BooleanSupplier button;
    boolean toggled;

    boolean currButton;
    boolean prevButton;

    enum State {
        RUNNING,
        STARTING,
        STATIC,
        SHUTTINGDOWN
    }

    State state;

    public TeleopTask(Task task, BooleanSupplier button) {
        this.task = task;
        this.button = button;

        toggled = false;
        state = State.STATIC;
    }

    @Override
    public boolean run() {
        currButton = button.getAsBoolean();

        if(currButton && !prevButton){
            toggled = true;
        }

        if(toggled) {
            toggled = !task.run();
            if(!toggled) {
                task = task.reset();
            }
        }

        prevButton = currButton;
        return false;
    }

}
