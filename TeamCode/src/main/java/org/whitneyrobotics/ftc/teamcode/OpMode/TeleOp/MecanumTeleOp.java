package org.whitneyrobotics.ftc.teamcode.OpMode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.whitneyrobotics.ftc.teamcode.Constants.Alliance;
import org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.MultipleChoicePoll;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.UnitConversion.DistanceUnit;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;

import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.BACKDROP_EDGE_FROM_WALL;
import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.FieldSide.AUDIENCE;
import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.FieldSide.BACKSTAGE;
import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.PIXEL_WIDTH;
import static org.whitneyrobotics.ftc.teamcode.Constants.RobotDetails.ROBOT_LENGTH;
import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.FieldSide;

import java.util.function.UnaryOperator;

@TeleOp(name = "Mecanum TeleOp Test" , group = "teleop")
public class MecanumTeleOp extends OpModeEx {
    private CenterstageMecanumDrive drivetrain;
    private Alliance alliance = Alliance.RED;

    private MultipleChoicePoll tileSelector;
    private UnaryOperator<Float> scalingFunctionDefault = x -> (float)Math.pow(x, 3);
    private String navPath = "";
    private FieldConstants.StartingTiles startingTile = FieldConstants.StartingTiles.RED_F2;

    void switchAlliance(){
        this.alliance = alliance == Alliance.RED ? Alliance.BLUE : Alliance.RED;
    }

    @Override
    public void initInternal() {
        drivetrain = new CenterstageMecanumDrive(hardwareMap);
        drivetrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        gamepad1.SELECT.onPress(this::switchAlliance);
        gamepad1.START.onPress(() -> {
            Pose2d previousPosition = drivetrain.getPoseEstimate();
            drivetrain.getLocalizer().setPoseEstimate(new Pose2d(
                    previousPosition.getX(),
                    previousPosition.getY(),
                    0 + (alliance == Alliance.RED ? Math.PI : 0)
            ));
        });

        tileSelector = new MultipleChoicePoll("Select Tile", false,
                new MultipleChoicePoll.MultipleChoiceOption<FieldSide>("Backstage", BACKSTAGE),
                new MultipleChoicePoll.MultipleChoiceOption<FieldSide>("Audience", AUDIENCE));


        gamepad1.TRIANGLE.onPress(() -> {
            navPath = "FORWARD 1 TILE";
            drivetrain.followTrajectoryAsync(
                    drivetrain.trajectoryBuilder(drivetrain.getPoseEstimate())
                            .forward(24)
                            .build()
            );
            drivetrain.update();
        });

        gamepad1.SQUARE.onPress(() -> {
            navPath = "SPLINE TO ALLIANCE BACKDROP";
            drivetrain.followTrajectoryAsync(
                    drivetrain.trajectoryBuilder(drivetrain.getPoseEstimate())
                            .splineToSplineHeading(new Pose2d(
                                    DistanceUnit.TILE_WIDTH.toInches(1.5)*alliance.allianceCoefficient,
                                    DistanceUnit.TILE_WIDTH.toInches(3)-BACKDROP_EDGE_FROM_WALL-ROBOT_LENGTH/2,
                                    Math.toRadians(-90)), Math.toRadians(90))
                            .build()
            );
            drivetrain.update();
        });

        gamepad1.DPAD_LEFT.onPress(() -> {
            navPath = "STRAFE LEFT 1 HALF PIXEL";
            drivetrain.followTrajectoryAsync(
                    drivetrain.trajectoryBuilder(drivetrain.getPoseEstimate())
                            .strafeRight(PIXEL_WIDTH/2)
                            .build()
            );
            drivetrain.update();
        });

        gamepad1.DPAD_LEFT.onPress(() -> {
            navPath = "STRAFE RIGHT 1 HALF PIXEL";
            drivetrain.followTrajectoryAsync(
                    drivetrain.trajectoryBuilder(drivetrain.getPoseEstimate())
                            .strafeLeft(PIXEL_WIDTH/2)
                            .build()
            );
            drivetrain.update();
        });

        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        telemetryPro.setInteractingGamepad(gamepad1, g->g.DPAD_DOWN, g->g.DPAD_UP);
        gamepad1.CIRCLE.onPress(drivetrain::cancelTrajectory);
        gamepad1.LEFT_STICK_X.onInteraction(drivetrain::cancelTrajectory);
        gamepad1.LEFT_STICK_Y.onInteraction(drivetrain::cancelTrajectory);
        gamepad1.RIGHT_STICK_X.onInteraction(drivetrain::cancelTrajectory);
        telemetryPro.addItem(tileSelector);

        //gamepad2.SQUARE.onPress(e -> robot.slides.setTarget(slides.Target.FIRSTMARK));
        //gamepad2.TRIANGLE.onPress(e -> robot.slides.setTarget(slides.Target.SECONDMARK));
        //gamepad2.CIRCLE.onPress(e -> robot.slides.setTarget(slides.Target.THIRDMARK));
        //gamepad2.CROSS.onPress(e -> robot.slides.setTarget(slides.Target.LOWERED));
    }

