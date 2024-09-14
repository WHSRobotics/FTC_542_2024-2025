//package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
//import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.FormalLinearSlides;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.FormalPIDLinearSlides;
//
//@TeleOp (name = "Formal Linear Slides Test")
//public class FormalLinearSlidesTest extends OpModeEx {
//    //public FormalLinearSlides linearSlides;
//    public FormalPIDLinearSlides linearSlides;
//
//    private double lastTime = 0;
//    private double lastVelocity = 0;
//
//    private double maxVelocity = 0;
//    private double maxAcceleration = 0;
//
//    private NanoStopwatch stopwatch;
//
//    @Override
//    public void initInternal() {
//        //linearSlides = new FormalLinearSlides(hardwareMap);
//        linearSlides = new FormalPIDLinearSlides(hardwareMap);
//        stopwatch = new NanoStopwatch();
//        stopwatch.reset();
//    }
//
//    @Override
//    protected void loopInternal() {
//        double power = linearSlides.operateTeleOp(gamepad1.A.value(), gamepad1.LEFT_STICK_Y.value());
//
//        if (linearSlides.lSlides.getVelocity(AngleUnit.RADIANS) > maxVelocity){
//            maxVelocity = linearSlides.lSlides.getVelocity(AngleUnit.RADIANS);
//        }
//
//        double acceleration = (linearSlides.lSlides.getVelocity(AngleUnit.RADIANS) - lastVelocity) / (stopwatch.seconds() - lastTime);
//
//        if (acceleration > maxAcceleration){
//            maxAcceleration = acceleration;
//        }
//
//        lastTime = stopwatch.seconds();
//        lastVelocity = linearSlides.lSlides.getVelocity(AngleUnit.RADIANS);
//
//        telemetryPro.addData("Position", linearSlides.lSlides.getCurrentPosition(), LineItem.Color.AQUA);
//        telemetryPro.addData("Current Target", linearSlides.currentTarget, LineItem.Color.RED);
//        telemetryPro.addData("Current Macro", linearSlides.currentMacro, LineItem.Color.YELLOW);
//        telemetryPro.addData("Power", linearSlides.lSlides.getPower(), LineItem.Color.GREEN);
//        telemetryPro.addData("Theoretical Power", power, LineItem.Color.GREEN);
//        telemetryPro.addLine();
//
//        telemetryPro.addData("Max Velocity", maxVelocity, LineItem.Color.GRAY);
//        telemetryPro.addData("Max Acceleration", maxAcceleration, LineItem.Color.GRAY);
//    }
//}
