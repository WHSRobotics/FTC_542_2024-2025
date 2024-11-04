package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "MoveRotatorTest")
public class MoveRotatorTest extends OpModeEx {
    private DcMotorEx arm_motor;
    @Override
    public void initInternal() {

        arm_motor = hardwareMap.get(DcMotorEx.class,"rotator");
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("armPOs",arm_motor.getCurrentPosition());
        arm_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        arm_motor.setPower(gamepad1.LEFT_STICK_X.value());
        gamepad1.CROSS.onPress(()->{
            arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });
    }
}
