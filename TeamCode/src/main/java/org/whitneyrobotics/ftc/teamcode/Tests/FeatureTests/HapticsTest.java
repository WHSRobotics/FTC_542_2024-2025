package org.whitneyrobotics.ftc.teamcode.Tests.FeatureTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name="Haptics Test")
public class HapticsTest extends OpModeEx {
    private double power = 0f;
    @Override
    public void initInternal() {
        gamepad1.DPAD_UP.onPress(() -> power = Range.clip(power+0.01, 0,1));
        gamepad1.DPAD_DOWN.onPress(() -> power = Range.clip(power-0.01, 0,1));
        gamepad1.DPAD_RIGHT.onPress(() -> power = Range.clip(power+0.05, 0,1));
        gamepad1.DPAD_LEFT.onPress(() -> power = Range.clip(power-0.05, 0,1));
    }

    @Override
    protected void loopInternal() {
        gamepad1.getEncapsulatedGamepad().rumble(power, power, 250);
        telemetryPro.addData("Power", power);
    }
}
