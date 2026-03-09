package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

public class DpadOffset extends Task{

    TritonicsOpMode opMode;

    boolean prevDpadLeft;
    boolean prevDpadRight;

    public DpadOffset(TritonicsOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public boolean run() {

        if(opMode.gamepad2.dpad_left && !prevDpadLeft) {
            opMode.offset++;
            opMode.gamepad2.rumble(100);
        }

        if(opMode.gamepad2.dpad_right && !prevDpadRight) {
            opMode.offset--;
            opMode.gamepad2.rumble(100);
        }

        prevDpadLeft = opMode.gamepad2.dpad_left;
        prevDpadRight = opMode.gamepad2.dpad_right;

        return false;
    }

}
