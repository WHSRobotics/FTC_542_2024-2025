package org.whitneyrobotics.ftc.teamcode.OpMode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Geometry.LinAlg.Vector;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequenceBuilder;

public class WHSAuto extends OpModeEx {
    CenterstageMecanumDrive drive;


    @Override
    public void initInternal() {

    }

    @Override
    protected void loopInternal() {
        Trajectory path1 = drive.trajectoryBuilder(new Pose2d())
                .forward(30)
                .splineTo(new Vector2d(0,0),25)
                .splineToConstantHeading(new Vector2d(0,0),20)
                .lineToLinearHeading(new Pose2d(0,0,Math.toRadians(45)))
                .build();

    }
}
