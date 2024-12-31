package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BreakBeam {
    DigitalChannel breakBeam;
    boolean state;
    FtcDashboard dashboard;
    public BreakBeam(HardwareMap hardwareMap){
        breakBeam=hardwareMap.get(DigitalChannel.class,"breakBeam");
        dashboard=FtcDashboard.getInstance();
    }
    public boolean objectDetected(){
        state=breakBeam.getState();
        TelemetryPacket packet=new TelemetryPacket();
        packet.put("Break Beam State:", (state?"Sample not detected":"Sample detected"));
        dashboard.sendTelemetryPacket(packet);
        return !(state);
    }
}
