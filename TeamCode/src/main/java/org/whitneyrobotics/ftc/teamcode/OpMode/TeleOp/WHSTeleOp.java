package org.whitneyrobotics.ftc.teamcode.OpMode.TeleOp;

import static org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.RumbleEffects.endgame;
import static org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.RumbleEffects.matchEnd;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.whitneyrobotics.ftc.teamcode.Constants.Alliance;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.Functions;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RobotImpl;
import org.whitneyrobotics.ftc.teamcode.Subsystems.CycleAutomationStateMachine;

import java.util.function.UnaryOperator;

@TeleOp(name = "AInto The Deep TeleOp")
public class WHSTeleOp extends OpModeEx {

    boolean fieldCentric = false;
    private final UnaryOperator<Float> scalingFunctionDefault = x -> (float)Math.pow(x, 3);
    public boolean change = true;

        DcMotor fl;
        DcMotor fr;
        DcMotor bl;
        DcMotor br;
        IMU imu;

    RobotImpl robot;
    CycleAutomationStateMachine cycleStateMachine;

    @Override
    public void initInternal() {
        robot = RobotImpl.getInstance(hardwareMap);
        robot.drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //        dashboardTelemetry.setMsTransmissionInterval(25);
        //        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        gamepad1.SQUARE.onPress(robot::switchAlliance);

//                fl = hardwareMap.get(DcMotor.class, "fL");
//                fr = hardwareMap.get(DcMotor.class, "fR");
//                bl = hardwareMap.get(DcMotor.class, "bL");
//                br = hardwareMap.get(DcMotor.class, "bR");
//
//                imu = hardwareMap.get(IMU.class,"imu");
//
//                IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
//                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
//                        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
//                imu.initialize(parameters);
        //
        //        robot.rotationSlides.slidesSetPower(gamepad1);
        //        robot.rotationSlides.rotatorSetPower(gamepad1);

        gamepad1.START.onPress(() -> {
            Pose2d previousPosition = robot.drive.getPoseEstimate();
            robot.drive.setPoseEstimate(new Pose2d(
                    previousPosition.getX(),
                    previousPosition.getY(),
                    robot.alliance.headingAngle
            ));
        });
        robot.teleOpInit();
        //        robot.rotationSlides.resetEncoders();
        //
        //        robot.ascend.slidesInputPower(gamepad2.LEFT_STICK_Y.value());
        //        robot.ascend.rotatorInputPower(gamepad2.LEFT_STICK_X.value());
        cycleStateMachine = new CycleAutomationStateMachine(robot);
        setupNotifications();
    }

    void setupNotifications() {
        addTemporalCallback(resolve -> {
            gamepad1.getEncapsulatedGamepad().runRumbleEffect(endgame);
            gamepad2.getEncapsulatedGamepad().runRumbleEffect(endgame);
            telemetryPro.addLine("Endgame approaching soon!", LineItem.Color.ROBOTICS, LineItem.RichTextFormat.BOLD).persistent();
            resolve.accept(!gamepad1.getEncapsulatedGamepad().isRumbling() &&
                    gamepad2.getEncapsulatedGamepad().isRumbling());
        }, 85000);

        addTemporalCallback(resolve -> {
            telemetryPro.removeLineByCaption("Endgame approaching soon!");
            resolve.accept(true);
        },90000);

        addTemporalCallback(resolve -> {
            //playSound("matchend",100f);
            gamepad1.getEncapsulatedGamepad().runRumbleEffect(matchEnd);
            gamepad2.getEncapsulatedGamepad().runRumbleEffect(matchEnd);
            telemetryPro.addLine("Match ends in 5 seconds!", LineItem.Color.FUCHSIA, LineItem.RichTextFormat.BOLD).persistent();
            resolve.accept(!gamepad1.getEncapsulatedGamepad().isRumbling() &&
                    gamepad2.getEncapsulatedGamepad().isRumbling());
        }, 113000);

        addTemporalCallback(resolve -> {
            //playSound("slay",100f);
            telemetryPro.removeLineByCaption("Match ends in 5 seconds!");
            telemetryPro.addLine("Match has ended.", LineItem.Color.LIME, LineItem.RichTextFormat.ITALICS).persistent();
            resolve.accept(true);
        },120000);
    }

