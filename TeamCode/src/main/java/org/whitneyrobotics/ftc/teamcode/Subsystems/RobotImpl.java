package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Constants.Alliance;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.StandardTrackingWheelLocalizer;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.Ascend;

import java.util.ArrayList;

public class RobotImpl {



    public static Pose2d poseMemory = new Pose2d(0, 0, 0);

    private static RobotImpl instance = null;

    public Alliance alliance = Alliance.RED;


    //    public RotatorMotor rotationSlides;
//
//    public Claw claw;
//
//    //    public Ascend ascend;
//    public Wrist wrist;
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

    public HorizontalServo horizontalServo;



    public VerticalSlides verticalSlides;
    public RotatorMotor autoVerticalSlides;

    public intakeServo intakeServo;
    public ClawWrist intakeWrist;
    public ElbowWrist elbowWrist;
    public outtakeServo OuttakeServo;
    public DigitalChannel breakBeam;
    public CycleAutomationImpl cycleAutomation;
    private RobotImpl(HardwareMap hardwareMap) {
        drive = new IntoTheDeepMecanumDrive(hardwareMap);
//        rotationSlides = new RotatorMotor(hardwareMap);
//        claw = new IntakeClaw(hardwareMap)
        verticalSlides = new VerticalSlides(hardwareMap);
        localizer = new StandardTrackingWheelLocalizer(hardwareMap, new ArrayList<>(), new ArrayList<>());
        horizontalServo = new HorizontalServo(hardwareMap);
        autoVerticalSlides = new RotatorMotor(hardwareMap);
        intakeServo = new intakeServo(hardwareMap);
        intakeWrist = new ClawWrist(hardwareMap);
        elbowWrist = new ElbowWrist(hardwareMap);
        OuttakeServo = new outtakeServo(hardwareMap);
        breakBeam = hardwareMap.get(DigitalChannel.class,"breakBeam");
        cycleAutomation = new CycleAutomationImpl(hardwareMap);

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
//        cycleAutomation.update();
        horizontalServo.run();
        intakeServo.run();
        intakeWrist.run();
        elbowWrist.run();
        OuttakeServo.run();

        //ascend.update();
//        claw.run();
//
////        wrist.update();
////        wrist.run();
//        rotationSlides.update();
//        wrist.update();
//        wrist.run();
        //rotationSlides.update();
    }
    public void updateAuto() {
        drive.update();
        cycleAutomation.update();
        horizontalServo.run();
        intakeServo.run();
        intakeWrist.run();
        elbowWrist.run();
        OuttakeServo.run();
    }

}

