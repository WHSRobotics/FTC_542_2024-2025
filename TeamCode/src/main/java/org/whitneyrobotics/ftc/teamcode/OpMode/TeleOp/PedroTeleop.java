package org.whitneyrobotics.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.pedroPathing.FutureDrive;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "work pls")
public class PedroTeleop extends OpModeEx {

    FutureDrive drive;

    @Override
    public void initInternal() {
        drive = new FutureDrive(hardwareMap);
        drive.initialize();
    }

    @Override
    public void start() {
        drive.startDrive();
    }

    @Override
    protected void loopInternal() {
        gamepad1.BUMPER_RIGHT.onPress(() -> drive.driveMode());
        drive.update(gamepad1);
        telemetryPro.addData("robotCentric: ", drive.getDriveMode());
    }
}