    @Override
    protected void loopInternal() {
//        robot.autoElbowWrist.runAuto();
        robot.elbowWrist.run();
        robot.horizontalServo.run();
        robot.intakeServo.run();
        robot.intakeWrist.run();
        robot.OuttakeServo.run();

        robot.verticalSlides.updateToggleState(gamepad2);
        robot.verticalSlides.updateJoystick(gamepad2);
        robot.verticalSlides.update(gamepad2);

        gamepad2.SQUARE.onPress(() -> robot.horizontalServo.update());
        gamepad2.TRIANGLE.onPress(() -> robot.intakeWrist.update());
        gamepad2.CIRCLE.onPress(() -> robot.elbowWrist.update());
        //        gamepad2.DPAD_LEFT.onPress(() -> {
        //            robot.intakeServo.update();
        //        });

        gamepad2.BUMPER_LEFT.onPress(e -> robot.intakeServo.update());
        gamepad1.CROSS.onPress(() -> robot.OuttakeServo.updateState());
        gamepad1.BUMPER_RIGHT.onPress(() -> fieldCentric = !fieldCentric);

        float brakePower = gamepad1.LEFT_TRIGGER.value();
        UnaryOperator<Float> scaling = scalingFunctionDefault;

        telemetryPro.update();
        //        robot.rotationSlides.useEncoders();
        gamepad2.SQUARE.onPress(e -> robot.horizontalServo.update());
        //        robot.rotationSlides.rotatorSetPower(gamepad2);
        //        robot.rotationSlides.slidesSetPower(gamepad2);
        //        telemetryPro.addData("Current Position of Rotator", robot.rotationSlides.getCurrentPosition());
        //        telemetryPro.addData("Current Position of Slides", robot.rotationSlides.getSlidesTicks());
        if (fieldCentric) telemetryPro.addLine("FIELD CENTRIC ENABLED", LineItem.Color.YELLOW, LineItem.RichTextFormat.BOLD);
        if (robot.alliance == Alliance.RED) telemetryPro.addLine("Robot Alliance:", LineItem.Color.RED, LineItem.RichTextFormat.BOLD);
        if (robot.alliance == Alliance.BLUE) telemetryPro.addLine("Robot Alliance:", LineItem.Color.BLUE, LineItem.RichTextFormat.BOLD);

        //        gamepad2.SQUARE.onPress(robot.claw::updateState);
        //
        if (gamepad1.BUMPER_LEFT.value()) scaling = x -> x / 2;
        if (!robot.drive.isBusy()) robot.drive.setWeightedDrivePower(
                Functions.rotateVectorCounterclockwise(new Pose2d(
                        scaling.apply((float) (gamepad1.LEFT_STICK_Y.value() )),
                        scaling.apply((float) (-gamepad1.LEFT_STICK_X.value())),
                        scaling.apply((float) (-gamepad1.RIGHT_STICK_X.value()))
                ).times(1 - brakePower), (fieldCentric ? -robot.drive.getPoseEstimate().getHeading() + robot.alliance.headingAngle : 0))
        );
        telemetryPro.addData("Pose", robot.drive.getPoseEstimate());
//
//        double y = -(gamepad1.LEFT_STICK_Y.value()); // Remember, Y stick value is reversed
//        double x = gamepad1.LEFT_STICK_X.value();
//        double rx = gamepad1.RIGHT_STICK_X.value();
//        gamepad1.START.onPress(e -> imu.resetYaw());
//
//        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
//
//        rotX = rotX * 1.1;  // Counteract imperfect strafing
//
//        // Denominator is the largest motor power (absolute value) or 1
//        // This ensures all the powers maintain the same ratio,
//        // but only if at least one is out of the range [-1, 1]
//        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
//        double frontLeftPower = (rotY + rotX + rx) / denominator;
//        double backLeftPower = (rotY - rotX + rx) / denominator;
//        double frontRightPower = (rotY - rotX - rx) / denominator;
//        double backRightPower = (rotY + rotX - rx) / denominator;
//
//        fl.setPower(frontLeftPower);
//        bl.setPower(backLeftPower);
//        fr.setPower(frontRightPower);
//        br.setPower(backRightPower);
        telemetryPro.addData("State", robot.breakBeam.getState());
        robot.intakeServo.beamBreakUpdate(!(robot.breakBeam.getState()), gamepad1);
        robot.intakeServo.setOverride(gamepad1);
        if (robot.intakeServo.getOverride()) telemetryPro.addLine("BREAK BEAM ENABLED", LineItem.Color.RED, LineItem.RichTextFormat.BOLD);
        telemetryPro.addData("OUTTAKE CLAW POSITION", robot.OuttakeServo.currentState);
        telemetryPro.addData("INTAKE CLAW POSITION", robot.intakeServo.currentState);
        telemetryPro.addData("Stateforge current state:", cycleStateMachine.getState());
        telemetryPro.addData("Target Position", robot.verticalSlides.getTargetPosition());
        telemetryPro.addData("Cycle State", cycleStateMachine.getState());
        telemetryPro.addData("clawwrist dump state", robot.intakeWrist.position);
        telemetryPro.update();

        gamepad2.BUMPER_RIGHT.onPress(() -> {
            cycleStateMachine.toggle();
        });
        cycleStateMachine.update();
    }
}