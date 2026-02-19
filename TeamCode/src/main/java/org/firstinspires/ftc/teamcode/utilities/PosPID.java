package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public class PosPID {

    double pCoeff;
    double iCoeff;
    double dCoeff;

    double power;

    double runningIntegral;

    double currPos;
    double prevPos;

    DoubleSupplier pos;

    ElapsedTime dt;

    Telemetry telem;

    public PosPID(double pCoeff, double iCoeff, double dCoeff, DoubleSupplier pos, Telemetry telem){
        this.pCoeff = pCoeff;
        this.iCoeff = iCoeff;
        this.dCoeff = dCoeff;

        currPos = 0;
        prevPos = 0;
        runningIntegral = 0;
        dt = new ElapsedTime();

        this.pos = pos;
        this.telem = telem;
    }

    public void resetIntegral() {
        runningIntegral = 0;
        prevPos = 0;
        dt.reset();
    }

    public double findPower(double target) {
        prevPos = currPos;
        currPos = pos.getAsDouble() - target;

        runningIntegral += currPos * dt.seconds();
        power = pCoeff*currPos + iCoeff*runningIntegral + dCoeff*(prevPos - currPos)/dt.seconds();
        dt.reset();
        return power;
    }

    public double findPower() {
        prevPos = currPos;
        currPos = pos.getAsDouble();

        runningIntegral += currPos * dt.seconds();
        power = pCoeff*currPos + iCoeff*runningIntegral + dCoeff*(prevPos - currPos)/dt.seconds();
        dt.reset();
        return power;
    }

}
