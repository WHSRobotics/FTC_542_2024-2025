package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name= "quicktest")
public class Servotest extends OpModeEx {
    Servo claw;

    @Override
    public void initInternal() {
        claw = hardwareMap.get(Servo.class,"test");
    }

    @Override
    protected void loopInternal() {
        claw.setPosition(1);
    }
}
