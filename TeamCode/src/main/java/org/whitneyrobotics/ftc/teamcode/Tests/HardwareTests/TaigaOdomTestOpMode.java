package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "TaigaOdomTest")
public class TaigaOdomTestOpMode extends OpModeEx {
    public TaigaOdometryTesting taigaOdom;
    @Override
    public void initInternal() {
        taigaOdom = new TaigaOdometryTesting(hardwareMap);
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("X Change", taigaOdom.getXChange());
        telemetryPro.addData("Y Change", taigaOdom.getYChange());
        telemetryPro.addData("Rotation", taigaOdom.getRotationChange());
    }
}
