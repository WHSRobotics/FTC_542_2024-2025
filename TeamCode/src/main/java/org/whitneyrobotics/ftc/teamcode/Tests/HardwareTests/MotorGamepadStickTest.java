package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "Quick Gamepad Stick Test")
public class MotorGamepadStickTest extends OpModeEx {
    private double power = 0;
    private double powerCap = 0.2;

    private boolean powerCapToggle = true;

    private DcMotorEx testMotor;

    @Override
    public void initInternal() {
        testMotor = hardwareMap.get(DcMotorEx.class, "testM");

        gamepad1.A.onPress(() -> powerCapToggle = !powerCapToggle);
    }

    @Override
    protected void loopInternal() {
        if (Math.abs(gamepad1.LEFT_STICK_Y.value()) > powerCap && powerCapToggle){
            if (gamepad1.LEFT_STICK_Y.value() > 0){
                power = powerCap;
            } else {
                power = -powerCap;
            }
        } else {
            power = gamepad1.LEFT_STICK_Y.value();
        }
    }
}
