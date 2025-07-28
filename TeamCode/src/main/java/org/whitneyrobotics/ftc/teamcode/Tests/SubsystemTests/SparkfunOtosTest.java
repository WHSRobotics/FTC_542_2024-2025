package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.SparkfunOtos;

import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "1sparkfun")
public class SparkfunOtosTest extends OpModeEx {

    public SparkfunOtos otos;
    public DcMotorEx fL, bL, fR, bR;

    @Override
    public void initInternal() {
        otos = new SparkfunOtos(hardwareMap);
        fL = hardwareMap.get(DcMotorEx.class, "fL");
        fR = hardwareMap.get(DcMotorEx.class, "fR");
        bL = hardwareMap.get(DcMotorEx.class, "bL");
        bR = hardwareMap.get(DcMotorEx.class, "bR");


        otos.configureOtos();
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("x-posit", otos.myOtos.getPosition().x);
        telemetryPro.addData("y-posit", otos.myOtos.getPosition().y);
        double robotHeading = otos.myOtos.getPosition().h;

        telemetryPro.addData("HEADING: ", robotHeading);


        double y = gamepad1.LEFT_STICK_Y.value();
        double x = gamepad1.LEFT_STICK_X.value();
        double rx = gamepad1.RIGHT_STICK_X.value();

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        fL.setPower(frontLeftPower);
        bL.setPower(backLeftPower);
        fR.setPower(frontRightPower);
        bR.setPower(backRightPower);

        telemetryPro.update();
    }
}