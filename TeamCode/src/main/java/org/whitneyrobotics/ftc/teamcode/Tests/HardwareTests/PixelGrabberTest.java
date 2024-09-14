//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.PixelGrabber;
//
//@TeleOp(name="Pixel Grabber Test", group = "Hardware Tests")
//public class PixelGrabberTest extends OpModeEx {
//    PixelGrabber grabber;
//    @Override
//    public void initInternal() {
//        grabber = new PixelGrabber(hardwareMap);
//        gamepad1.CROSS.onPress(grabber::grabBoth);
//        gamepad1.SQUARE.onPress(grabber::grabOne);
//        gamepad1.TRIANGLE.onPress(grabber::releaseBoth);
//    }
//
//    @Override
//    protected void loopInternal() {
//
//    }
//}
