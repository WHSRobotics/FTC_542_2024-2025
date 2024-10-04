//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//
//@TeleOp(name="DroneTest", group="HardwareTests")
//public class DroneTest extends OpModeEx {
//    Drone d;
//    @Override
//    public void initInternal() {
//        d = new Drone(hardwareMap);
//        gamepad1.A.onPress(d::fire);
//        gamepad1.B.onPress(d::updateMacroAngle);
//    }
//
//    @Override
//    protected void loopInternal() {
//        d.update();
//        telemetryPro.addData("STATE", d.getState());
//        telemetryPro.addData("Pos", d.getPos());
//    }
//}
