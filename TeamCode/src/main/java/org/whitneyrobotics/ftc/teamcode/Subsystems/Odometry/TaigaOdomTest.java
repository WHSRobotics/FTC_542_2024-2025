package org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Tests.Test;

@Test (name = "Taiga Odom Test")
public class TaigaOdomTest extends OpMode {
    private DcMotorEx fL;
    private DcMotorEx fR;
    private DcMotorEx bL;
    private DcMotorEx bR;

    private final double DEG_TO_RAD = Math.PI / 180;
    private final double RAD_TO_DEG = 180 / Math.PI;
    private final double TICKS_PER_REVOLUTION = 2048; // ticks
    private final double ENCODER_DIAMETER = 1.5; // inches
    private final double TICKS_TO_IN = 2 * Math.PI * (ENCODER_DIAMETER / 2) / TICKS_PER_REVOLUTION;

    private double calculateAngle(String Units, double rotation){
        if (Units == "DEG"){
            return (rotation / TICKS_PER_REVOLUTION) * 360;
        }

        if (Units == "RAD"){
            return ((rotation / TICKS_PER_REVOLUTION) * 360) * DEG_TO_RAD;
        }

        return 0;
    }
    @Override
    public void init() {
        DcMotorEx fL = hardwareMap.get(DcMotorEx.class, "fL");
        DcMotorEx fR = hardwareMap.get(DcMotorEx.class, "fR");
        DcMotorEx bL = hardwareMap.get(DcMotorEx.class, "bL");
        DcMotorEx bR = hardwareMap.get(DcMotorEx.class, "bR");

        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);

        fL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // X Direction on Odom
        fR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Y Direction on Odom
        bL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Heading or Rotation on Odom

        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // X
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Y
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Heading or Rotation
    }

    @Override
    public void loop() {
        double movementInXDirection = Math.abs(fL.getCurrentPosition());
        double movementInYDirection = Math.abs(fR.getCurrentPosition());
        double rotation = Math.abs(bL.getCurrentPosition());

        double xPosition = movementInXDirection < 0 ? - movementInXDirection * TICKS_TO_IN : movementInXDirection * TICKS_TO_IN;
        double yPosition = movementInYDirection < 0 ? - movementInYDirection * TICKS_TO_IN : movementInYDirection * TICKS_TO_IN;
        double heading = rotation < 0 ? - calculateAngle("DEG", rotation) : calculateAngle("DEG", rotation);

        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;

        double r = gamepad1.right_stick_x;

        double adjustment = Math.max(Math.abs(y + x + r), 1.0);
        double slow = gamepad1.left_bumper ? 2 : 1;

        fL.setPower((y - x - r) / (adjustment * slow));
        fR.setPower((y + x + r) / (adjustment * slow));
        bL.setPower((- y - x + r) / (adjustment * slow));
        bR.setPower((- y + x - r) / (adjustment * slow));

        telemetry.addData("X Position", xPosition);
        telemetry.addData("Y Position", yPosition);
        telemetry.addData("Heading", heading);
    }
}
