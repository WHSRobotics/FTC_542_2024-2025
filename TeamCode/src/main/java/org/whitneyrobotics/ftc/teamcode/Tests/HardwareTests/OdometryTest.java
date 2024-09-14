package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Geometry.Coordinate;
import org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry.EncoderConverter;
import org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry.HWheelOdometry;
import org.whitneyrobotics.ftc.teamcode.Tests.Test;

@RequiresApi(api = Build.VERSION_CODES.N)
@Test(name="Odometry Test")
@TeleOp(name="Odometry Test Updated", group="HardwareTests")
public class OdometryTest extends OpModeEx {
    HWheelOdometry odometry;

    @Override
    public void initInternal() {
        odometry = new HWheelOdometry(
                new EncoderConverter.EncoderConverterBuilder()
                        .setEncoderMotor(hardwareMap.get(DcMotorEx.class,"driveBL"))
                        .setTicksPerRev(8192)
                        .setUnit(DistanceUnit.INCH)
                        .setWheelDiameter(2)
                        .setRevEncoder(true)
                        .build(),
                new EncoderConverter.EncoderConverterBuilder()
                        .setEncoderMotor(hardwareMap.get(DcMotorEx.class,"driveFR"))
                        .setTicksPerRev(8192)
                        .setUnit(DistanceUnit.INCH)
                        .setWheelDiameter(2)
                        .setRevEncoder(true)
                        .build(),
                new EncoderConverter.EncoderConverterBuilder()
                        .setEncoderMotor(hardwareMap.get(DcMotorEx.class,"driveBR"))
                        .setTicksPerRev(8192)
                        .setUnit(DistanceUnit.INCH)
                        .setWheelDiameter(2)
                        .setRevEncoder(true)
                        .build(),
                12.23,
                6.04
        );
        initializeDashboardTelemetry(10);
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
    }

    @Override
    protected void loopInternal() {
        odometry.update();
        Coordinate currentPos = odometry.getCurrentPosition();
        telemetryPro.addLine(currentPos.toString(), LineItem.Color.AQUA);
        packet.fieldOverlay().setStroke("red");
        packet.fieldOverlay().setStrokeWidth(2);
        packet.fieldOverlay().strokeCircle(currentPos.getX(), currentPos.getY(), 14);

        double theta = currentPos.getHeading();
        packet.fieldOverlay()
                .setStrokeWidth(1)
                .setStroke("#00000044")
                .setStroke("blue")
                .strokeLine(currentPos.getX(), currentPos.getY(), currentPos.getX()+7*Math.sin(theta), currentPos.getY()+7*Math.cos(theta))
                .setStroke("red")
                .strokeLine(currentPos.getX(), currentPos.getY(), currentPos.getX(), currentPos.getY()+7);
    }
}
