package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests.Drivetrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "OmniDrive", group = "Drivetrains")
public class OmniDrive extends LinearOpMode {

    private DcMotor fL;
    private DcMotor fR;
    private DcMotor bL;
    private DcMotor bR;

    private IMU imu;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        fL = hardwareMap.get(DcMotor.class, "fL");
        fR = hardwareMap.get(DcMotor.class, "fR");
        bL = hardwareMap.get(DcMotor.class, "bL");
        bR = hardwareMap.get(DcMotor.class, "bR");

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;


            double robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double tempX = x * Math.cos(robotHeading) - y * Math.sin(robotHeading);
            double tempY = x * Math.sin(robotHeading) + y * Math.cos(robotHeading);
            x = tempX;
            y = tempY;

            double frontLeftPower = y + x + rotate;
            double frontRightPower = y - x - rotate;
            double backLeftPower = y - x + rotate;
            double backRightPower = y + x - rotate;

            fL.setPower(frontLeftPower);
            fR.setPower(frontRightPower);
            bL.setPower(backLeftPower);
            bR.setPower(backRightPower);

        }
    }
}
