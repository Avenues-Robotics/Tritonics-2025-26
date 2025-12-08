package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task take two input tasks and runs them in parallel, declaring to be done when both
 * tasks have finished
 */

public class ParallelTask extends Task{

    Task taskOne;
    Task taskTwo;

    boolean ahh;
    boolean aah;

    public ParallelTask(Task taskOne, Task taskTwo) {
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
    }

    public ParallelTask(Task[] tasks) {
        if (tasks.length > 2) {
            taskTwo = tasks[tasks.length-1];
            Task[] tasksOne = new Task[tasks.length-1];
            System.arraycopy(tasks, 0, tasksOne, 0, tasks.length - 1);
            taskOne = new ParallelTask(tasksOne);
        } else if (tasks.length == 2) {
            taskOne = tasks[0];
            taskTwo = tasks[1];
        } else if (tasks.length == 1) {
            taskOne = new Task();
            taskTwo = tasks[0];
        } else {
            taskOne = new Task();
            taskTwo = new Task();
        }
    }

    @Override
    public boolean run() {
        ahh = taskOne.run();
        aah = taskTwo.run();
        return ahh && aah;
    }

    @Override
    public Task reset() {
        return new ParallelTask(taskOne.reset(), taskTwo.reset());
    }
}
