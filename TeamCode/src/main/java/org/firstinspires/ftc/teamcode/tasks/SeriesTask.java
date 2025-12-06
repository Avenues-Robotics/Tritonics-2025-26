package org.firstinspires.ftc.teamcode.tasks;

/*
 * This task takes two input tasks and runs them in series, one after the other
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SeriesTask extends Task{

    Task taskOne;
    Task taskTwo;

    public SeriesTask(Task taskOne, Task taskTwo) {
        this.taskOne = taskOne;
        this.taskTwo = taskTwo;
    }

    @Override
    public boolean run() {
        if(taskOne.run()) {
            return taskTwo.run();
        }
        return false;
    }

}
