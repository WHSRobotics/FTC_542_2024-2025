package org.whitneyrobotics.ftc.teamcode.OpMode.Autonomous;

import static org.whitneyrobotics.ftc.teamcode.Constants.Alliance.BLUE;
import static org.whitneyrobotics.ftc.teamcode.Constants.Alliance.RED;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.whitneyrobotics.ftc.teamcode.Constants.Alliance;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.AutoSetupTesting.Tests;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.MultipleChoicePoll;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.IntoTheDeepMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;
import org.whitneyrobotics.ftc.teamcode.Subsystems.ElbowWrist;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RobotImpl;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RotatorMotor;
import org.whitneyrobotics.ftc.teamcode.Subsystems.VerticalSlides;

@Autonomous(name = "AutoWHSBlue")
public class WHSAutoBlueN extends OpModeEx {
    IntoTheDeepMecanumDrive drive;
    RobotImpl robot;
    MultipleChoicePoll tileSelector;
    String selectedTrajectory;

    AutoPaths paths;

    private int numeric_path;

    @Override
    public void initInternal() {
        RobotImpl.init(hardwareMap);
        robot = RobotImpl.getInstance();
        telemetryPro.useTestManager()
                .addTest("Gamepad 1 Initialization", () -> Tests.assertGamepadSetup(gamepad1, "Gamepad 1"))
                .addTest("Gamepad 2 Initialization", () -> Tests.assertGamepadSetup(gamepad2, "Gamepad 2"))
                .addTest("Battery voltage test", () -> Tests.assertBatteryCharged(hardwareMap.get(LynxModule.class, "Control Hub")));

        drive = new IntoTheDeepMecanumDrive(hardwareMap);
        if (robot.alliance == RED){
            robot.alliance = BLUE;
        }else{
            robot.alliance = BLUE;
        }
//        gamepad1.CIRCLE.onPress(() -> {
//            robot.switchAlliance();
//            telemetryPro.addData("Alliance manually changed to", robot.alliance.name(), robot.alliance == RED ? LineItem.Color.RED : LineItem.Color.BLUE, LineItem.RichTextFormat.ITALICS).persistent();
//
//        });
        robot.drive.sendPacket(packet);

    }

    @Override
    public void initInternalLoop(){
        robot.verticalSlides.resetEncoders();
        robot.verticalSlides.setState(VerticalSlides.AngleTicks.ZERO);
        telemetryPro.addData("Alliance", robot.alliance.name(), (robot.alliance == Alliance.RED ? LineItem.Color.RED : LineItem.Color.BLUE));

    }

    @Override
    public void startInternal(){
        TrajectorySequence desiredTrajectory = null;
        switch (robot.alliance){
            case BLUE:
                desiredTrajectory = AutoPaths.BlueBackstageLeft(robot.drive);
                robot.drive.getLocalizer().setPoseEstimate(new Pose2d(-15, 60, Math.toRadians(90)));
                selectedTrajectory = "BLUE AUDIENCE";

        }
        robot.drive.followTrajectorySequenceAsync(desiredTrajectory);

    }
    @Override
    protected void loopInternal() {

        AutoPaths.setAutoSubsystems(robot.elbowWrist,robot.OuttakeServo,robot.verticalSlides);
        telemetryPro.addData("Trajectory",selectedTrajectory);
        telemetryPro.addData("TIME RIGHT NOW: ", System.currentTimeMillis()/1000);
        telemetryPro.addData("Positon of Elbow AUTO: ", robot.elbowWrist.positionAuto);
        telemetryPro.addData("Positon of Elbow TELE: ", robot.elbowWrist.position);


        robot.drive.update();
        robot.horizontalServo.run();

        robot.intakeServo.run();
        robot.intakeWrist.run();
        robot.OuttakeServo.run();
        robot.verticalSlides.autoUpdate();

//        robot.updateAuto();
        telemetryPro.update();
        RobotImpl.poseMemory = robot.drive.getPoseEstimate();



    }
}