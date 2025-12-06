package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.IntSupplier;

public class VelPID {

    double pCoeff;
    double iCoeff;
    double dCoeff;
    double fCoeff;

    IntSupplier globalPos;
    IntSupplier pos;

    double p;
    double i;
    double d;

    double pLast;
    double iLast;

    ElapsedTime t;
    ElapsedTime dt;

    int initPos;

    Telemetry telem;

    public VelPID(double pCoeff, double iCoeff, double dCoeff, double fCoeff, IntSupplier pos, Telemetry telem){
        this.pCoeff = pCoeff;
        this.iCoeff = iCoeff;
        this.dCoeff = dCoeff;
        this.fCoeff = fCoeff;

        this.globalPos = pos;

        t = new ElapsedTime();
        dt = new ElapsedTime();

        this.telem = telem;
    }

    //vel is in ticks/millisecond
    public void init(double vel) {
        initPos = globalPos.getAsInt();
        pos = () -> globalPos.getAsInt() - initPos;
        t.reset();
        iLast = pos.getAsInt() - vel * t.milliseconds();
        dt.reset();
    }

    public void initTwo(double vel) {
        i = pos.getAsInt() - vel * t.milliseconds();
        pLast = (iLast - i)/dt.milliseconds();
        dt.reset();
        iLast = i;
    }

    public double findPower(double vel) {
        i = pos.getAsInt() - vel * t.milliseconds();
        p = (iLast - i)/dt.milliseconds();
        d = (pLast - p)/dt.milliseconds();
        dt.reset();
        iLast = i;
        pLast = p;
        telem.addData("Speed:", p);
        return p*pCoeff+i*iCoeff+d*dCoeff+fCoeff;
    }
}
