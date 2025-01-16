package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDControlledMotor;
import org.whitneyrobotics.ftc.teamcode.Tests.Test;

@RequiresApi(api = Build.VERSION_CODES.N)
@TeleOp(name = "PID Controlled Motor Test", group="Hardware Tests")
@Config
@Test(name="PID Controlled Motor Test")
public class PIDControlledMotorTest extends OpModeEx {
    public static double TARGET_VEL, kI, kD;
    public static double kP = 1.0d;

    PIDControlledMotor motor;
    @Override
    public void initInternal() {
        motor.reloadCoefficients(kP, kI, kD);
        motor = new PIDControlledMotor(hardwareMap.get(DcMotorEx.class, "rotator"), 3000);
        initializeDashboardTelemetry(50);
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
    }

    @Override
    public void startInternal() {
        motor.reset();
    }

    @Override
    protected void loopInternal() {
        motor.setTargetVelocity(TARGET_VEL);
        motor.update();
        telemetryPro.addData("target",TARGET_VEL);
        telemetryPro.addData("current",motor.getMotor().getVelocity(AngleUnit.RADIANS));
        telemetryPro.addData("output",motor.controller.getOutput());
    }
}
