package org.whitneyrobotics.ftc.teamcode.Subsystems;

public interface SubsystemIterative {
    /**
     * Method to be called in OpMode/OpModeEx init()/initInternal() method
     */
    default void init() {}

    /**
     * Method to be called in OpMode/OpModeEx init_loop()/initLoopInternal() method
     * Place all hardware phase changes in this method
     */
    void update();

    /**
     * Resets the subsystem to a default state
     */
    void reset();
}
