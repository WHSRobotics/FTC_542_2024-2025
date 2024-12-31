package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.HorizontalServo;

@TeleOp(name = "horizontalTest")
public class HorizontalTest extends OpModeEx {
    private HorizontalServo horizontal;
    @Override
    public void initInternal() {
        horizontal = new HorizontalServo(hardwareMap);

    }

    @Override
    protected void loopInternal() {
        gamepad1.SQUARE.onPress(()->{
            horizontal.update();
        });
        horizontal.run();
    }
}
