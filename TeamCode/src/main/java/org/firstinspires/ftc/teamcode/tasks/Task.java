package org.firstinspires.ftc.teamcode.tasks;

/*
 * This is the base class of the task system, every task is something that the robot may
 * do and is accomplished by a run function that is called every cycle. The run function
 * returns true when the task is finished and false otherwise. These tasks can then be
 * assembled using "SeriesTask.java," "ParallelTask.java," and "ParallelRaceTask.java"
 * into more complex tasks that the robot may actually do.
 */

public class Task {

    public Task() {}

    public boolean run() {
        return true;
    }

}
