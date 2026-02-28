package org.firstinspires.ftc.teamcode.tasks;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

import org.firstinspires.ftc.teamcode.utilities.TritonicsOpMode;

import java.util.List;

public class ReadMotif extends Task{

    TritonicsOpMode opMode;
    LLResult result;
    List<LLResultTypes.FiducialResult> tags;
    int id;

    public ReadMotif(TritonicsOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public boolean run() {
        if(opMode.motif == TritonicsOpMode.Motif.UNREAD) {
            result = opMode.sensors.limelight.getLatestResult();
            if (result.isValid()) {
                tags = result.getFiducialResults();
                for (LLResultTypes.FiducialResult tag : tags) {
                    id = tag.getFiducialId();
                    if (id == 21) {
                        opMode.motif = TritonicsOpMode.Motif.GPP;
                    } else if (id == 22) {
                        opMode.motif = TritonicsOpMode.Motif.PGP;
                    } else if (id == 23) {
                        opMode.motif = TritonicsOpMode.Motif.PPG;
                    }
                }
            }
        }
        return opMode.motif != TritonicsOpMode.Motif.UNREAD;
    }

    @Override
    public Task reset() {
        return new ReadMotif(opMode);
    }

}
