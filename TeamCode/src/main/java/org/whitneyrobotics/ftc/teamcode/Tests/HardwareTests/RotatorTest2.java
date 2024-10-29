package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RotationSlides;

@Config
@TeleOp(name = "RotatorTest2", group = "Control")
public class RotatorTest2 extends OpModeEx {
    private PIDController controller;
    private MotionProfileTrapezoidal motionProfile;
    private NanoStopwatch stopwatch;
    private boolean telemetryInitialized = false;

    public static double p = 0.005, i = 0, d = 0;
    public static double f = 0.002;

    private RotationSlides arm;
    @Override
    public void initInternal() {

        arm = new RotationSlides(hardwareMap);
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void start() {
        // Reset stopwatch and create a new motion profile for the target
        arm.setState(RotationSlides.AngleTicks.ZERO);
        arm.set_Goal();
    }

    @Override
    public void loopInternal() {
        gamepad1.A.onPress(()->{
            arm.setState((RotationSlides.AngleTicks.DROP));
            arm.set_Goal();
        });
        
        arm.update();
        // Telemetry updates
//        telemetry.addData("Current Position", currentPos);
//        telemetry.addData("Target Position", targetPosition);
//        telemetry.addData("Desired Position", desiredPosition);
//        telemetry.addData("Desired Velocity", desiredVelocity);
//        telemetry.update();
    }
}
