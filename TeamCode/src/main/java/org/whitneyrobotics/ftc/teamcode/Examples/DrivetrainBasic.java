package org.whitneyrobotics.ftc.teamcode.Examples;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Subsystems.WHSIMU;

import java.util.function.UnaryOperator;

@TeleOp(name="Demo")
public class DrivetrainBasic extends OpModeEx {
    DcMotorEx fL, fR, bL, bR;
    IMU imu;
    boolean fieldCentric = true;
    @Override
    public void initInternal() {
        fL = hardwareMap.get(DcMotorEx.class, "fL");
        fR = hardwareMap.get(DcMotorEx.class, "fR");
        bL = hardwareMap.get(DcMotorEx.class, "bL");
        bR = hardwareMap.get(DcMotorEx.class, "bR");
        hardwareMap.getAll(DcMotorEx.class).forEach(m -> {
            m.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        });
        //TODO: Check if this is correct
        fR.setDirection(DcMotorEx.Direction.REVERSE);
        bR.setDirection(DcMotorEx.Direction.REVERSE);
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT)
        ));
        setBulkReadBehavior(BULK_READ_BEHAVIOR.ONE_FRAME_PER_LOOP);
        gamepad1.A.onPress(e -> fieldCentric = !fieldCentric);
        gamepad1.B.onPress(e->imu.resetYaw());
    }

    @Override
    protected void loopInternal() {
        double angle = (fieldCentric ? imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) : 0);
        UnaryOperator<Float> powerScaling = x -> (float)Math.pow(x, 3);
        if(gamepad1.BUMPER_LEFT.value()){
            powerScaling = x -> x / 2;
        }

        double cY = powerScaling.apply(gamepad1.LEFT_STICK_Y.value());
        double cX = powerScaling.apply(gamepad1.LEFT_STICK_X.value());
        double vOmega = powerScaling.apply(gamepad1.RIGHT_STICK_X.value());

        double vX = cX*Math.cos(angle) - cY*Math.sin(angle);
        double vY = cX*Math.sin(angle) + cY*Math.cos(angle);
        double denom = Math.max(1,(Math.abs(vY)+Math.abs(vX)+Math.abs(vOmega)));

        fL.setPower((vY + vX + vOmega)/denom);
        fR.setPower((vY - vX - vOmega)/denom);
        bL.setPower((vY - vX + vOmega)/denom);
        bR.setPower((vY + vX - vOmega)/denom);
        telemetryPro.addData("Field Centric", fieldCentric, LineItem.Color.AQUA);
        telemetryPro.addData("Heading", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES), LineItem.Color.LIME);
        telemetryPro.addData("Slow mode", gamepad1.BUMPER_LEFT.value(), LineItem.Color.YELLOW);
    }
}
