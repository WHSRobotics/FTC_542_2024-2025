package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.RobotDataUtil;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.WHSRobotData;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TelemetryData;
import org.whitneyrobotics.ftc.teamcode.Subsystems.WHSIMU;
import org.whitneyrobotics.ftc.teamcode.Tests.Test;

@TeleOp(name="AutoHandoff", group="Software Tests")
@Test(name="AutoTeleHandoff")
public class AutoTeleHandoffTest extends OpModeEx {
    public WHSIMU imu;
    @TelemetryData
    public double heading;
    @Override
    public void initInternal() {
        imu = new WHSIMU(hardwareMap);
    }

    @Override
    protected void loopInternal() {
        heading = imu.getHeadingYaw();
        telemetryPro.addData("heading",Math.toDegrees(heading));
    }

    @Override
    public void stop() {
        WHSRobotData.heading = heading;
        RobotDataUtil.save(WHSRobotData.class);
    }
}
