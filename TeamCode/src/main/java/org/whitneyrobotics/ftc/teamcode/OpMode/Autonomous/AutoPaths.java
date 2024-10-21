package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;

public class AutoPaths {
    public static final TrajectorySequence BlueBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(0,0,0))
                .build();
    }
}
