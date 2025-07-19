package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.whitneyrobotics.ftc.teamcode.Subsystems.ElbowWrist;
import org.whitneyrobotics.ftc.teamcode.Subsystems.VerticalSlides;
import org.whitneyrobotics.ftc.teamcode.Subsystems.outtakeServo;

public class AutoPaths {

    public static ElbowWrist elbow;
    public static outtakeServo OuttakeServo;

    public static VerticalSlides verticalSlides;
    public static void setAutoSubsystems(ElbowWrist el, outtakeServo out, VerticalSlides slides) {
        elbow = el;
        OuttakeServo = out;
        verticalSlides = slides;
    }
    public static final TrajectorySequence BlueBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(-15, 60, Math.toRadians(90)))
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 50)
                .waitSeconds(3) // Increased wait time to allow initial subsystems to move
                .addTemporalMarker(0.5, () -> { // Slightly delayed to ensure execution
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                    verticalSlides.setGoal();
                    elbow.updateAuto();
                    elbow.runAuto();
                })

                .lineToLinearHeading(new Pose2d(-4, 35, Math.toRadians(90)))
                .addTemporalMarker(4, () -> { // Extended timing for slides to reset
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                    verticalSlides.setGoal();
                })
                .addTemporalMarker(5.1, () -> { // Extended timing for slides to reset
                    OuttakeServo.updateState();
                })
                .waitSeconds(2.5)
                .lineToLinearHeading(new Pose2d(-33, 50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-33, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-45, 0, Math.toRadians(-85)))
                .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(-85)))
                .lineToLinearHeading(new Pose2d(-45, 35, Math.toRadians(-85)))
                .waitSeconds(2) // Increased wait for subsystems to catch up
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 25)
                .lineToLinearHeading(new Pose2d(-45, 54.5, Math.toRadians(-85)))
                .waitSeconds(2) // Extended for slides to complete motion
                .addTemporalMarker(15, () -> { // More time for slides transition
                    verticalSlides.setState(VerticalSlides.AngleTicks.MIN);
                })
                .addTemporalMarker(17, () -> { // More time for slides transition
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(18.5, () -> { // More time for slides transition
                    verticalSlides.setState(VerticalSlides.AngleTicks.HALF);
                })
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 45)
                .lineToLinearHeading(new Pose2d(0, 43, Math.toRadians(0)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(-7, 37.5, Math.toRadians(90)))
                .addTemporalMarker(21, () -> { // Extended slide movement buffer
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .waitSeconds(1.2) // Allow slides to fully reach ONE
                .addTemporalMarker(23, () -> { // Adjusted timing
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                })
                .addTemporalMarker(23.7, () -> { // Adjusted timing
                    OuttakeServo.updateState();
                })
                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(-42, 56, Math.toRadians(-85)))

                .build();
    }
    public static final TrajectorySequence RedBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(15, -60, Math.toRadians(-90)))
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 50)
                .waitSeconds(3) // Increased wait time to allow initial subsystems to move
                .addTemporalMarker(0.5, () -> { // Slightly delayed to ensure execution

                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                    verticalSlides.setGoal();
                })

                .lineToLinearHeading(new Pose2d(4, -35, Math.toRadians(-90)))
                .addTemporalMarker(4, () -> { // Extended timing for slides to reset
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                    verticalSlides.setGoal();
                })
                .addTemporalMarker(5.1, () -> { // Extended timing for slides to reset
                    OuttakeServo.updateState();
                })
                .waitSeconds(2.5)
                .lineToLinearHeading(new Pose2d(37, -50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(37, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(45, 0, Math.toRadians(85)))
                .lineToLinearHeading(new Pose2d(45, -50, Math.toRadians(85)))
                .lineToLinearHeading(new Pose2d(45, -35, Math.toRadians(85)))
                .waitSeconds(2) // Increased wait for subsystems to catch up
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 25)
                .lineToLinearHeading(new Pose2d(42, -56, Math.toRadians(85)))
                .waitSeconds(2) // Extended for slides to complete motion
                .addTemporalMarker(17, () -> { // More time for slides transition
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(18.5, () -> { // More time for slides transition
                    verticalSlides.setState(VerticalSlides.AngleTicks.HALF);
                })
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 45)
                .lineToLinearHeading(new Pose2d(0, -43, Math.toRadians(0)))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(6, -31, Math.toRadians(-90)))
                .addTemporalMarker(21, () -> { // Extended slide movement buffer
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);

                })
                .waitSeconds(2) // Allow slides to fully reach ONE
                .addTemporalMarker(23, () -> { // Adjusted timing
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                })
                .addTemporalMarker(25, () -> { // Adjusted timing
                    OuttakeServo.updateState();
                })
                .waitSeconds(1.5)
//                .lineToLinearHeading(new Pose2d(42, -56, Math.toRadians(85)))

                .build();
    }
}
