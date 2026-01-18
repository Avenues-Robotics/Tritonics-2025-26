package org.firstinspires.ftc.teamcode.tasks;

import java.util.function.BooleanSupplier;

public class TeleopTask extends Task{

    Task task;
    BooleanSupplier button;
    boolean toggled;
    boolean toggleable;

    boolean currButton;
    boolean prevButton;

    enum State {
        RUNNING,
        STARTING,
        STATIC,
        SHUTTINGDOWN
    }

    State state;

    public TeleopTask(Task task, BooleanSupplier button, boolean toggleable) {
        this.task = task;
        this.button = button;
        this.toggleable = toggleable;

        toggled = false;
        state = State.STATIC;
    }

    @Override
    public boolean run() {
        currButton = button.getAsBoolean();

        if(toggleable) {
            if (currButton && !prevButton) {
                toggled = true;
            }

            if (toggled) {
                toggled = !task.run();
                if (toggled) {
                    toggled = !(currButton && !prevButton);
                }
                if (!toggled) {
                    task = task.reset();
                }
            }
        } else {
            if(currButton) {
                if(task.run()) {
                    task = task.reset();
                }
            } else {
                task.end();
            }
        }

        prevButton = currButton;
        return false;
    }

}
