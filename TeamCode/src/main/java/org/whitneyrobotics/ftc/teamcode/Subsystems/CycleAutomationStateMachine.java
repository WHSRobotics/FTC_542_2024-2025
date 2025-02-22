package org.whitneyrobotics.ftc.teamcode.Subsystems;

public class CycleAutomationStateMachine {
    public enum CycleMode { INTAKE, OUTTAKE }
    public enum IntakeState { IDLE, HORIZON_OUT, INTAKE_DOWN, COMPLETE }
    public enum OuttakeState { IDLE, HORIZON_IN, WRISTUP, SERVO_SWAP, ELBOW_FLIP, COMPLETE }

    private final RobotImpl robot;
    private CycleMode currentMode = CycleMode.INTAKE;
    private IntakeState currentIntakeState = IntakeState.IDLE;
    private OuttakeState currentOuttakeState = OuttakeState.IDLE;
    private double stateStartTime = getCurrentTime();
    private boolean active = false;
    private double delayIdleIntake = 0.0;
    private double delayHorizonOutIntake = 0.1;
    private double delayIntakeDown = 0.1;
    private double delayIdleOuttake = 0.0;
    private double delayHorizonIn = 0.1;
    private double delayWristUp = 1.5;
    private double delayServoSwap = 1;
    private double delayElbowFlip = 1.0;

    public CycleAutomationStateMachine(RobotImpl robot) {
        this.robot = robot;
    }

    public void toggle() {
        if (!active) {
            active = true;
            stateStartTime = getCurrentTime();
            return;
        }
        if (currentMode == CycleMode.INTAKE && currentIntakeState != IntakeState.COMPLETE) {
            advanceIntakeState();
        } else if (currentMode == CycleMode.OUTTAKE && currentOuttakeState != OuttakeState.COMPLETE) {
            advanceOuttakeState();
        } else {
            switchMode();
        }
    }

    public void update() {
        if (!active) return;
        double currentTime = getCurrentTime();
        if (currentMode == CycleMode.INTAKE) {
            double delayVal = getDelayForCurrentIntakeState();
            if (currentTime - stateStartTime >= delayVal) {
                advanceIntakeState();
            }
        } else {
            double delayVal = getDelayForCurrentOuttakeState();
            if (currentTime - stateStartTime >= delayVal) {
                advanceOuttakeState();
            }
        }
    }

    private double getDelayForCurrentIntakeState() {
        switch (currentIntakeState) {
            case IDLE:
                return delayIdleIntake;
            case HORIZON_OUT:
                return delayHorizonOutIntake;
            case INTAKE_DOWN:
                return delayIntakeDown;
            case COMPLETE:
                return 0;
        }
        return 0;
    }

    private double getDelayForCurrentOuttakeState() {
        switch (currentOuttakeState) {
            case IDLE:
                return delayIdleOuttake;
            case HORIZON_IN:
                return delayHorizonIn;
            case WRISTUP:
                return delayWristUp;
            case SERVO_SWAP:
                return delayServoSwap;
            case ELBOW_FLIP:
                return delayElbowFlip;
            case COMPLETE:
                return 0;
        }
        return 0;
    }

    private void advanceIntakeState() {
        switch (currentIntakeState) {
            case IDLE:
                currentIntakeState = IntakeState.HORIZON_OUT;
                robot.horizontalServo.currentState = HorizontalServo.Positions.EXTEND;
                robot.OuttakeServo.currentState = outtakeServo.Positions.OPEN;
                robot.elbowWrist.position = ElbowWrist.ElbowState.DOWN;
                break;
            case HORIZON_OUT:
                currentIntakeState = IntakeState.INTAKE_DOWN;
                robot.intakeWrist.position = ClawWrist.ClawState.DOWN;
                break;
            case INTAKE_DOWN:
                currentIntakeState = IntakeState.COMPLETE;
                break;
            case COMPLETE:
                break;
            default:
                break;
        }
        stateStartTime = getCurrentTime();
    }

    private void advanceOuttakeState() {
        switch (currentOuttakeState) {
            case IDLE:
                currentOuttakeState = OuttakeState.HORIZON_IN;
                robot.horizontalServo.currentState = HorizontalServo.Positions.RETRACT;
                break;
            case HORIZON_IN:
                currentOuttakeState = OuttakeState.WRISTUP;
                robot.intakeWrist.position = ClawWrist.ClawState.UP;
                break;
            case WRISTUP:
                currentOuttakeState = OuttakeState.SERVO_SWAP;
                robot.OuttakeServo.currentState = outtakeServo.Positions.CLOSED;
                robot.intakeServo.currentState = intakeServo.Positions.OPEN;
                break;
            case SERVO_SWAP:
                currentOuttakeState = OuttakeState.ELBOW_FLIP;
                robot.elbowWrist.position = ElbowWrist.ElbowState.UP;
                break;
            case ELBOW_FLIP:
                currentOuttakeState = OuttakeState.COMPLETE;
                break;
            case COMPLETE:
                break;
            default:
                break;
        }
        stateStartTime = getCurrentTime();
    }

    private void switchMode() {
        if (currentMode == CycleMode.INTAKE) {
            currentMode = CycleMode.OUTTAKE;
            currentOuttakeState = OuttakeState.IDLE;
        } else {
            currentMode = CycleMode.INTAKE;
            currentIntakeState = IntakeState.IDLE;
        }
        stateStartTime = getCurrentTime();
    }

    public String getState() {
        if (currentMode == CycleMode.INTAKE) {
            return "INTAKE: " + currentIntakeState.name();
        } else {
            return "OUTTAKE: " + currentOuttakeState.name();
        }
    }

    private static double getCurrentTime() {
        return System.currentTimeMillis() / 1000.0;
    }
}
