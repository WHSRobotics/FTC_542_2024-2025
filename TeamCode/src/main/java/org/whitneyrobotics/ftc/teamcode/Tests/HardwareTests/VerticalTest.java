package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.VerticalSlides;

@TeleOp(name = "VerticalTest")
public class VerticalTest extends OpModeEx {
    public VerticalSlides verticalSlides;
    @Override
    public void initInternal() {
        verticalSlides = new VerticalSlides(hardwareMap);
    }

    @Override
    protected void loopInternal() {
        verticalSlides.setPower(gamepad1);

    }
}
