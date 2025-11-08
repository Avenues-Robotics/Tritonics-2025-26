package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task takes two input tasks and runs them in parallel, declaring to be done when
 * either of the two tasks have finished
 */

public class ParallelRaceTask extends Task{

    Task taskOne;
    Task taskTwo;

    public ParallelRaceTask(Task taskOne, Task taskTwo) {
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
    }

    @Override
    public boolean run() {
        return taskOne.run() || taskTwo.run();
    }
}
