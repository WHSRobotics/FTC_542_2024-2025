package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.AutoSetupTesting.Tests;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RobotImpl;

@Autonomous(name = "AutoWHS")
public class WHSAuto extends OpModeEx {
    IntoTheDeepMecanumDrive drive;
    RobotImpl robot;

    @Override
    public void initInternal() {
        RobotImpl.init(hardwareMap);
        robot = RobotImpl.getInstance();
        telemetryPro.useTestManager()
                .addTest("Gamepad 1 Initialization", () -> Tests.assertGamepadSetup(gamepad1, "Gamepad 1"))
                .addTest("Gamepad 2 Initialization", () -> Tests.assertGamepadSetup(gamepad2, "Gamepad 2"))
                .addTest("Battery voltage test", () -> Tests.assertBatteryCharged(hardwareMap.get(LynxModule.class, "Control Hub")));

        drive = new IntoTheDeepMecanumDrive(hardwareMap);
    }

    @Override
    protected void loopInternal() {


        while (drive.isBusy()) {
            drive.update();
            telemetryPro.update();
        }
    }
}