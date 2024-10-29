package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;


public class RotationSlides{
    private PIDController controller;
    private MotionProfileTrapezoidal motionProfile;
    private NanoStopwatch stopwatch;
    private boolean telemetryInitialized = false;

    public static double p = 0.005, i = 0, d = 0;
    public static double f = 0.002;
    public static AngleTicks targetPosition = AngleTicks.ZERO;

    public static double V_MAX = 240; // max velocity in degrees/s
    public static double A_MAX = 100; // max acceleration in degrees/s^2
    private static final double ticksInDegrees = 700 / 180.0;

    private ControlConstants controlConstants;
    private DcMotorEx armMotor;
    private int initialPosition;

    public enum AngleTicks{
        ZERO(0),
        DROP(5000);

        double ticks;

        AngleTicks(double tick_pos){
            ticks = tick_pos;
        }

    }
    public RotationSlides(HardwareMap map){
        controlConstants = new ControlConstants(0.005, 0, 0);
        controller = new PIDController(controlConstants);
        armMotor = map.get(DcMotorEx.class, "rotator");

        motionProfile = new MotionProfileTrapezoidal(V_MAX, A_MAX);
        stopwatch = new NanoStopwatch();

    }
    public double get_Current_Position(){
        initialPosition = armMotor.getCurrentPosition();
        return (initialPosition);
    }
    public void reset_Encoders(){
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setState(AngleTicks angle){
        targetPosition = angle;
    }

    public void set_Goal() {
        stopwatch.reset();

        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        // Reset stopwatch and create a new motion profile for the target

        initialPosition = armMotor.getCurrentPosition();

        int currentPos = armMotor.getCurrentPosition();
        double error = (targetPosition.ticks - currentPos) / ticksInDegrees;
        motionProfile.setGoal(error);
    }
    public void reset_time(){
    }
    public double get_Error(){
        int currentPos = armMotor.getCurrentPosition();
        return((targetPosition.ticks - currentPos) / ticksInDegrees);
    }

    public void update() {
        // Update PID control constants
        controlConstants = new ControlConstants(0.005, 0, 0);
        controller.setConstants(controlConstants);

        double elapsedTime = stopwatch.seconds();
        double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
        double desiredVelocity = motionProfile.velocityAt(elapsedTime);

        int currentPos = armMotor.getCurrentPosition();
        double error = (desiredPosition - currentPos);
        controller.calculate(error);

        // Feedforward term for desired velocity
        double ff = desiredVelocity * f;
        double pid = controller.getOutput();
        double power = pid + ff;

        armMotor.setPower(power);

        // Telemetry updates

    }
}
