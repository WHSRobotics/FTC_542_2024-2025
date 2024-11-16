package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.GamepadEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;


public class RotatorMotor {
    private PIDController controller;
    private MotionProfileTrapezoidal motionProfile;
    private NanoStopwatch stopwatch;
    private boolean telemetryInitialized = false;

    public static double p = 0.005, i = 0, d = 0.00028;
    public static double f = 0;
    public static AngleTicks targetPosition = AngleTicks.ZERO;

    public static double V_MAX = 2350; // max velocity in degrees/s
    public static double A_MAX = 700; // max acceleration in degrees/s^2
    private static final double ticksInDegrees = 700 / 180.0;

    private ControlConstants controlConstants;
    private DcMotorEx armMotor;
    private DcMotorEx slides;
    private int initialPosition;

    boolean isGamepad = false;

    public enum AngleTicks{

        ZERO(0),
        ONE(200),
        TWO(600),

        THREE(1100),

        FOUR(1800);

        double ticks;

        AngleTicks(double tick_pos){
            ticks = tick_pos;
        }

    }
    public RotatorMotor(HardwareMap map) {
        controller = new PIDController(new ControlConstants(p, i, d));
        armMotor = map.get(DcMotorEx.class, "rotator");
        slides = map.get(DcMotorEx.class,"slides");
//        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motionProfile = new MotionProfileTrapezoidal(V_MAX, A_MAX);
        stopwatch = new NanoStopwatch();
    }

    public void resetEncoders() {
        armMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void setState(AngleTicks angle) {
        targetPosition = angle;
        setGoal();

    }

    public void setGamepadState(AngleTicks angle){
        targetPosition = angle;
        setGoal();
    }
    public void GamepadUsed(boolean isUsed){
        isGamepad = isUsed;
    }
    private void setGoal() {
        stopwatch.reset();
        initialPosition = -1 * armMotor.getCurrentPosition();
        double error = (targetPosition.ticks - initialPosition) / ticksInDegrees;
        motionProfile.setGoal(error);

    }

    public void update() {
        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        double elapsedTime = stopwatch.seconds();
        double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
        int currentPos = -1 * armMotor.getCurrentPosition();
        double error = desiredPosition - currentPos;
        controller.calculate(error);
        double pidOutput = controller.getOutput();
        double ff = motionProfile.velocityAt(elapsedTime) * f;
        armMotor.setPower(pidOutput + ff);


    }

    public void slideBrake(){
        slides.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
    public double getCurrentPosition() {
        return -1*armMotor.getCurrentPosition();
    }
    public void rotatorSetPower(GamepadEx gm) {
        armMotor.setPower(gm.LEFT_STICK_X.value());
    }
    public void slidesSetPower(GamepadEx gm) {
        slides.setPower(gm.RIGHT_STICK_X.value());
    }
}