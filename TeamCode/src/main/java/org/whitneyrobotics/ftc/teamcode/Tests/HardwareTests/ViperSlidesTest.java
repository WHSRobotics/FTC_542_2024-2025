package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Tests.Test;


@TeleOp(name = "ViperSlidesTest", group = "Tests")
@Test(name = "ViperSlidesTest")
public class ViperSlidesTest extends OpModeEx {
    DcMotorEx slideMotor;

    @Override
    public void initInternal() {
        slideMotor = hardwareMap.get(DcMotorEx.class, "slideMotor");
        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        gamepad1.A.onPress(() -> {
            slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if(slideMotor.getDirection() == DcMotorSimple.Direction.REVERSE){
                slideMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            } else slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        });
    }

    @Override
    protected void loopInternal() {
        slideMotor.setPower(gamepad1.LEFT_STICK_Y.value() * (gamepad1.BUMPER_LEFT.value() ? 0.5 : 1));
        telemetryPro.addData("power", slideMotor.getPower(), LineItem.Color.ROBOTICS);
        telemetryPro.addData("position", slideMotor.getCurrentPosition(), LineItem.Color.ROBOTICS);
    }
}
