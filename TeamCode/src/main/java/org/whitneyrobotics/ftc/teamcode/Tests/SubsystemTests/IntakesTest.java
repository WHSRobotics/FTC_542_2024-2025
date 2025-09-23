package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp
public class IntakesTest extends OpModeEx {

    DcMotorEx motor;
    boolean joystick = false;

    @Override
    public void initInternal() {
        motor = hardwareMap.get(DcMotorEx.class, "motor");
    }

    @Override
    protected void loopInternal() {
        gamepad1.CIRCLE.onPress(() -> motor.setPower(-1));
        gamepad1.BUMPER_RIGHT.onPress(() -> joystick =! joystick);
        if(joystick){
            motor.setPower(-gamepad1.LEFT_STICK_Y.value());
            telemetryPro.addData("motor power:", -gamepad1.LEFT_STICK_Y.value());
        }
    }
}