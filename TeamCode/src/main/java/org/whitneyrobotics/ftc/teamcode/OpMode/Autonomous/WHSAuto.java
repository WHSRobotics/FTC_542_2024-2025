package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TelemetryPro;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;

@TeleOp(name = "AutoWHS")
public class WHSAuto extends OpModeEx {
    CenterstageMecanumDrive drive;

    @Override
    public void initInternal() {
    }

    @Override
    protected void loopInternal() {
        TrajectorySequence smth_awesome = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(40,40))
                .addSpatialMarker(new Vector2d(30,30), () ->{
                })
                .build();
    }
}
