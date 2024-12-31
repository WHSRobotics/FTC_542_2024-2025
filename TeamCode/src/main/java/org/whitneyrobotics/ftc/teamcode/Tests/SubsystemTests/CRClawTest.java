package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.CRClaw;

@TeleOp(name = "CRServo")
public class CRClawTest extends OpModeEx {
    private CRClaw claw;
    @Override
    public void initInternal() {
        claw = new CRClaw(hardwareMap);
    }

    @Override
    protected void loopInternal() {
//        gamepad1.Y.onPress(()->{
//            claw.setPower(true);
//        });
//        gamepad1.B.onPress(()->{
//            claw.setPower(false);
//        });
        if (gamepad1.LEFT_TRIGGER.value() != 0 &&
                gamepad1.RIGHT_TRIGGER.value()==0){
            claw.ReverseCR(false);
            claw.update();
        }else if (gamepad1.RIGHT_TRIGGER.value() != 0 &&
                gamepad1.LEFT_TRIGGER.value()==0) {
            claw.ReverseCR(true);
            claw.update();
        } else if (gamepad1.RIGHT_TRIGGER.value() == 0 &&
                gamepad1.LEFT_TRIGGER.value()==0){
            claw.stop();
        }
        telemetryPro.addData("LEFT TRIGGER",gamepad1.LEFT_TRIGGER.value());
        telemetryPro.addData("RIGHT TRIGGER",gamepad1.RIGHT_TRIGGER.value());

    }
}
