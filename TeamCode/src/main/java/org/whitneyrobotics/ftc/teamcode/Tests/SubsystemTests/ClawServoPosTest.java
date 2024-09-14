package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

public class ClawServoPosTest extends OpModeEx {
    public Servo clawTestServo;
    public double testPos;

    public boolean reset = true;

    @Override
    public void initInternal() {
        clawTestServo = hardwareMap.get(Servo.class, "test");
        testPos = clawTestServo.getPosition();
    }

    @Override
    protected void loopInternal() {
        if (gamepad1.DPAD_UP.value() && reset){
            testPos += 1;
            reset = false;
        }
        if (gamepad1.DPAD_DOWN.value() && reset){
            testPos -= 1;
            reset = false;
        }
        if (!gamepad1.DPAD_UP.value() && !gamepad1.DPAD_DOWN.value()){
            reset = true;
        }

        if (gamepad1.A.value()){
            clawTestServo.setPosition(testPos);
        }

        telemetryPro.addData("Test Position", testPos);
    }
}
