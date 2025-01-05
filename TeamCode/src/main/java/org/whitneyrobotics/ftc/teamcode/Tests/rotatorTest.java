package org.whitneyrobotics.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "MoveRotator")
public class rotatorTest extends OpModeEx {
    DcMotorEx vertical;
    @Override
    public void initInternal() {
        vertical = hardwareMap.get(DcMotorEx.class,"vertical");
        vertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        vertical.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        vertical.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    protected void loopInternal() {
        vertical.setPower(gamepad1.LEFT_STICK_Y.value());
        telemetryPro.addData("Current POs",vertical.getCurrentPosition());
    }
}
