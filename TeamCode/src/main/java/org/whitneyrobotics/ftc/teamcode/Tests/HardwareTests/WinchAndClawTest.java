//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TelemetryPro;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.WinchAndClaw;
//
//@TeleOp(name="Winch and Claw Test", group="Hardware Tests")
//public class WinchAndClawTest extends OpModeEx {
//    WinchAndClaw grapple;
//    @Override
//    public void initInternal() {
//        grapple = new WinchAndClaw(hardwareMap);
//    }
//
//    @Override
//    protected void loopInternal() {
//        grapple.update();
//        telemetryPro.addData("Winch Position", grapple.getPosition());
//        telemetryPro.addData("Winch Velocity", grapple.getVelocity());
//    }
//}
