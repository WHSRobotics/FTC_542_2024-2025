package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "Servo Testing 12.1.2023")
public class ClawDualServoPosTest extends OpModeEx {
    public Servo clawTestServo;
    public Servo clawTestServoTwo;
    public double testPos;
    public double testPosTwo;

    public boolean reset = true;
    public boolean resetTwo = true;

    public boolean firstLoop = true;

    @Override
    public void initInternal() {
        clawTestServo = hardwareMap.get(Servo.class, "angle");
        clawTestServoTwo = hardwareMap.get(Servo.class, "drone");

        testPos = 0;
        testPosTwo = 0;
    }

    @Override
    protected void loopInternal() {
        //testPos = Math.round(testPos * 100) / 100;
        //testPosTwo = Math.round(testPosTwo * 100) / 100;
        if (firstLoop){
            testPos = 0;
            testPosTwo = 1;
            firstLoop = false;
        }

        if (gamepad1.DPAD_UP.value() && reset){
            testPos += 0.01;
            reset = false;
        }
        if (gamepad1.DPAD_DOWN.value() && reset){
            testPos -= 0.01;
            reset = false;
        }
        if (gamepad1.DPAD_RIGHT.value() && resetTwo){
            testPosTwo += 0.01;
            resetTwo = false;
        }
        if (gamepad1.DPAD_LEFT.value() && resetTwo){
            testPosTwo -= 0.01;
            resetTwo = false;
        }
        if (!gamepad1.DPAD_UP.value() && !gamepad1.DPAD_DOWN.value()){
            reset = true;
        }
        if (!gamepad1.DPAD_LEFT.value() && !gamepad1.DPAD_RIGHT.value()){
            resetTwo = true;
        }

        if (gamepad1.TRIANGLE.value()){
            clawTestServo.setPosition(testPos);
            clawTestServoTwo.setPosition(testPosTwo);
        }

        if (gamepad1.CIRCLE.value()){
            clawTestServo.setPosition(testPos);
        }

        if (gamepad1.SQUARE.value()){
            clawTestServoTwo.setPosition(testPosTwo);
        }

        if (gamepad1.CROSS.value()){
            testPos = clawTestServo.getPosition();
            testPosTwo = clawTestServoTwo.getPosition();
        }

        telemetryPro.addData("Test Position", testPos);
        telemetryPro.addData("Claw Position", clawTestServo.getPosition());
        telemetryPro.addData("Test Position Two", testPosTwo);
        telemetryPro.addData("Claw Position Two", clawTestServoTwo.getPosition());
    }
}
