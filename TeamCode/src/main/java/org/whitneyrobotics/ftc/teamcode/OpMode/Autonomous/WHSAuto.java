package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "AutoWHS")
public class WHSAuto extends OpModeEx {
    CenterstageMecanumDrive drive;

    @Override
    public void initInternal() {

    }

    @Override
    protected void loopInternal() {
        TrajectorySequence smth_awesome = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(12,18, Math.toRadians(45)))
                .lineToLinearHeading(new Pose2d(24,36,Math.toRadians(45)))
                .build();
    }
}
