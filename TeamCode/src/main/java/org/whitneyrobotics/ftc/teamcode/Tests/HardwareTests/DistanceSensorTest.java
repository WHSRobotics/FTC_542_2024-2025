package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
@Autonomous(group="Z")
public class DistanceSensorTest extends OpModeEx {
    public Rev2mDistanceSensor left, right;

    @Override
    public void initInternal() {
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        left = hardwareMap.get(Rev2mDistanceSensor.class,"distanceLeft");
        right = hardwareMap.get(Rev2mDistanceSensor.class,"distanceRight");
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("left",left.getDistance(DistanceUnit.CM));
        telemetryPro.addData("right",right.getDistance(DistanceUnit.CM));
    }
}
