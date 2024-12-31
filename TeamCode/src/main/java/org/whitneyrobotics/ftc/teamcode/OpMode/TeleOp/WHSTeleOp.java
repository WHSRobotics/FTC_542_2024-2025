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

import java.util.function.UnaryOperator;

@TeleOp(name = "Into The Deep TeleOp", group = "A")
public class WHSTeleOp extends OpModeEx {

    boolean fieldCentric = false;

    private final UnaryOperator<Float> scalingFunctionDefault = x -> (float)Math.pow(x, 3);
    public boolean change = true;

    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
//    IMU imu;

    RobotImpl robot;
    @Override
    public void initInternal() {
        robot = RobotImpl.getInstance(hardwareMap);
        robot.drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dashboardTelemetry.setMsTransmissionInterval(25);
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        gamepad1.SQUARE.onPress(robot::switchAlliance);
//
//        fl = hardwareMap.get(DcMotor.class, "fL");
//        fr = hardwareMap.get(DcMotor.class, "fR");
//        bl = hardwareMap.get(DcMotor.class, "bL");
//        br = hardwareMap.get(DcMotor.class, "bR");
//
//        imu = hardwareMap.get(IMU.class,"imu");
//
//        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
//                RevHubOrientationOnRobot.LogoFacingDirection.UP,
//                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
//
//        imu.initialize(parameters);
//
//        robot.rotationSlides.slidesSetPower(gamepad1);
//        robot.rotationSlides.rotatorSetPower(gamepad1);

        gamepad1.START.onPress(() -> {
            Pose2d previousPosition = robot.drive.getPoseEstimate();
            robot.drive.setPoseEstimate(new Pose2d(
                    previousPosition.getX(),
                    previousPosition.getY(),
                    0 + (robot.alliance == Alliance.RED ? Math.PI/2 : -Math.PI/2)
            ));
        });
        robot.teleOpInit();
//        robot.rotationSlides.resetEncoders();

        gamepad1.BUMPER_RIGHT.onPress(() -> fieldCentric = !fieldCentric);
//        robot.ascend.slidesInputPower(gamepad2.LEFT_STICK_Y.value());
//        robot.ascend.rotatorInputPower(gamepad2.LEFT_STICK_X.value());


    }

    void setupNotifications() {
        addTemporalCallback(resolve -> {
            gamepad1.getEncapsulatedGamepad().runRumbleEffect(endgame);
            gamepad2.getEncapsulatedGamepad().runRumbleEffect(endgame);
            telemetryPro.addLine("Endgame approaching soon!", LineItem.Color.ROBOTICS, LineItem.RichTextFormat.BOLD).persistent();
            resolve.accept(!gamepad1.getEncapsulatedGamepad().isRumbling() && gamepad2.getEncapsulatedGamepad().isRumbling());
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
            resolve.accept(!gamepad1.getEncapsulatedGamepad().isRumbling() && gamepad2.getEncapsulatedGamepad().isRumbling());
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
        robot.update();
        robot.verticalSlides.setPower(gamepad2);

        gamepad2.SQUARE.onPress(()->{
            robot.horizontalServo.update();
        });
        gamepad2.TRIANGLE.onPress(()->{
            robot.intakeWrist.update();
        });
        gamepad2.CIRCLE.onPress(()->{
            robot.elbowWrist.update();
        });
        gamepad2.DPAD_LEFT.onPress(()->{
            robot.intakeServo.updateState();
        });
        gamepad2.DPAD_RIGHT.onPress(()->{
            robot.OuttakeServo.updateState();
        });
        float brakePower = gamepad1.LEFT_TRIGGER.value();
        UnaryOperator<Float> scaling = scalingFunctionDefault;


        telemetryPro.update();
//
//        robot.rotationSlides.useEncoders();
        gamepad2.SQUARE.onPress(e -> {
            robot.horizontalServo.update();
        });
//
//        robot.rotationSlides.rotatorSetPower(gamepad2);
//        robot.rotationSlides.slidesSetPower(gamepad2);
//        telemetryPro.addData("Current Position of Rotator", robot.rotationSlides.getCurrentPosition());
//        telemetryPro.addData("Current Position of Slides", robot.rotationSlides.getSlidesTicks());
        if(fieldCentric) telemetryPro.addLine("FIELD CENTRIC ENABLED", LineItem.Color.YELLOW, LineItem.RichTextFormat.BOLD);
        telemetryPro.addData("Add Data:", robot.alliance);
//        gamepad2.SQUARE.onPress(robot.claw::updateState);
//
        if(gamepad1.BUMPER_LEFT.value()) scaling = x -> x/2;
        if (!robot.drive.isBusy()) robot.drive.setWeightedDrivePower(
                Functions.rotateVectorCounterclockwise(new Pose2d(
                        scaling.apply(gamepad1.LEFT_STICK_Y.value()),
                        scaling.apply(-gamepad1.LEFT_STICK_X.value()),
                        scaling.apply(-gamepad1.RIGHT_STICK_X.value())
                ).times(1-brakePower), (fieldCentric ? -robot.drive.getPoseEstimate().getHeading()+robot.alliance.headingAngle : 0))
        );
        telemetryPro.addData("brake", brakePower);
        telemetryPro.addData("angle", Math.toDegrees(robot.drive.getPoseEstimate().getHeading()));

//        double robotHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//
//        double rotX = x * Math.cos(-robotHeading) - y * Math.sin(-robotHeading);
//        double rotY = x * Math.sin(-robotHeading) + y + Math.cos(-robotHeading);
//
//        rotX = rotX * 1.1;
//
//        double omega = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
//        double flp = (rotY + rotX + rx) / omega;
//        double blp = (rotY - rotX + rx) / omega;
//        double frp = (rotY - rotX - rx) / omega;
//        double brp = (rotY + rotX - rx) / omega;
//
//        fl.setPower(flp);
//        bl.setPower(blp);
//        fr.setPower(frp);

    }
}
