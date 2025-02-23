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
                .lineToLinearHeading(new Pose2d(-4, 35, Math.toRadians(90)))
                .addTemporalMarker(2.5,()->{ // Reduced time delay
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .addTemporalMarker(3.7,()->{
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(4.5,()->{
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
//                .lineToLinearHeading(new Pose2d(-45, 0, Math.toRadians(-90)))
//                .lineToLinearHeading(new Pose2d(-55, 0, Math.toRadians(-90)))
//                .lineToLinearHeading(new Pose2d(-55, 50, Math.toRadians(-90)))
//                .lineToLinearHeading(new Pose2d(-55, 30, Math.toRadians(-90)))
                .addTemporalMarker(10.5,()->{ // Adjusted timing
                    elbow.wallAutoUpdate();
                    elbow.wallAutoRun();
//                    OuttakeServo.updateState();
                })
                .waitSeconds(1)
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 40) // Increased speed slightly
                .lineToLinearHeading(new Pose2d(-45, 51.5, Math.toRadians(-90)))
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 60) // Increased speed
                .addTemporalMarker(12.5,()->{ // Adjusted timing
                    OuttakeServo.updateState();
                })
                .waitSeconds(1) // Reduced from 3 seconds
                .addTemporalMarker(13,()->{ // Adjusted timing
                    elbow.updateAuto();
                    elbow.runAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.THIRD);
                    verticalSlides.setGoal();
                })
                .lineToLinearHeading(new Pose2d(-4, 48, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(-4, 36, Math.toRadians(90)))
                .waitSeconds(3)
                .addTemporalMarker(16.5, ()->{
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .addTemporalMarker(17.5, ()->{
                    OuttakeServo.updateState();
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                })
                .addTemporalMarker(19.5, ()->{
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(20.5, () ->{
                    elbow.wallAutoUpdate();
                    elbow.wallAutoRun();
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                    verticalSlides.setGoal();
                })
                .lineToLinearHeading(new Pose2d(-45, 35, Math.toRadians(-90)))
                .addTemporalMarker(21.5, () ->{
                    elbow.wallAutoUpdate();
                    elbow.wallAutoUpdate();
                })
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 40) // Increased speed slightly
                .lineToLinearHeading(new Pose2d(-45, 53, Math.toRadians(-90)))
//                .waitSeconds(1)
//                .addTemporalMarker(22.5, () ->{
//                    OuttakeServo.updateState();
//                    elbow.updateAuto();
//                    elbow.runAuto();
//                })
                .build();
    }
    public static final TrajectorySequence RedBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(15, -59.5, Math.toRadians(-90)))
                .waitSeconds(1) // Reduced from 2 seconds
                .addTemporalMarker(0.5, () -> {
                    elbow.updateAuto();
                    elbow.runAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.HALF);
                    verticalSlides.setGoal();
                })
                .lineToLinearHeading(new Pose2d(4, -35, Math.toRadians(-90)))
                .addTemporalMarker(2.5, () -> { // Reduced time delay
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .addTemporalMarker(3.5, () -> {
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(4.5, () -> {
                    elbow.updateAuto();
                    elbow.runAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
                    verticalSlides.setGoal();
                })
                .waitSeconds(1) // Reduced from 2 seconds
                .lineToLinearHeading(new Pose2d(33, -52.5, Math.toRadians(-180)))
                .lineToLinearHeading(new Pose2d(33, 0, Math.toRadians(-180)))
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 100)
                .lineToLinearHeading(new Pose2d(45, 0, Math.toRadians(-270)))
                .lineToLinearHeading(new Pose2d(45, -52.5, Math.toRadians(-270)))
                .lineToLinearHeading(new Pose2d(45, -15, Math.toRadians(-270)))
                .addTemporalMarker(12, () -> { // Adjusted timing
                    elbow.wallAutoUpdate();
                    elbow.wallAutoRun();
                })
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 40) // Increased speed slightly
                .lineToLinearHeading(new Pose2d(45, -50, Math.toRadians(-270)))
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 60) // Increased speed
                .addTemporalMarker(13.5, () -> { // Adjusted timing
                    OuttakeServo.updateState();
                })
                .waitSeconds(2) // Reduced from 3 seconds
                .addTemporalMarker(15.5, () -> { // Adjusted timing
                    elbow.updateAuto();
                    elbow.runAuto();
                    verticalSlides.setState(VerticalSlides.AngleTicks.THIRD);
                    verticalSlides.setGoal();
                })

                .lineToLinearHeading(new Pose2d(4, -48, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(4, -35, Math.toRadians(-90)))
                .addTemporalMarker(17.5, () -> {
                    verticalSlides.setState(VerticalSlides.AngleTicks.ONE);
                })
                .build();
    }
}