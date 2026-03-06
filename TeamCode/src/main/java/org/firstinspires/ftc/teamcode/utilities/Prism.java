package org.firstinspires.ftc.teamcode.utilities;

import android.content.Context;

import com.qualcomm.hardware.lynx.LynxI2cDeviceSynchV2;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;

import java.util.List;

public class Prism {

    // goBILDA Prism default 7-bit address
    public static final int PRISM_ADDR_7BIT = 0x38;

    // Prism layer0 register\
    private static final int REG_LAYER0 = 0x08;

    // Sub-registers for layer writes
    private static final int SUB_SELECTED_ANIMATION = 0x00;
    private static final int SUB_BRIGHTNESS = 0x01;
    private static final int SUB_START = 0x02;
    private static final int SUB_STOP = 0x03;
    private static final int SUB_PRIMARY_RGB = 0x04;

    // Animations
    private static final int ANIM_SOLID_COLOR = 0x01;

    private final LynxI2cDeviceSynchV2 i2c;
    /**
     * @param i2cPort 0..3 on the Control Hub I2C bus (matches the labeled ports)
     */
    public Prism(HardwareMap hw, int i2cPort) {
        Context ctx = hw.appContext;

        // Pick the first LynxModule (works for Control Hub only setups).
        // If you have BOTH Control Hub + Expansion Hub, you may need to select the right module.
        List<LynxModule> modules = hw.getAll(LynxModule.class);
            if (modules.isEmpty()) throw new IllegalStateException("No LynxModule found in hardwareMap");

        LynxModule module = modules.get(0);

        i2c = new LynxI2cDeviceSynchV2(ctx, module, i2cPort);  // public ctor  [oai_citation:1\'87javadoc](https://javadoc.io/static/org.firstinspires.ftc/Hardware/9.1.0/com/qualcomm/hardware/lynx/LynxI2cDeviceSynchV2.html)
            i2c.setI2cAddress(I2cAddr.create7bit(PRISM_ADDR_7BIT));
            i2c.engage(); // engage() is inherited from LynxController  [oai_citation:2\'87javadoc](https://javadoc.io/static/org.firstinspires.ftc/Hardware/9.1.0/com/qualcomm/hardware/lynx/LynxI2cDeviceSynch.html)
    }

    public void solidAll(int r, int g, int b, int brightnessPct) {
        writeLayer(REG_LAYER0, SUB_SELECTED_ANIMATION, (byte) ANIM_SOLID_COLOR);
        writeLayer(REG_LAYER0, SUB_BRIGHTNESS, (byte) clamp(brightnessPct, 0, 100));
        writeLayer(REG_LAYER0, SUB_START, (byte) 0);
        writeLayer(REG_LAYER0, SUB_STOP, (byte) 255);
        writeLayer(REG_LAYER0, SUB_PRIMARY_RGB,
            (byte) clamp(r, 0, 255),
            (byte) clamp(g, 0, 255),
            (byte) clamp(b, 0, 255));
    }

            /** Set an inclusive segment [start..stop] (indices 0..255) to a solid color on the given layer. */
    public void setSegment(int layer, int start, int stop, int r, int g, int b, int brightnessPct) {
        int layerReg = REG_LAYER0 + clamp(layer, 0, 9);

        // Select Solid Color animation for this layer
        writeLayer(layerReg, SUB_SELECTED_ANIMATION, (byte) ANIM_SOLID_COLOR);

        // Brightness (0..100)
        writeLayer(layerReg, SUB_BRIGHTNESS, (byte) clamp(brightnessPct, 0, 100));

        // Start/Stop indices (uint8)
        writeLayer(layerReg, SUB_START, (byte) clamp(start, 0, 255));
        writeLayer(layerReg, SUB_STOP,  (byte) clamp(stop,  0, 255));

        // Color (R,G,B)
        writeLayer(layerReg, SUB_PRIMARY_RGB,
            (byte) clamp(r, 0, 255),
            (byte) clamp(g, 0, 255),
            (byte) clamp(b, 0, 255));
    }

    public void off() { solidAll(0, 0, 0, 0); }
        /** Writes: [subReg][data...] into the given layer register. */
        private void writeLayer(int layerReg, int subReg, byte... data) {
        byte[] payload = new byte[1 + data.length];
        payload[0] = (byte) (subReg & 0xFF);
        System.arraycopy(data, 0, payload, 1, data.length);
        i2c.write(layerReg, payload);
    }

    private static int clamp(int v, int lo, int hi) { return Math.max(lo, Math.min(hi, v)); }

}