    @Override
    public void initInternalLoop(){
        switch((int)tileSelector.getSelected()[0].getValue()){
            case 0:
                startingTile = alliance == Alliance.RED ? FieldConstants.StartingTiles.RED_F2 : FieldConstants.StartingTiles.BLUE_A2;
                break;
            case 1:
                startingTile = alliance == Alliance.RED ? FieldConstants.StartingTiles.RED_F4 : FieldConstants.StartingTiles.BLUE_A4;
                break;
        }
        telemetryPro.addLine("Starting at " + startingTile.name(), LineItem.Color.LIME);
        telemetryPro.addData("Alliance", (alliance == Alliance.RED ? "RED" : "BLUE"), (alliance == Alliance.RED ? LineItem.Color.RED : LineItem.Color.BLUE));
    }

    @Override
    public void startInternal() {
        drivetrain.getLocalizer().setPoseEstimate(startingTile.pose);
        telemetryPro.removeLineByReference(tileSelector);
    }

    @Override
    protected void loopInternal() {
        float brakePower = gamepad1.LEFT_TRIGGER.value();
        UnaryOperator<Float> scaling = scalingFunctionDefault;
        if(gamepad1.BUMPER_LEFT.value()) scaling = x -> x/2;
        drivetrain.update();
        if (!drivetrain.isBusy()) {
            drivetrain.setWeightedDrivePower(
                    //We use Y for our X movement as our drivers are aligned to a side of the field
                    new Pose2d(
                            scaling.apply(gamepad1.LEFT_STICK_Y.value()),
                            scaling.apply(gamepad1.LEFT_STICK_X.value()), //When on the left side of the field, our strafe controls are inverted
                            scaling.apply(-gamepad1.RIGHT_STICK_X.value())
                    ).times(1-brakePower)
            );
        }

        if(drivetrain.isBusy()){
            telemetryPro.addLine("Autonomous Navigation Enabled", LineItem.Color.FUCHSIA, LineItem.RichTextFormat.BOLD);
            telemetryPro.addData("Path", navPath);
        }
        else if(gamepad1.BUMPER_LEFT.value()) telemetryPro.addLine("SLOW MODE ENABLED", LineItem.Color.LIME);
        telemetryPro.addData("Alliance", (alliance == Alliance.RED ? "RED" : "BLUE"), (alliance == Alliance.RED ? LineItem.Color.RED : LineItem.Color.BLUE));
        telemetryPro.addData("x", drivetrain.getPoseEstimate().getX());
        telemetryPro.addData("y", drivetrain.getPoseEstimate().getY());
        telemetryPro.addData("thetaDeg", Math.toDegrees(drivetrain.getPoseEstimate().getHeading()));
        telemetryPro.addData("brake", brakePower);
    }
}
