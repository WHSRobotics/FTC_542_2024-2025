////package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
////
////import com.acmerobotics.dashboard.config.Config;
////import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
////
////import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
////import org.whitneyrobotics.ftc.teamcode.Subsystems.RotatorMotor;
////
////@Config
////@TeleOp(name = "RotatorTest", group = "Control")
////public class RotatorTest extends OpModeEx {
////    private RotatorMotor rotationSlides; // Instance of RotationSlides
////
////    public static double p = 0.005, i = 0, d = 0;
////    public static double f = 0.00028;
////    public static RotatorMotor.AngleTicks targetPosition = RotatorMotor.AngleTicks.FOUR;
////
////    @Override
////    public void initInternal() {
////        // Initialize the RotationSlides instance
////        rotationSlides = new RotatorMotor(hardwareMap);
////        rotationSlides.setState(targetPosition); // Set initial target position
////
//////        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
////    }
////
////    @Override
////    public void start() {
////        // Reset the state of the RotationSlides when the OpMode starts
////        rotationSlides.setState(0);
////    }
////
////    @Override
////    public void loopInternal() {
////        // Update target position based on gamepad input
////
////        gamepad1.TRIANGLE.onPress(()->{
//=======
//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.RotatorMotor;
//
//@Config
//@TeleOp(name = "RotatorTest", group = "Control")
//public class RotatorTest extends OpModeEx {
//    private RotatorMotor rotationSlides; // Instance of RotationSlides
//
//    public static double p = 0.005, i = 0, d = 0;
//    public static double f = 0.00028;
//    public static RotatorMotor.AngleTicks targetPosition = RotatorMotor.AngleTicks.ZERO;
//
//    @Override
//    public void initInternal() {
//        // Initialize the RotationSlides instance
//        rotationSlides = new RotatorMotor(hardwareMap);
//        rotationSlides.setState(targetPosition); // Set initial target position
//
////        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//    }
//
//    @Override
//    public void start() {
//        // Reset the state of the RotationSlides when the OpMode starts
//        rotationSlides.setState(RotatorMotor.AngleTicks.ZERO);
//    }
//
//    @Override
//    public void loopInternal() {
//        // Update target position based on gamepad input
//
////        gamepad1.TRIANGLE.onPress(()->{
////            targetPosition = RotatorMotor.AngleTicks.ZERO;
//
////            rotationSlides.setState(targetPosition);
////
////        });
////        gamepad1.CIRCLE.onPress(()->{
//
////
//
////            targetPosition = RotatorMotor.AngleTicks.FOUR;
//
////            rotationSlides.setState(targetPosition);
////
////        });
////        gamepad1.CROSS.onPress(()->{
//
////            targetPosition = RotatorMotor.AngleTicks.TWO;
//
////            rotationSlides.setState(targetPosition);
////
////        });
////
////        gamepad1.SQUARE.onPress(()->{
//
////            rotationSlides.setState(targetPosition);
////
////        });
////        rotationSlides.slideBrake();
////
////        if (gamepad1.LEFT_STICK_X.value() == 0){
////            rotationSlides.GamepadUsed(false);
////            rotationSlides.update();
////        }else{
////            rotationSlides.GamepadUsed(true);
////            rotationSlides.rotatorSetPower(gamepad1);
////            targetPosition = rotationSlides.getCurrentPosition();
////            rotationSlides.setState(targetPosition);
////
////
////        }
////
////        rotationSlides.slidesSetPower(gamepad1);
////
////        telemetryPro.addData("Gamepad Value is 0",gamepad1.LEFT_STICK_X.value() ==0);
////        // Telemetry updates
////        telemetryPro.addData("Current Position", rotationSlides.getCurrentPosition());
////        telemetryPro.addData("Target Position", targetPosition);
////        telemetryPro.update();
////    }
////    @Override
////    public void stop(){
////        targetPosition = 0;
////        rotationSlides.setState(targetPosition);
////        rotationSlides.update();
////        super.stop();
////
////    }
////}
//
////            targetPosition = RotatorMotor.AngleTicks.ONE;
////            rotationSlides.setState(targetPosition);
////
////        });
//        rotationSlides.slideBrake();
//        rotationSlides.rotatorSetPower(gamepad1);
//
//        rotationSlides.slidesSetPower(gamepad1);
//
//        telemetryPro.addData("Gamepad Value is 0",gamepad1.LEFT_STICK_X.value() ==0);
//        // Telemetry updates
//        telemetryPro.addData("Current Position", rotationSlides.getCurrentPosition());
//        telemetryPro.addData("Target Position", targetPosition);
//        telemetryPro.update();
//    }
//    @Override
//    public void stop(){
//        rotationSlides.setState(targetPosition);
//        rotationSlides.update();
//        super.stop();
//
//    }
//}
//
