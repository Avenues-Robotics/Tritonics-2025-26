package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.hardware.Servo;

public class RotateServo extends Task{

    Servo servo;
    double position;

    public RotateServo(double position, Servo servo){
        this.servo = servo;
        this.position = position;
    }

    @Override
    public boolean run() {
        servo.setPosition(position);
        return true;
    }

    @Override
    public Task reset() {
        return new RotateServo(position, servo);
    }

}
