package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;


@TeleOp(name = "AngleTest")
public class AngleTest extends OpModeEx {
    public IMU imu;
    @Override
    public void initInternal() {
        imu = hardwareMap.get(IMU.class, "imu");
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("IMU", imu.getRobotYawPitchRollAngles().getYaw());
    }
}
