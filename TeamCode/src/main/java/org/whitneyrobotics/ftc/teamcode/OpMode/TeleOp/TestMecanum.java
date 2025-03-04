package org.whitneyrobotics.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "TestForwardAndBack")
public class TestMecanum extends OpModeEx {
    DcMotorEx fL,fR,bL,bR;
    @Override
    public void initInternal() {
        fL = hardwareMap.get(DcMotorEx.class, "fL");
        fR = hardwareMap.get(DcMotorEx.class, "fR");
        bL = hardwareMap.get(DcMotorEx.class, "bL");
        bR = hardwareMap.get(DcMotorEx.class, "bR");

    }

    @Override
    protected void loopInternal() {
        fL.setPower(-gamepad1.LEFT_STICK_Y.value());
        fR.setPower(gamepad1.LEFT_STICK_Y.value());
        bL.setPower(-gamepad1.LEFT_STICK_Y.value());
        bR.setPower(gamepad1.LEFT_STICK_Y.value());


    }
}
