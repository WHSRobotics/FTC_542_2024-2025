package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelImpl;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name="BreakBeamTest")
public class BreakBeamTest extends OpModeEx {

    private boolean state;
    private DigitalChannel beam;

    private FtcDashboard dashboard;
    @Override
    public void initInternal() {
        beam = hardwareMap.get(DigitalChannel.class,"breakBeam");
        beam.setMode(DigitalChannel.Mode.INPUT);
        dashboard = FtcDashboard.getInstance();
    }

    @Override
    protected void loopInternal() {
        state = beam.getState();
        telemetryPro.addData("State:",state);
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("State:",state ? "Beam not broken" : "Beam Broken ");
        dashboard.sendTelemetryPacket(packet);
    }
}
