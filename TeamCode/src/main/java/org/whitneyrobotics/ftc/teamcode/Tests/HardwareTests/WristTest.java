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
////import org.whitneyrobotics.ftc.teamcode.Subsystems.PixelJoint;
//import org.whitneyrobotics.ftc.teamcode.Tests.Test;
//
//@TeleOp(name="Wrist Test")
//public class WristTest extends OpModeEx {
//    PixelJoint wrist;
//    @Override
//    public void initInternal() {
//        wrist = new PixelJoint(hardwareMap);
//        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
//        dashboardTelemetry.setMsTransmissionInterval(25);
//        wrist.resetEncoders();
//        gamepad1.SQUARE.onPress(() -> wrist.setTarget(PixelJoint.ArmPositions.INTAKE));
//        gamepad1.CIRCLE.onPress(() -> wrist.setTarget(PixelJoint.ArmPositions.OUTTAKE));
//        gamepad1.START.onPress(wrist::resetEncoders);
//    }
//
//    @Override
//    protected void loopInternal() {
//        wrist.update();
//        telemetryPro.addData("Elevator State", wrist.getState());
//        telemetryPro.addData("Elevator Position", wrist.getPosition());
//        telemetryPro.addData("Elevator Velocity", wrist.getVelocity());
//        telemetryPro.addData("Desired Position", wrist.getDesiredPosition());
//        telemetryPro.addData("Desired Velocity", wrist.getDesiredVelocity());
//        telemetryPro.addData("Elevator Acceleration", wrist.getDesiredAcceleration());
//        telemetryPro.addData("Target Position", wrist.getTargetPosition());
//        telemetryPro.addData("Motion Profile Duration", wrist.getMotionProfileDuration());
//        telemetryPro.addData("Target", wrist.target==null ? "null" : wrist.target);
//    }
//}
