package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="encoderopmode", group="tests")
public class KickerTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        final DcMotor motor = hardwareMap.get(DcMotor.class,"arm");

        // Reset the motor encoder so that it reads zero ticks
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // BRAKE is essential for holding position

        // Initialize mode to RUN_USING_ENCODER or similar before waitForStart
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        // Variables to track if we've sent a command to move to position for each button
        boolean rightBumperCommandSent = false;
        boolean leftBumperCommandSent = false;

        // Define target positions
        final int TARGET_POSITION_KICKER = -200;
        final int TARGET_POSITION_ROTATION = 300; // Assuming 300 ticks is a whole rotation

        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                if (!rightBumperCommandSent) { // Only set target and mode once per button press
                    motor.setTargetPosition(TARGET_POSITION_KICKER);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setPower(1); // Use positive power, RUN_TO_POSITION handles direction
                    rightBumperCommandSent = true; // Mark command as sent
                    leftBumperCommandSent = false; // Reset other command flag
                }
            } else if (gamepad1.left_bumper) { // Check for left bumper
                if (!leftBumperCommandSent) { // Only set target and mode once per button press
                    motor.setTargetPosition(TARGET_POSITION_ROTATION);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setPower(1); // Use positive power, RUN_TO_POSITION handles direction
                    leftBumperCommandSent = true; // Mark command as sent
                    rightBumperCommandSent = false; // Reset other command flag
                }
            } else {
                rightBumperCommandSent = false;
                leftBumperCommandSent = false;

            }

            // Get the current position of the motor
            int position = motor.getCurrentPosition();

            // Show the position of the motor on telemetry
            telemetry.addData("Encoder Position", position);
            telemetry.addData("Motor Target", motor.getTargetPosition());
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Motor Mode", motor.getMode());
            telemetry.addData("Motor Busy", motor.isBusy());
            telemetry.addData("Right bumper pressed?", rightBumperCommandSent);
            telemetry.addData("Left bumper pressed?", leftBumperCommandSent);

            telemetry.update();

            // Add a brief pause to prevent overwhelming the control hub/RC
            // Thread.sleep(5);
        }

        // Ensure motor stops and is in a default mode at the end of the OpMode
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}