package org.firstinspires.ftc.teamcode.tasks;

public class EndTask extends Task{

    Task task;

    public EndTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean run() {
        return task.end();
    }

}
