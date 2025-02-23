package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.whitneyrobotics.ftc.teamcode.Subsystems.AutoElbowWrist;
import org.whitneyrobotics.ftc.teamcode.Subsystems.ElbowWrist;
import org.whitneyrobotics.ftc.teamcode.Subsystems.VerticalSlides;
import org.whitneyrobotics.ftc.teamcode.Subsystems.outtakeServo;

public class AutoPaths {

    public static AutoElbowWrist elbow;
    public static outtakeServo OuttakeServo;

    public static VerticalSlides verticalSlides;
    public static void setAutoSubsystems(AutoElbowWrist el, outtakeServo out, VerticalSlides slides) {
        elbow = el;
        OuttakeServo = out;
        verticalSlides = slides;
    }
    public static final TrajectorySequence BlueBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(-15, 60, Math.toRadians(90)))
                .waitSeconds(1) // Reduced from 2 seconds
                .addTemporalMarker(0.5,()->{
                    elbow.updateAuto();
                    elbow.runAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.HALF);
                    verticalSlides.setGoal();
                })
                .lineToLinearHeading(new Pose2d(-4, 34.5, Math.toRadians(90)))
                .addTemporalMarker(2.5,()->{ // Reduced time delay
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .addTemporalMarker(3,()->{
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(4,()->{
                    elbow.updateAuto();
                    elbow.runAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                    verticalSlides.setGoal();
                })
                .waitSeconds(1) // Reduced from 2 seconds
                .lineToLinearHeading(new Pose2d(-33, 50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-33, 0, Math.toRadians(0)))
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 100)
                .lineToLinearHeading(new Pose2d(-45, 0, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-45, 35, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-45, 0, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-55, 0, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-55, 50, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-55, 30, Math.toRadians(-90)))
                .addTemporalMarker(15,()->{ // Adjusted timing
                    elbow.wallAutoUpdate();
                    elbow.wallAutoRun();
                    OuttakeServo.updateState();
                })
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 40) // Increased speed slightly
                .lineToLinearHeading(new Pose2d(-55, 50, Math.toRadians(-90)))
                .waitSeconds(2) // Reduced from 3 seconds
                .addTemporalMarker(18,()->{ // Adjusted timing
                    elbow.updateAuto();
                    elbow.runAuto();
                })
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 60) // Increased speed
                .lineToLinearHeading(new Pose2d(-4, 45, Math.toRadians(90)))
                .build();
    }
    public static final TrajectorySequence RedBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(15, -60, Math.toRadians(-90)))
                .waitSeconds(2)
                .addTemporalMarker(0.5,()->{
                    elbow.updateAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);

                })

                .lineToLinearHeading(new Pose2d(4, -38, Math.toRadians(-90)))
                .addTemporalMarker(5,()->{
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                })

                .addTemporalMarker(7,()->{
                    elbow.updateAuto();
                })
                .waitSeconds(4.5)

                .lineToLinearHeading(new Pose2d(37, -50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(37, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(45, 0, Math.toRadians(85)))
                .lineToLinearHeading(new Pose2d(45, -50, Math.toRadians(85)))
                .lineToLinearHeading(new Pose2d(45, -35, Math.toRadians(85)))
                .addTemporalMarker(15,()->{
                    elbow.updateAuto();
                    OuttakeServo.updateState();
                })
                .waitSeconds(5)
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 20)
                .lineToLinearHeading(new Pose2d(45, -56, Math.toRadians(90)))
                .waitSeconds(3.5)

                .addTemporalMarker(21,()->{
                    OuttakeServo.updateState();

                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .lineToLinearHeading(new Pose2d(0, -43, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(-90)))

                .waitSeconds(1)
                .addTemporalMarker(28,()->{
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                })


                .build();
    }
}
