package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name="Touchpad Test", group="Software Tests")
public class TouchpadTest extends OpModeEx {
    @Override
    public void initInternal() {
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        dashboardTelemetry.setMsTransmissionInterval(25);
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("x", gamepad1.getEncapsulatedGamepad().touchpad_finger_1_x);
        telemetryPro.addData("y", gamepad1.getEncapsulatedGamepad().touchpad_finger_1_y);
        telemetryPro.addData("touchpad", gamepad1.getEncapsulatedGamepad().touchpad);
        telemetryPro.addData("touchpad finger 1", gamepad1.getEncapsulatedGamepad().touchpad_finger_1);
    }
}
