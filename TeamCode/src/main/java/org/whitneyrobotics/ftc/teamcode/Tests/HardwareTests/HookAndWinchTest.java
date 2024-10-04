//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import static org.whitneyrobotics.ftc.teamcode.Subsystems.ArmElevator.Target.ONE;
//import static org.whitneyrobotics.ftc.teamcode.Subsystems.ArmElevator.Target.RETRACT;
//import static org.whitneyrobotics.ftc.teamcode.Subsystems.ArmElevator.Target.THREE;
//import static org.whitneyrobotics.ftc.teamcode.Subsystems.ArmElevator.Target.TWO;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.Meet3Outtake.Gate;
//
//@TeleOp(name="Hook and Winch Test", group="Hardware Tests")
//public class HookAndWinchTest extends OpModeEx {
//    Meet3Intake meet3Intake;
//    ArmElevator elevator;
//    ElbowWristImpl elbowWrist;
//    Gate gate;
//    HookAndWinch hookAndWinch;
//    @Override
//    public void initInternal() {
//        meet3Intake = new Meet3Intake(hardwareMap);
//        hookAndWinch = new HookAndWinch(hardwareMap);
//        meet3Intake.onePosition();
//        elevator = new ArmElevator(hardwareMap);
//        elbowWrist = new ElbowWristImpl(hardwareMap);
//        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
//        dashboardTelemetry.setMsTransmissionInterval(25);
//        gamepad1.BUMPER_LEFT.onPress(elevator::slowModeOn);
//        gamepad1.BUMPER_LEFT.onRelease(elevator::slowModeOff);
//        gamepad1.CROSS.onPress(() -> elevator.setTargetPosition(RETRACT));
//        gamepad1.SQUARE.onPress(() -> elevator.setTargetPosition(ONE));
//        gamepad1.TRIANGLE.onPress(() -> elevator.setTargetPosition(TWO));
//        gamepad1.CIRCLE.onPress(() -> elevator.setTargetPosition(THREE));
//        gamepad1.BUMPER_RIGHT.onPress(elbowWrist::toggle);
//        hookAndWinch.init();
//        gamepad1.BUMPER_LEFT.onPress(() -> {
//            hookAndWinch.toggleServoPosition();
//            elbowWrist.endgameFlipBucket();
//        });
//        gamepad1.START.onPress(elevator::resetEncoders);
//    }
//
//    @Override
//    protected void loopInternal() {
//        hookAndWinch.setPower(gamepad1.RIGHT_STICK_Y.value());
//        elevator.inputPower(gamepad1.LEFT_STICK_Y.value());
//        meet3Intake.update();
//        hookAndWinch.update();
//        elevator.update();
//        elbowWrist.update();
//        telemetryPro.addData("Right stick y", gamepad1.RIGHT_STICK_Y.value());
//        telemetryPro.addData("Hook state", hookAndWinch.getServoPosition(), LineItem.Color.AQUA);
//        telemetryPro.addData("Hook distance", hookAndWinch.getDistanceTravelled());
//        telemetryPro.addData("Hook revolutions", hookAndWinch.getRevolutions());
//        telemetryPro.addData("Velocity", hookAndWinch.getLinearVelocity());
//    }
//}
