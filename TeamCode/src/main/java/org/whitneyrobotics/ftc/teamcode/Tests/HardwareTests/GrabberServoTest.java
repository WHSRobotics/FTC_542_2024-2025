package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.SliderDisplayLine;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDControlledMotor;
import org.whitneyrobotics.ftc.teamcode.Tests.Test;

@Config
@TeleOp(group="HardwareTests")
public class GrabberServoTest extends OpModeEx {
    public static double pos;

    Servo gate;
    @Override
    public void initInternal() {
        gate = hardwareMap.get(Servo.class,"gate");
    }

    @Override
    protected void loopInternal() {
        gate.setPosition(pos);
    }

    @TeleOp(
            name="MotorPIDTest",
            group="Tests"
    )
    @Config
    @Test(name="MotorPIDTest")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static class MotorPIDTest extends OpModeEx {
        private DcMotorEx motor;
        private PIDControlledMotor motorPID;
        public static double kP = 1;
        public static double kD = 0;
        public static double kI = 0;
        //private PIDCoefficients pidConstants = new PIDCoefficients(1,0,0);
        //private PIDControllerNew pid = new PIDControllerNew();

        /**
         * User defined init method
         * <p>
         * This method will be called once when the INIT button is pressed.
         */
        @Override
        public void initInternal() {
            initializeDashboardTelemetry(10);
            motor = hardwareMap.get(DcMotorEx.class, "motor");
            motorPID = new PIDControlledMotor(motor, 500);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            gamepad1.Y.onShortPress(e -> motorPID.reloadCoefficients(kP,kI,kD));

            telemetryPro.addItem(new SliderDisplayLine("Motor velocity", () -> motor.getVelocity(AngleUnit.RADIANS), LineItem.Color.AQUA)
                    .setMin(-motorPID.MAX_VELOCITY)
                    .setScale(motorPID.MAX_VELOCITY)
            );
        }

        @Override
        public void startInternal(){
            //pid.reset();
            motorPID.reset();
        }

        /**
         * User defined loop method
         * <p>
         * This method will be called repeatedly in a loop while this op mode is running
         */
        @Override
        public void loopInternal() {
            double brakePower = gamepad1.LEFT_TRIGGER.value();
            motorPID.brake(brakePower);
            double target = 10*Math.sin((Math.PI/10) * getRuntime()) * brakePower;
            motorPID.setTargetVelocity(target);
            //pid.calculate(motor.getVelocity(AngleUnit.RADIANS));
            //double output = pid.getOutput();
            //double appliedPower = Math.signum(output) * Functions.map(Math.abs(output),0,15,0,1);
            //motor.setPower(Math.pow(appliedPower,(1/3)));
            motorPID.update();

            telemetry.addData("Motor Velocity",motor.getVelocity(AngleUnit.RADIANS));
            telemetry.addData("Target",target);
            telemetry.addData("Output",motorPID.controller.getOutput());
            telemetry.addData("Applied power", motorPID.getMotor().getPower());
            telemetry.addData("Error",target-motor.getVelocity(AngleUnit.RADIANS));
            telemetry.addData("Integral",motorPID.controller.getIntegral());
            telemetry.addData("Derivative",motorPID.controller.getDerivative());
        }
    }
}
