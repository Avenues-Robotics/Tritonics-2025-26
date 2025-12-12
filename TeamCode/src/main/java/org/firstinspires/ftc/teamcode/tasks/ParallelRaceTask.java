package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task takes two input tasks and runs them in parallel, declaring to be done when
 * either of the two tasks have finished
 */

public class ParallelRaceTask extends Task{

    Task taskOne;
    Task taskTwo;

    boolean aaa;

    public ParallelRaceTask(Task taskOne, Task taskTwo) {
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
    }

    public ParallelRaceTask(Task[] tasks) {
        if (tasks.length > 2) {
            taskTwo = tasks[tasks.length-1];
            Task[] tasksOne = new Task[tasks.length-1];
            System.arraycopy(tasks, 0, tasksOne, 0, tasks.length - 1);
            taskOne = new ParallelRaceTask(tasksOne);
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
        if(!aaa) {
            aaa = taskOne.run() || taskTwo.run();
            return aaa;
        }
        return true;
    }

    @Override
    public Task reset() {
        return new ParallelRaceTask(taskOne.reset(), taskTwo.reset());
    }
}
