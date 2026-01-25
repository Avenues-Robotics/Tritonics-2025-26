package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "test2")
public class testTwo extends LinearOpMode {


    double b; // "some height"
    double m; // slop
    double v; // velocity

    @Override

    public void runOpMode() {

        waitForStart(); // Bro noah is a fool

        // Perform calculations - something  noah cant do
        double calculation = -b * Math.log(1 + m) + (v / m);

        telemetry.addData("Calculation", calculation); //gpt said addData was better than Println
        telemetry.update(); // did gpt write this code? GPT code is unreliable and doesn't build your skills
    }



}
