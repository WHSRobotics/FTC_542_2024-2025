package org.whitneyrobotics.ftc.teamcode.OpMode.TeleOp;


import static org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.RumbleEffects.endgame;
import static org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.RumbleEffects.matchEnd;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.Functions;
import org.whitneyrobotics.ftc.teamcode.Subsystems.RobotImpl;

import java.util.function.UnaryOperator;

@TeleOp(name = "Into The Deep TeleOp", group = "A")
public class WHSTeleOp extends OpModeEx {

    boolean fieldCentric = true;

    private final UnaryOperator<Float> scalingFunctionDefault = x -> (float)Math.pow(x, 3);
    public boolean change = true;

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
        UnaryOperator<Float> scaling = scalingFunctionDefault;
        float brakePower = gamepad1.LEFT_TRIGGER.value();

        robot.rotationSlides.rotatorSetPower(gamepad2);
        robot.rotationSlides.slidesSetPower(gamepad2);
<<<<<<< HEAD
        gamepad2.SQUARE.onPress(()->{
            robot.wrist.Open();
        });
        gamepad2.CROSS.onPress(()->{
            robot.claw.update();
        });
        gamepad2.TRIANGLE.onPress(()->{
            robot.wrist.Half();
        });
        gamepad2.CIRCLE.onPress(()->{
            robot.wrist.Close();
        });
=======
>>>>>>> c2f414f ([6:18] Meet 1 Final pending wrist enums)
//        gamepad2.SQUARE.onPress(robot.claw::updateState);

        if (!robot.drive.isBusy()) robot.drive.setWeightedDrivePower(
                Functions.rotateVectorCounterclockwise(new Pose2d(
                        scaling.apply(gamepad1.LEFT_STICK_Y.value()),
                        scaling.apply(-gamepad1.LEFT_STICK_X.value()),
                        scaling.apply(-gamepad1.RIGHT_STICK_X.value())
                ).times(1-brakePower), (fieldCentric ? -robot.drive.getPoseEstimate().getHeading()+robot.alliance.headingAngle : 0))
        );

    }
}
