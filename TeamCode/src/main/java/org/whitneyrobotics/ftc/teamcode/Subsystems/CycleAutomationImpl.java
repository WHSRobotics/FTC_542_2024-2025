package org.whitneyrobotics.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Libraries.StateForge.StateForge;
import org.whitneyrobotics.ftc.teamcode.Libraries.StateForge.StateMachine;

import org.whitneyrobotics.ftc.teamcode.Libraries.StateForge.SubstateMachine;

public class CycleAutomationImpl {
    public static ClawWrist intakeElbow;
    public static HorizontalServo horizontalextension;
    public static ElbowWrist outtakeElbow;
    public static intakeServo intake;

    public enum CycleSystemStates {
        INTAKE, OUTTAKE
    }

    public enum INTAKE_SUBSTATES {
        IDLE, HORIZONOUT, INTAKEDOWN, COMPLETE
    }

    public enum OUTTAKE_SUBSTATES {
        IDLE, CLAWCLOSE, INTAKEUP, HORIZONIN, OUTTAKEOUT, COMPLETE
    }

    boolean toggle = false;

    boolean substateCompleted = false;

    public static StateMachine<CycleSystemStates> stateMachine;
    public CycleAutomationImpl(HardwareMap hardwareMap){
        intakeElbow = new ClawWrist(hardwareMap);
        outtakeElbow = new ElbowWrist(hardwareMap);
        horizontalextension = new HorizontalServo(hardwareMap);
        intake = new intakeServo(hardwareMap);
        stateMachine = new StateForge.StateMachineBuilder<CycleSystemStates>()
                .substate(CycleSystemStates.INTAKE)
                .transitionBehavior(SubstateMachine.TRANSITION_BEHAVIOR.RESET_INTERNAL_STATE)
                .buildEmbeddedStateMachine(s ->
                        s.state(INTAKE_SUBSTATES.IDLE)
                                .fin()
                                .state(INTAKE_SUBSTATES.HORIZONOUT)
                                .onEntry(() -> horizontalextension.currentState = HorizontalServo.Positions.EXTEND)
                                .timedTransitionLinear(0.1)
                                .fin()
                                .state(INTAKE_SUBSTATES.INTAKEDOWN)
                                .onEntry(() -> intakeElbow.position = ClawWrist.ClawState.DOWN)
                                .timedTransitionLinear(0.1)
                                .fin()
                                .state(INTAKE_SUBSTATES.COMPLETE)
                                .transitionLinear(() -> true)
                                .fin()
                )
                .transitionWithEmbeddedStateMachine(s -> s.getMachineState() == INTAKE_SUBSTATES.COMPLETE)
                .fin()
                .substate(CycleSystemStates.OUTTAKE)
                .transitionBehavior(SubstateMachine.TRANSITION_BEHAVIOR.RESET_INTERNAL_STATE)
                .buildEmbeddedStateMachine(s ->
                        s.state(OUTTAKE_SUBSTATES.IDLE)
                                .fin()
                                .state(OUTTAKE_SUBSTATES.CLAWCLOSE)
                                .onEntry(() -> intake.currentState = intakeServo.Positions.CLOSED)
                                .timedTransitionLinear(0.1)
                                .fin()
                                .state(OUTTAKE_SUBSTATES.INTAKEUP)
                                .onEntry(() -> intakeElbow.position = ClawWrist.ClawState.UP)
                                .timedTransitionLinear(0.1)
                                .fin()
                                .state(OUTTAKE_SUBSTATES.HORIZONIN)
                                .onEntry(() -> horizontalextension.currentState = HorizontalServo.Positions.RETRACT)
                                .timedTransitionLinear(0.1)
                                .fin()
                                .state(OUTTAKE_SUBSTATES.OUTTAKEOUT)
                                .onEntry(() -> outtakeElbow.position = ElbowWrist.ElbowState.UP)
                                .timedTransitionLinear(0.1)
                                .fin()
                                .state(OUTTAKE_SUBSTATES.COMPLETE)
                                .transitionLinear(() -> true)
                                .fin()
                )
                .transitionWithEmbeddedStateMachine(s -> s.getMachineState() == OUTTAKE_SUBSTATES.COMPLETE)
                .fin()
                .build();

        stateMachine.start();
    }

    public void toggle(){
        if(stateMachine.getState() instanceof SubstateMachine){
           ((SubstateMachine<?,?>) stateMachine.getState())
                    .getEmbeddedStateMachine()
                    .transitionNextLinear();
            toggle=!toggle;
        } else {
            stateMachine.transitionNextLinear();
        }
    }
    public void update(){
        stateMachine.update();
        intakeElbow.run();
        intake.run();
        horizontalextension.run();
        outtakeElbow.run();
    }

    public String getState(){
        String s =  stateMachine.getMachineState().name();
        if(stateMachine.getState() instanceof SubstateMachine){
            s += ((SubstateMachine<?,?>) stateMachine.getState()).getSubstate().name();
        }
        return s;
    }

    public boolean getToggle(){
        return toggle;
    }
}