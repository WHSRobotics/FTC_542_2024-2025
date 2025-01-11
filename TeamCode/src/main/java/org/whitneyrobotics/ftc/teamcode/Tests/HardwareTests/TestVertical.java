package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "TestVertical")
public class TestVertical extends OpModeEx {
    DcMotorEx ls;

    @Override
    public void initInternal() {
        ls = hardwareMap.get(DcMotorEx.class, "vertical");
        ls.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    @Override
    protected void loopInternal() {
        ls.setPower(gamepad1.LEFT_STICK_Y.value());
        gamepad1.Y.onPress(()->{
            ls.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        });
        telemetryPro.addData("current position",ls.getCurrentPosition());
    }
}
