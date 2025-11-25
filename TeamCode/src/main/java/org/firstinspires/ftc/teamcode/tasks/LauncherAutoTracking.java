package org.firstinspires.ftc.teamcode.tasks;

public class LauncherAutoTracking {
    double DO_y = 1.04
    double DX = Math.sqrt(Math.pow(G_xo,2)+Math.pow(G_yo,2));
    double V_g = V_gx(Math.cos(theta-90)-V_gy(Math.sin(theta-90)));
    double t_o = Math.sqrt((2*DO_xo(DY*DO_xo-DO_y*DX))/(9.8*DX(DX-DO_xo)));
    double T = (t_o * DX)/DO_xo;
    double V_oy = DO_y/t_o - (1/2(9.8)*t_o);
    double V_ox = DO_xo/t_o - V_g;
    double V_o = Math.sqrt(Math.pow(V_oy, 2)+Math.pow(V_ox, 2));
    double RA = Math.atan(V_oy/V_ox);
    double DEC = 180/Math.PI(Math.atan((G_yo-V_gy*T)/(G_xo-Vgx*T))) - theta

}
