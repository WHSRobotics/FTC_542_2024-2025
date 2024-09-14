package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;

@Config
@TeleOp(name = "BaiscWrist", group = "Wrist Tests")
public class BasicWristTest extends OpModeEx {
    Servo wristJoint;
    public static double position;

    private boolean toggle = true;

    @Override
    public void initInternal() {
        initializeDashboardTelemetry(0);
        wristJoint = hardwareMap.get(Servo.class, "wristJoint");
        wristJoint.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    protected void loopInternal() {
        if (gamepad1.DPAD_UP.value() && toggle){
            wristJoint.setPosition(Math.min(1, wristJoint.getPosition() + 0.01));
            toggle = false;
        } else if (gamepad1.DPAD_DOWN.value() && toggle){
            wristJoint.setPosition(Math.max(0, wristJoint.getPosition() - 0.01));
            toggle = false;
        }

        if (!gamepad1.DPAD_UP.value() && !gamepad1.DPAD_DOWN.value()){
            toggle = true;
        }
        //wristJoint.setPosition(position);
        telemetryPro.addData("Position", wristJoint.getPosition(), LineItem.Color.AQUA);
        dashboardTelemetry.addData("Position", wristJoint.getPosition());
    }
}
