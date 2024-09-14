package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "GamepadLED Test", group="tests")
@Config
public class GamepadLEDTest extends OpModeEx {
    public static int R;
    public static int G;
    public static int B;
    @Override
    public void initInternal() {

    }

    @Override
    protected void loopInternal() {
        gamepad1.getEncapsulatedGamepad().setLedColor(R,G,B,-1);
        gamepad2.getEncapsulatedGamepad().setLedColor(R,G,B,-1);
    }
}
