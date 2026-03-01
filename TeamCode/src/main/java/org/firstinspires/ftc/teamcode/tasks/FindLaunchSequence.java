package org.firstinspires.ftc.teamcode.tasks;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;
import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode.Motif;
import static org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode.Motif.*;

import androidx.annotation.NonNull;

public class FindLaunchSequence extends Task {

    TritonicsOpMode opMode;
    Motif current;

    Task loadSequence;

    public FindLaunchSequence(TritonicsOpMode opMode, @NonNull Motif current) {
        this.opMode = opMode;
        this.current = current;
        loadSequence = null;
    }

    @Override
    public boolean run() {
        if (loadSequence == null) {
            if (opMode.motif != UNREAD) {
                if (current == opMode.motif) {
                    loadSequence = new LaunchSequenceOne(opMode.intake, opMode.launcher);
                } else if ((current == GPP && opMode.motif == PPG) || (current == PGP && opMode.motif == GPP) || (current == PPG && opMode.motif == PGP)) {
                    loadSequence = new LaunchSequenceTwo(opMode.intake, opMode.launcher);
                } else {
                    loadSequence = new LaunchSequenceThree(opMode.intake, opMode.launcher);
                }
            }
            loadSequence = new LaunchSequenceThree(opMode.intake, opMode.launcher);
            return false;
        } else {
            return loadSequence.run();
        }
    }

}
