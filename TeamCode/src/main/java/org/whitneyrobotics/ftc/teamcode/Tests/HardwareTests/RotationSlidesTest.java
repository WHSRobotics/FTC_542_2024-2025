package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

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
    private DcMotorEx armMotor;
    private int initialPosition;

    @Override
    public void init() {
        controlConstants = new ControlConstants(p, i, d);
        controller = new PIDController(controlConstants);
        armMotor = hardwareMap.get(DcMotorEx.class, "rotator");
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());



    }

    @Override
    public void start() {
        // Reset stopwatch and create a new motion profile for the target
        stopwatch.reset();

        int currentPos = armMotor.getCurrentPosition();
        double error = (targetPosition - currentPos) / ticksInDegrees;
        motionProfile.setGoal(error);
    }

    @Override
    public void loop() {
        // Update PID control constants
        controlConstants = new ControlConstants(p, i, d);
        controller.setConstants(controlConstants);

        double elapsedTime = stopwatch.seconds();
        double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
        double desiredVelocity = motionProfile.velocityAt(elapsedTime);

        int currentPos = armMotor.getCurrentPosition();
        double error = (desiredPosition - currentPos);
        controller.calculate(error);

        // Feedforward term for desired velocity
        double ff = desiredVelocity * f;
        double pid = controller.getOutput();
        double power = pid + ff;

        armMotor.setPower(power);

        // Telemetry updates
        telemetry.addData("Current Position", currentPos);
        telemetry.addData("Target Position", targetPosition);
        telemetry.addData("Desired Position", desiredPosition);
        telemetry.addData("Desired Velocity", desiredVelocity);
        telemetry.update();
    }
}
