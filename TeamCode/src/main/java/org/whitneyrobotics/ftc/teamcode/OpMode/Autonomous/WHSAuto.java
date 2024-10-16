package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TelemetryPro;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;

import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name = "AutoWHS")
public class WHSAuto extends OpModeEx {
    CenterstageMecanumDrive drive;

    @Override
    public void initInternal() {
        drive = new CenterstageMecanumDrive(hardwareMap);
    }

    @Override
    protected void loopInternal() {
        TrajectorySequence smth_awesome = drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
                .lineToLinearHeading(new Pose2d(19.7, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(19.7, 5, Math.toRadians(0)))

                .addSpatialMarker(new Vector2d(30, 30), () -> {
                    // Add some action here, like moving a servo or motor
                })
                .build();

        drive.followTrajectorySequenceAsync(smth_awesome);

        while (drive.isBusy()) {
            drive.update();
            telemetryPro.update();
        }
    }
}