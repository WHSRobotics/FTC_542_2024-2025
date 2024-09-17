package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests.Drivetrains;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "TankDrive", group = "Drivetrains")
public class TankDrive extends LinearOpMode{

    private DcMotor fL;
    private DcMotor fR;
    private DcMotor bL;
    private DcMotor bR;
    private IMU imu;
    @Override
    public void runOpMode() throws InterruptedException {
        fL = hardwareMap.dcMotor.get("fL");
        fR = hardwareMap.dcMotor.get("fR");
        bL = hardwareMap.dcMotor.get("bL");
        bR = hardwareMap.dcMotor.get("bR");

        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {

            double robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double forward = (-gamepad1.left_stick_y + -gamepad1.right_stick_y) / 2;
            double rotate = (gamepad1.right_stick_x - gamepad1.left_stick_x) / 2;

            double tempforward = forward * Math.cos(robotHeading) - rotate * Math.sin(robotHeading);
            rotate = forward * Math.sin(robotHeading) + rotate * Math.cos(robotHeading);
            forward = tempforward;

            double leftPower = forward + rotate;
            double rightPower = forward - rotate;

            fL.setPower(leftPower);
            bL.setPower(leftPower);
            fR.setPower(rightPower);
            fL.setPower(rightPower);
        }


    }
}
