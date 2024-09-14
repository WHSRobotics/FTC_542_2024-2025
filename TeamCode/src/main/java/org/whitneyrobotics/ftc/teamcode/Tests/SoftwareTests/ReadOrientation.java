package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.RobotDataUtil;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.WHSRobotData;

@Autonomous(group="Z")
public class ReadOrientation extends OpModeEx {
    @Override
    public void initInternal() {
        RobotDataUtil.load(WHSRobotData.class);
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addLine(String.valueOf(WHSRobotData.heading));
    }
}
