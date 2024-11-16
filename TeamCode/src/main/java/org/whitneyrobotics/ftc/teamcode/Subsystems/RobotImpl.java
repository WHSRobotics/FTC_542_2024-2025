package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;

import org.whitneyrobotics.ftc.teamcode.Constants.Alliance;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.StandardTrackingWheelLocalizer;
import org.whitneyrobotics.ftc.teamcode.Subsystems.Claw;
import org.whitneyrobotics.ftc.teamcode.Subsystems.Wrist;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.Ascend;

import java.util.ArrayList;

public class RobotImpl {

    public static Pose2d poseMemory = new Pose2d(0, 0, 0);

    private static RobotImpl instance = null;

    public Alliance alliance = Alliance.RED;

    public RotatorMotor rotationSlides;

    public Claw claw;

//    public Ascend ascend;
    public Wrist wrist;
    public static RobotImpl getInstance(){
        return instance;
    }

    public static RobotImpl getInstance(HardwareMap hardwareMap){
        init(hardwareMap);
        return instance;

    }

    public static void init(HardwareMap hardwareMap) {
        instance = new RobotImpl(hardwareMap);
    }

    public IntoTheDeepMecanumDrive drive;

    public StandardTrackingWheelLocalizer localizer;

    private RobotImpl(HardwareMap hardwareMap) {
        drive = new IntoTheDeepMecanumDrive(hardwareMap);
//        rotationSlides = new RotatorMotor(hardwareMap);
//        claw = new IntakeClaw(hardwareMap)
        localizer = new StandardTrackingWheelLocalizer(hardwareMap, new ArrayList<>(), new ArrayList<>());
//        ascend = new Ascend(hardwareMap);
        claw = new Claw(hardwareMap);
        wrist = new Wrist(hardwareMap);
        rotationSlides = new RotatorMotor(hardwareMap);

    }

    public void switchAlliance(){
        alliance = alliance == Alliance.RED ? Alliance.BLUE : Alliance.RED;
    }

    public void teleOpInit(){
        Pose2d poseMemory = localizer.getPoseEstimate();

        drive.setPoseEstimate(poseMemory);


    }

    public void update(){
        drive.update();
//        ascend.update();
        claw.run();
//        wrist.update();
//        wrist.run();
//        rotationSlides.update();
    }

}
