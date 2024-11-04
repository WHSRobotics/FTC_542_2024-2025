package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;

@Config
@TeleOp(name = "PIDFTuner for rotator")
public class PIDFTunerTest extends OpMode {
    private PIDController controller;

    public static double p = 0.005, i = 0, d = 0.00028;
    public static double f= 0;
    public static int target = 0;
    private final double ticks_in_degrees = 700/180.0;
    private DcMotorEx arm_motor;
    private ControlConstants control_constants;
    @Override
    public void init() {
        control_constants = new ControlConstants(p,i,d);
        controller = new PIDController(control_constants);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        arm_motor = hardwareMap.get(DcMotorEx.class,"rotator");
//        arm_motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        arm_motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {
        arm_motor.setDirection(DcMotorSimple.Direction.FORWARD);

        controller.setConstants(control_constants);
        int armPos = -1*arm_motor.getCurrentPosition();
        double error = target- armPos;
        controller.calculate(error);
        double pid = controller.getOutput();
        double ff = Math.cos(Math.toRadians((target/ ticks_in_degrees)))*f;
        double power  = pid + ff;
        arm_motor.setPower(power);



        telemetry.addData("Current Position", armPos);
        telemetry.addData("Target Position", target);

        telemetry.update();

    }
}
