package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;

@Config
@TeleOp(name = "RotationSlidesTest", group = "Control")
public class RotationSlidesTest extends OpMode {
    private PIDController controller;
    private MotionProfileTrapezoidal motionProfile;
    private NanoStopwatch stopwatch;
    private boolean telemetryInitialized = false;

    public static double p = 0.005, i = 0, d = 0;
    public static double f = 0.002;
    public static int targetPosition = 0;

    public static double V_MAX = 10; // max velocity in degrees/s
    public static double A_MAX = 20; // max acceleration in degrees/s^2
    private static final double ticksInDegrees = 700 / 180.0;

    private ControlConstants controlConstants;
    private DcMotorEx verticalSlides;
    private int initialPosition;

    @Override
    public void init() {
        controller = new PIDController(new ControlConstants(p, i, d));
        verticalSlides = hardwareMap.get(DcMotorEx.class,"vertical");
//        verticalSlides.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        verticalSlides.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motionProfile = new MotionProfileTrapezoidal(V_MAX, A_MAX);
        stopwatch = new NanoStopwatch();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());



    }

    @Override
    public void start() {
        // Reset stopwatch and create a new motion profile for the target

        stopwatch.reset();
        initialPosition = verticalSlides.getCurrentPosition();
        double error = (targetPosition - initialPosition) / ticksInDegrees;
        motionProfile.setGoal(error);
    }

    @Override
    public void loop() {
        // Update PID control constants
//        controlConstants = new ControlConstants(p, i, d);
//        controller.setConstants(controlConstants);

//        double elapsedTime = stopwatch.seconds();
//        double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
//        double desiredVelocity = motionProfile.velocityAt(elapsedTime);
//
//        int currentPos = armMotor.getCurrentPosition();
//        double error = (desiredPosition - currentPos);
//        controller.calculate(error);
//
//        // Feedforward term for desired velocity
//        double ff = desiredVelocity * f;
//        double pid = controller.getOutput();
//        double power = pid + ff;
//
//        armMotor.setPower(power);
        controller.setConstants(new ControlConstants(p, i, d));
        verticalSlides.setDirection(DcMotorSimple.Direction.FORWARD);
        double elapsedTime = stopwatch.seconds();
        double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
        int currentPos = verticalSlides.getCurrentPosition();
        double error = desiredPosition - currentPos;
        controller.calculate(error);
        double pidOutput = controller.getOutput();
        double ff = motionProfile.velocityAt(elapsedTime) * f;
        verticalSlides.setPower(pidOutput + ff);
        // Telemetry updates
        telemetry.addData("Current Position", currentPos);
        telemetry.addData("Target Position", targetPosition);
        telemetry.addData("Desired Position", desiredPosition);
//        telemetry.addData("Desired Velocity", desiredVelocity);
        telemetry.update();
    }
}
