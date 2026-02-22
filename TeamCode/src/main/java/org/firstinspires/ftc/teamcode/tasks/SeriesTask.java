package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task takes two input tasks and runs them in series, one after the other
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SeriesTask extends Task{

    Task taskOne;
    Task taskTwo;

    boolean taskOneDone;

    public SeriesTask(Task taskOne, Task taskTwo) {
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
        taskOneDone = false;
    }

    public SeriesTask(Task[] tasks) {
        if (tasks.length > 2) {
            taskTwo = tasks[tasks.length-1];
            Task[] tasksOne = new Task[tasks.length-1];
            System.arraycopy(tasks, 0, tasksOne, 0, tasks.length - 1);
            taskOne = new SeriesTask(tasksOne);
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
        if(!taskOneDone){
            taskOneDone = taskOne.run();
        }
        if(taskOneDone) {
            return taskTwo.run();
        }
        return false;
    }

    @Override
    public Task reset() {
        return new SeriesTask(taskOne.reset(), taskTwo.reset());
    }

}
