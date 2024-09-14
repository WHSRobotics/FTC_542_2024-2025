//package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.newLinearSlides;
//
//@TeleOp (name = "Basic Linear Slides Test")
//public class LinearSlidesBasicTest extends OpModeEx {
//    newLinearSlides linearSlides;
//
//    @Override
//    public void initInternal() {
//        linearSlides = new newLinearSlides(hardwareMap);
//
//    }
//
//    @Override
//    public void loopInternal() {
//        //linearSlides.setTarget(gamepad1);
//        linearSlides.operate(gamepad1.LEFT_STICK_Y.value(), gamepad1.BUMPER_LEFT.value());
//    }
//}
