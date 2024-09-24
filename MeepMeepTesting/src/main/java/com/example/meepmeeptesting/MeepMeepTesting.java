package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
public class MeepMeepTesting {
    public static double extraDistance = 3;
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity blueBotBackdrop1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(11, 58.3 , Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(32.9, 36.5, Math.toRadians(180)))
                                .addDisplacementMarker(() -> {
                                    //CLAW STUFF
                                })

                                .lineToLinearHeading(new Pose2d(45.3, 42.5,Math.toRadians(180)))
                                .addTemporalMarker(() ->{

                                })

                                .build()

                );

//        RoadRunnerBotEntity blueBotBackdrop2 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(11, 58.3, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(23.3, 30, Math.toRadians(180)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(45.3, 35.5, Math.toRadians(180)))
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .build()
//                );
//        RoadRunnerBotEntity blueBotBackdrop3 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(11, 58.3, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(12.5, 36.8, Math.toRadians(180)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(45.3, 28, Math.toRadians(180)))
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .waitSeconds(2)
//                                .lineToLinearHeading(new Pose2d(47.0, 10.3, Math.toRadians(180)))
//                                .lineToLinearHeading(new Pose2d(58, 10.3, Math.toRadians(180)))
//
//                                .build()
//                );
//
//        RoadRunnerBotEntity redBotBackdrop1 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(12.5, -58.3, Math.toRadians(270)))
//                                .lineToLinearHeading(new Pose2d(32.9, -23, Math.toRadians(180)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(45.3, -42.5,Math.toRadians(180)))
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .waitSeconds(2)
//                                .lineToLinearHeading(new Pose2d(45.3, -10.3, Math.toRadians(180)))
//                                .lineToLinearHeading(new Pose2d(58, -10.3, Math.toRadians(180)))
//                                .build()
//                );
//        RoadRunnerBotEntity redBotBackdrop2 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(12.5, -58.3, Math.toRadians(270)))
//                                .lineToLinearHeading(new Pose2d(12.5, -23.5, Math.toRadians(180)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(45.3, -27.5, Math.toRadians(180)))
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .build()
//                );
//        RoadRunnerBotEntity redBotBackdrop3 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(11, -58.3, Math.toRadians(270)))
//                                .lineToLinearHeading(new Pose2d(23.3, -17.5, Math.toRadians(180)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(45.3, -35, Math.toRadians(180)))
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .build()
//                );
//        RoadRunnerBotEntity blueBotWall1 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, 58, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(-41.5, 22, Math.toRadians(90)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(-36, 9, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(15, 9, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(34.1, 24, Math.toRadians(180)))
//                                .lineToLinearHeading(new Pose2d(48.45, 27.8, Math.toRadians(180)))
//
//                                .build()
//                );
//
//        RoadRunnerBotEntity blueBotWall2 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, 58, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(-50, 21, Math.toRadians(0)))
//
//                                .lineToLinearHeading(new Pose2d(-36, 9, Math.toRadians(90)))
//                                .waitSeconds(1)
//                                .lineToLinearHeading(new Pose2d(15, 9, Math.toRadians(90)))
//                                .lineToLinearHeading(new Pose2d(34.1, 24, Math.toRadians(180)))
//                                .lineToLinearHeading(new Pose2d(48.45, 30.8, Math.toRadians(180)))
//
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//
//                                .build()
//                );
//        RoadRunnerBotEntity blueBotWall3 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(new Pose2d(-35.5, 58, Math.toRadians(90)))
//                                        .lineToLinearHeading(new Pose2d(-42, 24, Math.toRadians(0)))
//                                        .lineToLinearHeading(new Pose2d(-34.3, 24, Math.toRadians(0)))
//
//                                        .back(6.5)
//                                        .addDisplacementMarker(() -> {
//                                            //CLAW STUFF
//                                        })
////                                        .strafeLeft(12.3)
//////                                .splineToConstantHeading(new Vector2d(0.7, 7.8),Math.toRadians(0))
//////                                .splineToConstantHeading(new Vector2d(19, 18),Math.toRadians(0))
////                                        .forward(50)
//                                        .lineToLinearHeading(new Pose2d(-46.3, 24, Math.toRadians(0)))
//                                        .lineToLinearHeading(new Pose2d(-36, 9, Math.toRadians(90)))
//                                        .lineToLinearHeading(new Pose2d(15, 9, Math.toRadians(90)))
//                                        .lineToLinearHeading(new Pose2d(34.1, 24, Math.toRadians(180)))
//                                        .lineToLinearHeading(new Pose2d(48.45, 35.8, Math.toRadians(180)))
//
//
//                                        .addTemporalMarker(() ->{
//
//                                        })
//                                        .build()
//                );
//        RoadRunnerBotEntity redBotWall1 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, -58, Math.toRadians(270)))
//                                .lineToLinearHeading(new Pose2d(-35.3, -22, Math.toRadians(180)))
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .strafeLeft(13.2)
//                                .lineToLinearHeading(new Pose2d(45.3, -34.8, Math.toRadians(180)))
//                                .lineToLinearHeading(new Pose2d(45.3, -27.5, Math.toRadians(180)))
//
//                                .addTemporalMarker(() ->{
//
//                                })
//
//
//                                .build()
//                );
//        RoadRunnerBotEntity redBotWall2 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, -58, Math.toRadians(270)))
//                                .lineToLinearHeading(new Pose2d(-46.8, -30.5, Math.toRadians(0)))
//                                .waitSeconds(0.2)
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .lineToLinearHeading(new Pose2d(-36, -12, Math.toRadians(90)))
//
//                                .strafeRight(70)
//                                .lineToLinearHeading(new Pose2d(45.3, -35, Math.toRadians(180)))
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .build()
//                );
//
//        RoadRunnerBotEntity redBotWall3 = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, -58, Math.toRadians(270)))
//                                .back(15)
//                                .lineToLinearHeading(new Pose2d(-35.3, -37, Math.toRadians(0)))
//
//                                .addDisplacementMarker(() -> {
//                                    //CLAW STUFF
//                                })
//                                .strafeLeft(16)
//                                .lineToLinearHeading(new Pose2d(-36, -12, Math.toRadians(90)))
//
//                                .strafeRight(70)
//                                .waitSeconds(0.2)
//
//
//                                .lineToLinearHeading(new Pose2d(45.3, -42.5, Math.toRadians(180)))
//
//                                .addTemporalMarker(() ->{
//
//                                })
//                                .build()
//                );
//        RoadRunnerBotEntity something = new DefaultBotBuilder(meepMeep)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(-35.5, -58, Math.toRadians(270)))
//                                .splineToSplineHeading(new Pose2d(13.2,60.8, Math.toRadians(-90)),Math.toRadians(-180))
//                                .splineToSplineHeading(new Pose2d(32,30.8, Math.toRadians(-180)), Math.toRadians(-180))
//                                .splineToSplineHeading(new Pose2d(53.2,43.2, Math.toRadians(-99)), Math.toRadians(-180))
//                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)




                //blue alliance backdrop
                .addEntity(blueBotBackdrop1) //left
//                .addEntity(blueBotBackdrop2) //center
//                .addEntity(blueBotBackdrop3) //right(MIGHT COLLIDE WITH TRUSS)
//
//
//
//                //red alliance, backdrop
//                 .addEntity(redBotBackdrop1) //right
//                .addEntity(redBotBackdrop2) //left
//                .addEntity(redBotBackdrop3) //center
////
////
////
////
////                //blue alliance, wall
//                .addEntity(blueBotWall1) //right
//                .addEntity(blueBotWall2) //center (MIGHT COLLIDE WITH TRUSS)
//                .addEntity(blueBotWall3) //left (MIGHT HIT TRUSS)
////
////
//                .addEntity(redBotWall1)//left
//                .addEntity(redBotWall2)//center
//                .addEntity(redBotWall3)//right

                //.addEntity(something)

                .start();
    }
}
