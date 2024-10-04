//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//
//@TeleOp(name="Elbow Wrist Combined Test", group="Hardware Tests")
//public class ElbowWristTest extends OpModeEx {
//    ElbowWristImpl elbowWrist;
//    @Override
//    public void initInternal() {
//        elbowWrist = new ElbowWristImpl(hardwareMap);
//        gamepad1.CROSS.onPress(elbowWrist::toggle);
//    }
//
//    @Override
//    protected void loopInternal() {
//        elbowWrist.update();
//        telemetryPro.addData("Current State", elbowWrist.getState());
//        telemetryPro.addData("Toggle", elbowWrist.getToggle());
//    }
//}
