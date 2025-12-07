package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.Launcher;

public class PowerIntake extends Task{

    Intake intake;
    double speed;

    public PowerIntake(Intake intake, double speed) {
        this.intake = intake;
        this.speed = speed;
    }

    @Override
    public boolean run() {
        intake.motor.setPower(speed);
        return true;
    }

    @Override
    public boolean end() {
        intake.motor.setPower(0);
        return true;
    }

    @Override
    public Task reset() {
        return new PowerIntake(intake, speed);
    }

}
