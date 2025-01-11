package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import static android.service.autofill.Validators.not;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.intakeServo;

@TeleOp(name = "ClawBeamTest")
public class ClawBeamTest extends OpModeEx {
    
    DigitalChannel breakBeam;
    intakeServo claw;
    boolean override = false;

    FtcDashboard dashboard;
    @Override
    public void initInternal() {
        breakBeam=hardwareMap.get(DigitalChannel.class,"breakBeam");
        claw=new intakeServo(hardwareMap);
        dashboard = FtcDashboard.getInstance();
    }

    @Override
    protected void loopInternal() {
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("State:",breakBeam.getState() ? "Beam not broken" : "Beam Broken ");
        claw.beamBreakUpdate(!(breakBeam.getState()),gamepad1);
        claw.setOverride(gamepad1);
        telemetryPro.addData("Override",claw.getOverride());
        telemetryPro.update();
        claw.setOverride(gamepad1);
        dashboard.sendTelemetryPacket(packet);
        claw.run();
    }
}
