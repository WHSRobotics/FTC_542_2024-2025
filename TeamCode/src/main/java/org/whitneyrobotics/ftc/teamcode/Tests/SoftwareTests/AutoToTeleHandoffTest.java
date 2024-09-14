package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.RobotDataUtil;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.WHSRobotData;
import org.whitneyrobotics.ftc.teamcode.Subsystems.WHSIMU;
import org.whitneyrobotics.ftc.teamcode.Tests.Test;

@TeleOp
@Test(name="AutoToTeleHandoff", autoTerminateAfterSeconds = 10)
public class AutoToTeleHandoffTest extends OpModeEx {
    WHSIMU imu;
    public double heading;

    @Override
    public void initInternal() {
        imu = new WHSIMU(hardwareMap);
        imu.zeroCalibrationOffset();
    }

    @Override
    protected void loopInternal() {
        heading = imu.getHeadingYaw();
    }

    @Override
    public void stop() {
        WHSRobotData.heading = heading;
        RobotDataUtil.save(WHSRobotData.class);
    }
}
