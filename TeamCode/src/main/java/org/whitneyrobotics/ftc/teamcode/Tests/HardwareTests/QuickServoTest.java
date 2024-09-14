package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name="QuickServoTest", group="Hardware Tests")
public class QuickServoTest extends OpModeEx {
    Servo s;
    double pos;
    @Override
    public void initInternal() {
        s = hardwareMap.get(Servo.class, "angle");
        pos = s.getPosition();
        gamepad1.DPAD_UP.onPress(() -> pos = pos + 0.01);
        gamepad1.DPAD_DOWN.onPress(() -> pos = pos - 0.01);
        gamepad1.DPAD_RIGHT.onPress(() -> pos = pos + 0.1);
        gamepad1.DPAD_LEFT.onPress(() -> pos = pos - 0.1);
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        dashboardTelemetry.setMsTransmissionInterval(25);
    }

    @Override
    protected void loopInternal() {
        s.setPosition(pos);
        telemetryPro.addData("pos", pos);
    }
}
