package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity bot4 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(18, -60, Math.toRadians(90)))
                        .lineTo(new Vector2d(0,-35))
                        .waitSeconds(3)
//                        extend 26 inches vertical, move forward, unextend
                        .lineToLinearHeading(new Pose2d(30,-35, Math.toRadians(-90)))
                        .lineTo(new Vector2d(30,-10))
                        .lineTo(new Vector2d(42,-10))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(42,-50))
                        .lineTo(new Vector2d(42, -10))
                        .lineTo(new Vector2d(54,-10))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(56,-55))
                        .turn(Math.toRadians(45))
                        .waitSeconds(3)
//                       extend slides (not sure abt distance), close claw when have specimen
                        .lineToLinearHeading(new Pose2d(0,-35, Math.toRadians(90)))
                        .waitSeconds(3)
//                            extend 26 inches vertical (idk how much horizontal), open claw, unextend
                        .lineTo(new Vector2d(58,-58))
                        .build());


        RoadRunnerBotEntity bot2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-18, 60, Math.toRadians(-90)))
                        .lineTo(new Vector2d(0,35))
                        .waitSeconds(5)
                        .addTemporalMarker(1.5, () ->{
//                            extend 26 inches vertical (idk how much horizontal), open claw, unextend
                        })
                        .lineToLinearHeading(new Pose2d(-30,35, Math.toRadians(90)))
                        .lineTo(new Vector2d(-30,10))
                        .lineTo(new Vector2d(-42,10))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(-42,52))
                        .lineTo(new Vector2d(-42, 10))
                        .lineTo(new Vector2d(-54,10))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(-56,57))
                        .turn(Math.toRadians(45))
                        .waitSeconds(3)
                        .addTemporalMarker(17.6, () -> {
//                            extend slides (not sure abt distance), close claw when have specimen
                        })
                        .lineToLinearHeading(new Pose2d(0,35, Math.toRadians(-90)))
                        .waitSeconds(5)
                        .addTemporalMarker(24, () ->{
//                            extend 26 inches vertical (idk how much horizontal), open claw, unextend
                        })
                        .lineTo(new Vector2d(-58,58))
                        .build());
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(18, 60, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(48,50, Math.toRadians(45)))
                        .waitSeconds(5)
                        .addTemporalMarker(1.5, () ->{
                            //robot.highSlides();
                            //robot.openClaw();
                            //robot.midSlides();
                        })
                        //pick up second one
                        .lineToLinearHeading(new Pose2d(48,40, Math.toRadians(-90)))
                        .waitSeconds(3)
                        .addTemporalMarker(8, ()->{
                            //robot.lowSlides();
                            //robot.closeClaw();
                            //robot.midSlides();
                        })
                        //drop off second  one
                        .lineToLinearHeading(new Pose2d(48,50, Math.toRadians(45)))
                        .waitSeconds(5)
                        .addTemporalMarker(12, () ->{
                            //robot.liftSlides();
                            //robot.openClaw();
                            //robot.lowerSlides();
                        })
                        //pick up third one
                        .lineToLinearHeading(new Pose2d(58,40, Math.toRadians(-90)))
                        .waitSeconds(3)
                        .addTemporalMarker(18, ()->{
                            //robot.lowSlides();
                            //robot.closeClaw();
                            //robot.midSlides();
                        })
                        //drop off third one
                        .lineToLinearHeading(new Pose2d(48,50, Math.toRadians(45)))
                        .waitSeconds(5)
                        .addTemporalMarker(22, () -> {
                            //robot.liftSlides();
                            //robot.openClaw();
                            //robot.lowerSlides();
                        })
                        //park
                        .lineToLinearHeading(new Pose2d(56,56, Math.toRadians(90)))
                        .build());

        RoadRunnerBotEntity bot6 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(18, -60, Math.toRadians(90)))
                        .lineTo(new Vector2d(0,-35))
                        .waitSeconds(3)
//                      extend 26 inches vertical (idk how much horizontal), open claw, unextend
                        .lineToLinearHeading(new Pose2d(30,-40, Math.toRadians(180)))
                        .lineToLinearHeading(new Pose2d(36,-10, Math.toRadians(270)))
                        .lineTo(new Vector2d(42,-10))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(42,-50))
                        .lineTo(new Vector2d(42, -10))
                        .lineTo(new Vector2d(52,-10))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(55,-55))
                        .lineTo(new Vector2d(55,-50))
                        .waitSeconds(1)
                        .lineTo(new Vector2d(55,-55))
//                      extend slides (not sure abt distance), close claw when have specimen
                        .lineToLinearHeading(new Pose2d(0,-35, Math.toRadians(90)))
                        .waitSeconds(3)
//                      extend 26 inches vertical (idk how much horizontal), open claw, unextend
                        .lineTo(new Vector2d(58,-58))
                        .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
//                .addEntity(bot4)
//                .addEntity(bot2)
//                .addEntity(myBot)
                .addEntity(bot6)

                .start();
    }
}