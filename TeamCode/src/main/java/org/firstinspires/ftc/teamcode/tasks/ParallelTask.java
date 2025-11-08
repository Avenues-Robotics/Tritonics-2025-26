package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task take two input tasks and runs them in parallel, declaring to be done when both
 * tasks have finished
 */

public class ParallelTask extends Task{

    Task taskOne;
    Task taskTwo;

    public ParallelTask(Task taskOne, Task taskTwo) {
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
    }

    @Override
    public boolean run() {
        return taskOne.run() && taskTwo.run();
    }
}
