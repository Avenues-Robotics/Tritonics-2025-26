package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.function.IntSupplier;

public class VelPID {

    double pCoeff;
    double iCoeff;
    double dCoeff;

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

    public VelPID(double pCoeff, double iCoeff, double dCoeff, IntSupplier pos){
        this.pCoeff = pCoeff;
        this.iCoeff = iCoeff;
        this.dCoeff = dCoeff;

        this.globalPos = pos;

        t = new ElapsedTime();
        dt = new ElapsedTime();
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
        return p*pCoeff+i*iCoeff+d*dCoeff;
    }
}
