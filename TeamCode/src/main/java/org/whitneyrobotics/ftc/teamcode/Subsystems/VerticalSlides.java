package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.GamepadEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;

public class VerticalSlides {
    DcMotorEx vertical;
    private boolean joystick=true;
    private PIDController controller;
    private MotionProfileTrapezoidal motionProfile;
    private NanoStopwatch stopwatch;
    private boolean telemetryInitialized = false;

    public static double p = 0.005, i = 0, d = 0.00028;
    public static double f = 0;
    public static double targetPosition;
    public static double V_MAX = 1000; // max velocity in degrees/s
    public static double A_MAX = 900; // max acceleration in degrees/s^2
    private static final double ticksInDegrees = 700 / 180.0;

    private ControlConstants controlConstants;
    private int initialPosition;

    boolean isGamepad = false;

    public enum AngleTicks {

        ZERO(0),
        ONE(2650);

        double ticks;

        AngleTicks(double tick_pos) {
            ticks = tick_pos;
        }
    }

    public VerticalSlides(HardwareMap map){
        vertical = map.get(DcMotorEx.class, "vertical");
        controller = new PIDController(new ControlConstants(p, i, d));
        vertical.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        vertical.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motionProfile = new MotionProfileTrapezoidal(V_MAX, A_MAX);
        stopwatch = new NanoStopwatch();
    }

    public void setPower(GamepadEx gm){
        vertical.setPower(gm.LEFT_STICK_Y.value());
    }

    public void resetEncoders() {
        vertical.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        vertical.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
    public void setBrake(){
        vertical.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setState(AngleTicks angle) {
        targetPosition = angle.ticks;
        setGoal();

    }
    private void setGoal() {
        stopwatch.reset();
        initialPosition = vertical.getCurrentPosition();
        double error = (targetPosition - initialPosition) / ticksInDegrees;
        motionProfile.setGoal(error);

    }

    public void updateToggleState(GamepadEx gm){
        gm.DPAD_UP.onPress(()->{
            setState(AngleTicks.ONE);
            setGoal();
        });
        gm.DPAD_DOWN.onPress(()->{
            setState(AngleTicks.ZERO);
            setGoal();
        });
    }

    public void update(GamepadEx gm) {
        if(joystick){
            vertical.setPower(gm.LEFT_STICK_Y.value());
        }else{
            vertical.setDirection(DcMotorSimple.Direction.FORWARD);
            double elapsedTime = stopwatch.seconds();
            double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
            int currentPos = vertical.getCurrentPosition();
            double error = desiredPosition - currentPos;
            controller.calculate(error);
            double pidOutput = controller.getOutput();
            double ff = motionProfile.velocityAt(elapsedTime) * f;
            double sum = pidOutput+ff;
            if(targetPosition!=AngleTicks.ZERO.ticks && targetPosition!=AngleTicks.ONE.ticks){
                sum=0;
            }
            vertical.setPower(sum);
        }
    }
    public void autoUpdate() {
        vertical.setDirection(DcMotorSimple.Direction.FORWARD);
        double elapsedTime = stopwatch.seconds();
        double desiredPosition = initialPosition + motionProfile.positionAt(elapsedTime) * ticksInDegrees;
        int currentPos = vertical.getCurrentPosition();
        double error = desiredPosition - currentPos;
        controller.calculate(error);
        double pidOutput = controller.getOutput();
        double ff = motionProfile.velocityAt(elapsedTime) * f;
        double sum = pidOutput+ff;
        if(targetPosition!=AngleTicks.ZERO.ticks && targetPosition!=AngleTicks.ONE.ticks){
            sum=0;
        }
        vertical.setPower(sum);
    }

    public void updateJoystick(GamepadEx gm){
        if(gm.LEFT_STICK_Y.value()!=0){
            joystick=true;
            targetPosition=vertical.getCurrentPosition();
        }else{
            joystick=false;
        }
    }
    public double getTargetPosition(){
        return targetPosition;
    }
    public boolean isJoystick(){
        return joystick;
    }

    public double getCurrentPosition() {
        return vertical.getCurrentPosition();
    }
    public void rotatorSetPower(GamepadEx gm) {
        vertical.setPower(gm.LEFT_STICK_X.value());
    }
}
