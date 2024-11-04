package org.whitneyrobotics.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.IntakeClaw;

@TeleOp(name = "IntakeClawTest")
public class ClawTest extends OpModeEx {
    public IntakeClaw claw;
    @Override
    public void initInternal() {
        claw = new IntakeClaw(hardwareMap);
        gamepad1.SQUARE.onPress(claw::updateState);
    }

    @Override
    protected void loopInternal() {
        claw.run();
    }
}
