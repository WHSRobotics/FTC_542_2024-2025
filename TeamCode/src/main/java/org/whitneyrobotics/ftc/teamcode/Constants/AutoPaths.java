//package org.whitneyrobotics.ftc.teamcode.Constants;
//
//import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;
//import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.Meet3Outtake.Gate;
//
//import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.StartingTiles.*;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
//
//public class AutoPaths {
//
//
//    private  static PurpleServo purpleServo;
//    private  static ElbowWristImpl elbowWrist;
//    private  static Gate gate;
//
//    private static Meet3Intake intake;
//
//    public static double delay;
//
//    private static TrajectoryVelocityConstraint VEL;
//
//    public static void setAutoSubsystems(PurpleServo purple, ElbowWristImpl el, Gate gte, Meet3Intake in) {
//        purpleServo = purple;
//        elbowWrist = el;
//        gate = gte;
//        intake = in;
//    }
//
//    public static void setDelay(double del){
//        delay = del;
//    }
//    public static double getDelay(){
//        return delay;
//    }
//
//    private static final double distFromBackdrop = 48.65;
//
//
//
//    public static final TrajectorySequence BlueBackstageLeft(CenterstageMecanumDrive drivetrain
//                                                             ) {
//        return drivetrain.trajectorySequenceBuilder(BLUE_A4.pose)
//                .lineToLinearHeading(new Pose2d(35.2, 33.7, Math.toRadians(180)))
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//                })
//                .waitSeconds(1)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+2.9, 44.5,Math.toRadians(180)))
//                .waitSeconds(3)
//                .addTemporalMarker(3.4,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(4.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(6.8,() -> {
//                    gate.update();
//                    gate.run();
//
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(8,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop, 10.3, Math.toRadians(180)))
//                .lineToLinearHeading(new Pose2d(58, 10.3, Math.toRadians(180)))
//                .build();
//    }
//    public static final TrajectorySequence BlueBackstageCenter(CenterstageMecanumDrive drivetrain
//                                                               ) {
//        return drivetrain.trajectorySequenceBuilder(BLUE_A4.pose)
//                .lineToLinearHeading(new Pose2d(23.3, 26.8, Math.toRadians(180)))
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//                })
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+1.7, 39, Math.toRadians(180)))
//                .waitSeconds(3)
//                .addTemporalMarker(3.4,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(4.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(6.8,() -> {
//                    gate.update();
//                    gate.run();
//
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(8,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop-2, 12.3, Math.toRadians(180)))
//                .lineToLinearHeading(new Pose2d(58, 12.3, Math.toRadians(180)))
//                .build();
//    }
//    public static final TrajectorySequence BlueBackstageRight(CenterstageMecanumDrive drivetrain
//                                                              ) {
//        return drivetrain.trajectorySequenceBuilder(BLUE_A4.pose)
//                .lineToLinearHeading(new Pose2d(19, 37.8, Math.toRadians(180)))
//                .forward(7)
//                .addTemporalMarker(() -> {
//
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//
//                })
//                .waitSeconds(1)
//                .back(5)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+2.68, 31, Math.toRadians(180)))
//                .waitSeconds(3)
//                .addTemporalMarker(5.4,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(6.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(8.8,() -> {
//                    gate.update();
//                    gate.run();
//
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(10,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop, 12.3, Math.toRadians(180)))
//                .lineToLinearHeading(new Pose2d(58, 12.3, Math.toRadians(180)))
//
//                .build();
//    }
//
//    public static final TrajectorySequence RedBackstageLeft(CenterstageMecanumDrive drivetrain
//                                                            ){
//        return drivetrain.trajectorySequenceBuilder(RED_F4.pose)
//                .lineToLinearHeading(new Pose2d(19, -29, Math.toRadians(180)))
//                .forward(8.5)
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//                })
//                .waitSeconds(2)
//
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+1.5, -29.7, Math.toRadians(180)))
//
//                .waitSeconds(3)
//                .addTemporalMarker(6,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(6.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(8.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(10,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .lineToLinearHeading(new Pose2d(distFromBackdrop, -10.3, Math.toRadians(180)))
//                .lineToLinearHeading(new Pose2d(58, -8.3, Math.toRadians(180)))
//                .build();
//    }
//    public static final TrajectorySequence RedBackstageCenter(CenterstageMecanumDrive drivetrain
//                                                              ){
//        return drivetrain.trajectorySequenceBuilder(RED_F4.pose)
//                .lineToLinearHeading(new Pose2d(21.3, -18.2, Math.toRadians(180)))
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//
//
//                })
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+1.05, -35, Math.toRadians(180)))
//                .waitSeconds(2)
//                .addTemporalMarker(3.4,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(6.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//
//                .addTemporalMarker(8.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(3)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop-1, -10.3, Math.toRadians(180)))
//
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(58, -10.3, Math.toRadians(180)))
//                .build();
//    }
//    public static final TrajectorySequence RedBackstageRight(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(RED_F4.pose)
//                .lineToLinearHeading(new Pose2d(32.6, -25, Math.toRadians(180)))
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//
//                })
//                .lineToLinearHeading(new Pose2d(distFromBackdrop, -42.5,Math.toRadians(180)))
//                .waitSeconds(3)
//                .addTemporalMarker(3.4,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(5.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(6.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(8,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop-2, -10.3, Math.toRadians(180)))
//                .lineToLinearHeading(new Pose2d(58, -10.3, Math.toRadians(180)))
//                .build();
//    }
//
//    public static final TrajectorySequence BlueAudienceRight(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(BLUE_A2.pose)
//                .lineToLinearHeading(new Pose2d(-41.5, 22, Math.toRadians(90)))
//                .waitSeconds(0.3)
//                .addTemporalMarker(() -> {
//                    intake.raisedPosition();
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//                })
//                .waitSeconds(0.35)
//                .lineToLinearHeading(new Pose2d(-36, 9, Math.toRadians(90)))
//                .waitSeconds(delay)
//                .lineToLinearHeading(new Pose2d(15, 9, Math.toRadians(90)))
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(34.1, 24, Math.toRadians(180)))
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+1.5, 27.8, Math.toRadians(180)))
//                .addTemporalMarker(16,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(18.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(19.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(20,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(20)
//                .build();
//    }
//    public static final TrajectorySequence BlueAudienceCenter(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(BLUE_A2.pose)
//
//
//                .lineToLinearHeading(new Pose2d(-50, 21, Math.toRadians(0)))
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//
//                })
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(-36, 9, Math.toRadians(90)))
//                .waitSeconds(delay)
//
//                .lineToLinearHeading(new Pose2d(15, 9, Math.toRadians(90)))
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(34.1, 24, Math.toRadians(180)))
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+2.7, 30.8, Math.toRadians(180)))
//                .addTemporalMarker(14,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(16.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(17.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(18,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//
//                .waitSeconds(20)
//                .build();
//    }
//
//    public static final TrajectorySequence BlueAudienceLeft(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(BLUE_A2.pose)
//                .lineToLinearHeading(new Pose2d(-42, 24, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(-34.3, 24, Math.toRadians(0)))
//                .waitSeconds(0.3)
//                .addTemporalMarker(() -> {
//                    intake.raisedPosition();
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//                })
//                .waitSeconds(0.35)
//                .lineToLinearHeading(new Pose2d(-46.3, 24, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(-36, 9, Math.toRadians(90)))
//                .waitSeconds(delay)
//                .lineToLinearHeading(new Pose2d(15, 9, Math.toRadians(90)))
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(34.1, 24, Math.toRadians(180)))
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+2.7, 35.8, Math.toRadians(180)))
//                .addTemporalMarker(16,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(18.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(19.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(20,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(20)
//                .build();
//    }
//
//    public static final TrajectorySequence RedAudienceLeft(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(RED_F2.pose)
//                .lineToLinearHeading(new Pose2d(-55.2, -20, Math.toRadians(270)))
//                .strafeLeft(5.7)
//                .waitSeconds(0.2)
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//
//                })
//
//                .waitSeconds(2)
//                .strafeLeft(5)
//
//                .waitSeconds(0.35)
//                .lineToLinearHeading(new Pose2d(-36, -9, Math.toRadians(270)))
//                .waitSeconds(delay)
//                .lineToLinearHeading(new Pose2d(15, -6, Math.toRadians(270)))
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(34.1, -23, Math.toRadians(180)))
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+1.7, -25, Math.toRadians(180)))
//                .addTemporalMarker(20,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(22.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(23.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(24,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//
//                .waitSeconds(10)
//
//                .build();
//    }
//    public static final TrajectorySequence RedAudienceCenter(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(RED_F2.pose)
//
//
//                .lineToLinearHeading(new Pose2d(-50, -26.5, Math.toRadians(0)))
//                .addTemporalMarker(() -> {
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//
//                })
//                .waitSeconds(0.5)
//                .strafeLeft(4.5)
//                .lineToLinearHeading(new Pose2d(-36, -9, Math.toRadians(270)))
//                .waitSeconds(delay)
//
//                .lineToLinearHeading(new Pose2d(15, -6, Math.toRadians(270)))
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(34.1, -24, Math.toRadians(180)))
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+2.5, -28.2
//                        , Math.toRadians(180)))
//                .addTemporalMarker(20,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(22.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(23.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(24,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//
//                .waitSeconds(10)
//                .build();
//    }
//    public static final TrajectorySequence RedAudienceRight(CenterstageMecanumDrive drivetrain){
//        return drivetrain.trajectorySequenceBuilder(RED_F2.pose)
//                .lineToLinearHeading(new Pose2d(-42, -36.2, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(-35.1, -36.2, Math.toRadians(0)))
//                .waitSeconds(0.3)
//                .addTemporalMarker(() -> {
//                    intake.raisedPosition();
//                    //CLAW STUFF
//                    purpleServo.setState(PurpleServo.PurplePositions.CLOSED);
//                })
//                .waitSeconds(0.35)
//                .lineToLinearHeading(new Pose2d(-48.3, -38.8, Math.toRadians(0)))
//                .lineToLinearHeading(new Pose2d(-36, -9, Math.toRadians(90)))
//                .waitSeconds(delay)
//                .lineToLinearHeading(new Pose2d(15, -6, Math.toRadians(90)))
//                .waitSeconds(0.5)
//                .lineToLinearHeading(new Pose2d(34.1, -24, Math.toRadians(180)))
//                .waitSeconds(2)
//                .lineToLinearHeading(new Pose2d(distFromBackdrop+2, -32, Math.toRadians(180)))
//                .addTemporalMarker(20,() -> {
//                    intake.stackPosition();
//                    intake.update();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(22.5,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .addTemporalMarker(23.8,() -> {
//                    gate.update();
//                    gate.run();
//                })
//                .waitSeconds(1)
//                .addTemporalMarker(24,() -> {
//                    elbowWrist.toggle();
//                    elbowWrist.update();
//                })
//                .waitSeconds(10)
//                .build();
//    }
//
//
//     /*
//        public static final TrajectorySequence buildBlueBackstage(CenterstageMecanumDrive drivetrain) {
//            return drivetrain.trajectorySequenceBuilder(BLUE_A4.pose)
//                    .lineToConstantHeading(new Vector2d(TILE_WIDTH.toInches(-1.5), TILE_WIDTH.toInches(-0.5)))
//                    .splineToSplineHeading(
//                            new Pose2d(TILE_WIDTH.toInches(-0.5), TILE_WIDTH.toInches(2.5), Math.toRadians(-90)),
//                            Math.toRadians(90)
//                    ).build();
//        }
//
//        public static final TrajectorySequence buildRedBackstage(CenterstageMecanumDrive drivetrain){
//            return drivetrain.trajectorySequenceBuilder(RED_F4.pose)
//                    .lineToConstantHeading(new Vector2d(TILE_WIDTH.toInches(1.5), TILE_WIDTH.toInches(-0.5)))
//                    .splineToSplineHeading(
//                            new Pose2d(TILE_WIDTH.toInches(0.5), TILE_WIDTH.toInches(2.5), Math.toRadians(-90)),
//                            Math.toRadians(90)
//                    ).build();
//        }
//
//        public static final TrajectorySequence buildBlueAudience(CenterstageMecanumDrive drivetrain){
//            return drivetrain.trajectorySequenceBuilder(BLUE_A2.pose)
//                    .lineToConstantHeading(new Vector2d(TILE_WIDTH.toInches(-2.5), TILE_WIDTH.toInches(-1.5)))
//                    .lineToConstantHeading(new Vector2d(TILE_WIDTH.toInches(-2.5), TILE_WIDTH.toInches(0.5)))
//                    .lineToLinearHeading(new Pose2d(TILE_WIDTH.toInches(-2.5), TILE_WIDTH.toInches(2.5), Math.toRadians(-90))
//                    ).build();
//        }
//
//        public static final TrajectorySequence buildRedAudience(CenterstageMecanumDrive drivetrain){
//            return drivetrain.trajectorySequenceBuilder(RED_F2.pose)
//                    .lineToConstantHeading(new Vector2d(TILE_WIDTH.toInches(2.5), TILE_WIDTH.toInches(-1.5)))
//                    .lineToConstantHeading(new Vector2d(TILE_WIDTH.toInches(2.5), TILE_WIDTH.toInches(0.5)))
//                    .lineToLinearHeading(new Pose2d(TILE_WIDTH.toInches(2.5), TILE_WIDTH.toInches(2.5), Math.toRadians(-90))
//                    ).build();
//        }
//        */
//
//
//}
//
//
//
//
//
