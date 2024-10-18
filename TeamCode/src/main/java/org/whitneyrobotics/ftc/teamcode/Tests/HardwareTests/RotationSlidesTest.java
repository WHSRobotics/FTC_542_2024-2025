package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDVAController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;

@TeleOp(name = "rotatorTest")
public class RotationSlidesTest extends OpModeEx {
    private DcMotorEx rotator;  // Ensure motor is not static and properly initialized
    private static final double MAX_VELOCITY = 1000; // Max velocity for trapezoidal motion (encoder ticks/sec)
    private static final double MAX_ACCELERATION = 200; // Max acceleration (encoder ticks/sec^2)

    // PIDVA Constants (Tune these values as necessary)
    public static ControlConstants controllerC = new ControlConstants(1.0, 0, 0.002, 0.001, 0.01);
    public static PIDVAController control = new PIDVAController(MAX_VELOCITY, MAX_ACCELERATION,controllerC.kP, controllerC.kI, controllerC.kD);
    public static MotionProfileTrapezoidal trapezoidal = new MotionProfileTrapezoidal(MAX_VELOCITY,MAX_ACCELERATION);
    public static double output;
    public static double targetPos = 0;

    // Method to update the current position
    public double getCurrentPosition() {
        if (rotator != null) {
            return rotator.getCurrentPosition();
        } else {
            telemetry.addData("Error", "Motor not initialized properly");
            return 0;
        }
    }

    // Set the final target position using the enum
    public static void setFinalTarget(Positions positions) {
        targetPos = positions.position;  // targetPos will store the encoder value
        control.setTarget(targetPos); // Inform the controller about the new target
    }

    // Enum to define positions, ensure these are actual encoder values
    public enum Positions {
        POS1(1000),  // Example encoder position for POS1
        POS2(2000);  // Example encoder position for POS2

        double position;
        Positions(int number) {
            this.position = number;
        }
    }

    // Method to go to the position based on the PIDVA calculation
    public void goToPosition() {
        if (rotator != null) {
            double error = targetPos - rotator.getCurrentPosition();
            double velocity = rotator.getVelocity();

            // Calculate PIDVA output using error and current velocity
            control.calculate(error, velocity);
            output = control.getOutput();
            rotator.setPower(output);  // Apply PIDVA output to the motor
        } else {
            telemetry.addData("Error", "Motor not initialized properly");
        }
    }

    @Override
    public void initInternal() {
        // Initialize motor and check if it's properly connected
        try {

            rotator = hardwareMap.get(DcMotorEx.class, "rotator");
            rotator.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);  // Set brake behavior
            rotator.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);  // Ensure motor is in encoder mode
            control.reset();
        } catch (Exception e) {
            telemetry.addData("Motor Init Error", "Failed to initialize motor: " + e.getMessage());
        }
    }

    @Override
    protected void loopInternal() {
        // Ensure the motor is initialized before proceeding
        if (rotator == null) {
            telemetry.addData("Error", "Motor not initialized");
            telemetry.update();
            return; // Skip further execution if the motor is not initialized
        }

        // Set the target position (you can change this based on joystick input or button press)
        setFinalTarget(Positions.POS1);  // For example, going to POS1

        // Use the PIDVA controller to move the motor
        goToPosition();

        // Debug Telemetry
//        telemetry.addData("Current Position", getCurrentPosition());
//        telemetry.addData("Target Position", targetPos);
//        telemetry.addData("PIDVA Output", output);
//        telemetry.addData("Motor Velocity", rotator.getVelocity());
//        telemetry.update();
    }
}
