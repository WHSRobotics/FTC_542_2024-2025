//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Tests.Test;
//
//@TeleOp(name="Meet 3 Intake Test", group="Hardware Tests")
//@Test(name="Meet 3 Intake Test", description="Intake Test for Meet 3 Drop Down Intake")
//public class Meet3IntakeTest extends OpModeEx {
//    Meet3Intake intake;
//    @Override
//    public void initInternal() {
//        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
//        intake = new Meet3Intake(hardwareMap);
//        gamepad1.SQUARE.onPress(intake::raisedPosition);
//        gamepad1.TRIANGLE.onPress(intake::stackPosition);
//        gamepad1.CIRCLE.onPress(intake::onePosition);
//        intake.update();
//    }
//
//    @Override
//    protected void loopInternal() {
//        intake.setReversed(gamepad1.CROSS.value());
//        intake.setRPM(Math.sqrt(gamepad1.RIGHT_TRIGGER.value())*Meet3Intake.MAX_RPM);
//        intake.update();
//        telemetryPro.addData("Target RPM", intake.getTargetRPM());
//        telemetryPro.addData("Actual RPM", intake.getActualRPM());
//        telemetryPro.addData("Servo Pos", intake.servoPos());
//    }
//}
