package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import android.hardware.SensorDirectChannel;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

public class BreakBeam extends OpModeEx {

    private SensorDirectChannel breakbeam;
    @Override
    public void initInternal() {
        breakbeam=hardwareMap.get(SensorDirectChannel.class,"breakbeam");
    }

    @Override
    protected void loopInternal() {
        if breakbeam.
    }
}