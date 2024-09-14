package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp (name = "WilliamILTDroneTest")
public class WilliamM3DroneTest extends OpModeEx {
    public static double currentPositionAngle = 0.50;
    public static double currentPositionLaunch = 0.50;

    Servo angle = hardwareMap.get(Servo.class, "test");
    Servo launch = hardwareMap.get(Servo.class, "testTwo");

    @Override
    public void initInternal() {
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        dashboardTelemetry.setMsTransmissionInterval(15);
    }

    @Override
    protected void loopInternal() {
        angle.setPosition(currentPositionAngle);
        launch.setPosition(currentPositionLaunch);

        telemetryPro.addData("Angle Position", currentPositionAngle);
        telemetryPro.addData("Launch Position", currentPositionLaunch);
    }
}
