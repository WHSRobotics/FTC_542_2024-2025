package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.whitneyrobotics.ftc.teamcode.Subsystems.ElbowWrist;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RotatorMotor;
import org.whitneyrobotics.ftc.teamcode.Subsystems.outtakeServo;

public class AutoPaths {

    public static ElbowWrist elbow;
    public static outtakeServo OuttakeServo;

    public static RotatorMotor verticalSlides;
    public static void setAutoSubsystems(ElbowWrist el, outtakeServo out, RotatorMotor slides) {
        elbow = el;
        OuttakeServo = out;
        verticalSlides = slides;
    }
    public static final TrajectorySequence BlueBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(-15, 60, Math.toRadians(90)))
                .waitSeconds(2)
                .addTemporalMarker(0.5,()->{
                    elbow.update();
                    verticalSlides.setState(RotatorMotor.AngleTicks.ONE);

                })

                .lineToLinearHeading(new Pose2d(-4, 37, Math.toRadians(90)))
                .addTemporalMarker(5,()->{
                    verticalSlides.setState(RotatorMotor.AngleTicks.ZERO);
                })

                .addTemporalMarker(7,()->{
                    elbow.update();
                })
                .waitSeconds(4.5)

                .lineToLinearHeading(new Pose2d(-33, 50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-33, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-45, 0, Math.toRadians(-85)))
                .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(-85)))
                .lineToLinearHeading(new Pose2d(-45, 35, Math.toRadians(-85)))
                .addTemporalMarker(15,()->{
                    elbow.update();
                    OuttakeServo.updateState();
                })
                .waitSeconds(5)
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 10)

                .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(-85)))
                .waitSeconds(2)

                .addTemporalMarker(21,()-> {
                    OuttakeServo.updateState();
                })
                .addTemporalMarker(22,()->{

                    verticalSlides.setState(RotatorMotor.AngleTicks.ONE);
                })
                .lineToLinearHeading(new Pose2d(0, 43, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(5, 40, Math.toRadians(90)))
                .addTemporalMarker(27,()->{
                    verticalSlides.setState(RotatorMotor.AngleTicks.ZERO);
                })
                .waitSeconds(3)



                .build();
    }
    public static final TrajectorySequence RedBackstageLeft(IntoTheDeepMecanumDrive drivetrain){
        return drivetrain.trajectorySequenceBuilder(new Pose2d(15, -60, Math.toRadians(-90)))
                .waitSeconds(2)
                .addTemporalMarker(0.5,()->{
                    elbow.update();
                    verticalSlides.setState(RotatorMotor.AngleTicks.ONE);

                })

                .lineToLinearHeading(new Pose2d(4, -38, Math.toRadians(-90)))
                .addTemporalMarker(5,()->{
                    verticalSlides.setState(RotatorMotor.AngleTicks.ZERO);
                })

                .addTemporalMarker(7,()->{
                    elbow.update();
                })
                .waitSeconds(4.5)

                .lineToLinearHeading(new Pose2d(37, -50, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(37, 0, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(45, 0, Math.toRadians(85)))
                .lineToLinearHeading(new Pose2d(45, -50, Math.toRadians(85)))
                .lineToLinearHeading(new Pose2d(45, -35, Math.toRadians(85)))
                .addTemporalMarker(15,()->{
                    elbow.update();
                    OuttakeServo.updateState();
                })
                .waitSeconds(5)
                .setVelConstraint((v, pose2d, pose2d1, pose2d2) -> 20)
                .lineToLinearHeading(new Pose2d(45, -56, Math.toRadians(90)))
                .waitSeconds(3.5)

                .addTemporalMarker(21,()->{
                    OuttakeServo.updateState();

                    verticalSlides.setState(RotatorMotor.AngleTicks.ONE);
                })
                .lineToLinearHeading(new Pose2d(0, -43, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(5, -35, Math.toRadians(-90)))

                .waitSeconds(1)
                .addTemporalMarker(28,()->{
                    verticalSlides.setState(RotatorMotor.AngleTicks.ZERO);
                })


                .build();
    }
}
