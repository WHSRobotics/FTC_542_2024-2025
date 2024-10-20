package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDVAController;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;

@TeleOp(name = "rotatorTest")
@Config
public class RotationSlidesTest extends OpModeEx {
    private static DcMotorEx rotator;  // Ensure motor is not static and properly initialized

    public static double MAX_ACCEl =0.2;
    public static double MAX_VEL = 0.2;

    public static double KP = 1;
    public static double KI = 0;
    public static double KD = 1;

    private static ControlConstants constants = new ControlConstants(KP,KD,KI);

    private static PIDController controller = new PIDController(constants);
    private static double targetPos;
    private static double error = 0;
    public static double TOLERANCE = 30;  // Tolerance for how close we need to be to the target position


    public static void setTargetPosition(double position){
        targetPos = position;
    }
    public void setInitialError(){
        controller.init(rotator.getCurrentPosition());

    }
    public void GoToPosition(){
        error = targetPos - rotator.getCurrentPosition();

        controller.calculate(error);
        // If the error is within the tolerance, stop the motor
        if (Math.abs(error) <= TOLERANCE) {
            rotator.setPower(0);  // Stop the motor if within tolerance
        }else{
            rotator.setPower(controller.getOutput());
        }

    }
    @Override
    public void initInternal() {

        rotator = hardwareMap.get(DcMotorEx.class, "rotator");
        rotator.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);  // Set brake behavior
        rotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotator.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);  // Ensure motor is in encoder mode
        setInitialError();
        telemetryPro.addData("Current Position", rotator.getCurrentPosition());
        telemetryPro.addData("Motor Power", rotator.getPower());
        telemetryPro.update();

    }

    @Override
    protected void loopInternal() {
        setTargetPosition(5000);

        GoToPosition();
        // Add telemetry to check the motor status
        telemetryPro.addData("Current Position", rotator.getCurrentPosition());
        telemetryPro.addData("Motor Power", rotator.getPower());
        telemetryPro.addData("PID Output", controller.getOutput());
        telemetryPro.update();
    }
}
