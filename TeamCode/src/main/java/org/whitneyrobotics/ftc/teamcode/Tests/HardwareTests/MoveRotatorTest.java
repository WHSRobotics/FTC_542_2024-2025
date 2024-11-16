package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.IntakeClaw;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RotatorMotor;
import org.whitneyrobotics.ftc.teamcode.Subsystems.Wrist;

@TeleOp(name = "MoveRotatorTest")
public class MoveRotatorTest extends OpModeEx {
    private RotatorMotor arm_motor;
    private Wrist wrist;
    private IntakeClaw claw;

    @Override
    public void initInternal() {

        arm_motor = new RotatorMotor(hardwareMap);
        wrist = new Wrist(hardwareMap);
        claw = new IntakeClaw(hardwareMap);
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("armPOs",arm_motor.getCurrentPosition());
        arm_motor.rotatorSetPower(gamepad1);
//        gamepad1.CROSS.onPress(()->{
//            arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            arm_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        });
        arm_motor.slidesSetPower(gamepad1);
        gamepad1.SQUARE.onPress(()->{
            wrist.Open();
        });
        gamepad1.CROSS.onPress(()->{
            claw.updateState();
        });
        gamepad1.TRIANGLE.onPress(()->{
            wrist.Half();
        });
        gamepad1.CIRCLE.onPress(()->{
            wrist.Close();
        });
//        wrist.run();
        claw.run();

    }
}
