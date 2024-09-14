//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.GamepadEx;
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Subsystems.LiftingMechanism;
//
//public class LiftTest extends OpModeEx {
//    private LiftingMechanism liftingMechanism;
//    private GamepadEx gamepad;
//
//    @Override
//    public void initInternal() {
//        gamepad = gamepad2; // Assuming you're using gamepad1, change to gamepad2 if needed
//        liftingMechanism = new LiftingMechanism(hardwareMap);
//    }
//
//    @Override
//    protected void loopInternal() {
//        double leftTriggerValue = - gamepad.LEFT_TRIGGER.value(); // Reverse value for lifting
//        // Adjust the target position based on the left trigger value
//        double targetPosition = leftTriggerValue;
//
//        // Control the lifting mechanism with the adjusted target position
//        liftingMechanism.extendLift(leftTriggerValue);
//
//        // You can add telemetry or other updates here if needed
//    }
//}
