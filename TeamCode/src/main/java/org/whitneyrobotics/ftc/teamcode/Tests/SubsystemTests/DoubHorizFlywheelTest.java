package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp
public class DoubHorizFlywheelTest extends OpModeEx {

    DcMotorEx motor1;
    DcMotorEx motor2;

    @Override
    public void initInternal() {
        motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
        motor2 = hardwareMap.get(DcMotorEx.class, "motor2");
    }

    @Override
    protected void loopInternal() {
        double power = gamepad1.LEFT_STICK_Y.value();
        motor1.setPower(power);
        motor2.setPower(-power);
    }
}
