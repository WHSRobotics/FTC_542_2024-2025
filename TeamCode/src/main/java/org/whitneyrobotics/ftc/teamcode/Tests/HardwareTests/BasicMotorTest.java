package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.Toggler;

@TeleOp(name="Motor Test",group="Tests")
public class BasicMotorTest extends OpMode{
    private DcMotor testMotor;
    private Toggler motorToggler=new Toggler(4);
    boolean DRpressed = false; //is dpad_right pressed
    boolean DLpressed = false; //is dpad_left pressed
    boolean DUpressed = false; //is dpad_up pressed
    boolean DDpressed = false; //is dpad_down pressed
    boolean a_pressed = false; //is 'a' pressed
    double desiredPosition = 1; //measured 0 to 2000
    double motorPower=0;
    public void init(){
        testMotor = hardwareMap.dcMotor.get("elbowMotor");
    }
    public void loop() {
        if (gamepad1.b) {
            testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (gamepad1.dpad_right || gamepad1.dpad_left) {
            motorToggler.setState(3);
        } else if (gamepad1.a) {
            motorToggler.setState(2);
        } else if (gamepad1.dpad_up || gamepad1.dpad_down) {
            motorToggler.setState(1);
        } else {
            motorToggler.setState(0);
        }

        if (motorToggler.currentState() == 3 && !DRpressed && desiredPosition<2000) {
            desiredPosition+=1;
            motorPower=desiredPosition-testMotor.getCurrentPosition()/2000;
        } else if (motorToggler.currentState() == 3 && !DLpressed && desiredPosition>0) {
            desiredPosition-=1;
            motorPower=desiredPosition-testMotor.getCurrentPosition()/2000;
        } else if (motorToggler.currentState()==2 && !a_pressed) {
            motorPower=0;
        } else if (motorToggler.currentState()==1 && !DUpressed && motorPower < 1) {
            motorPower+=0.01;
        } else if (motorToggler.currentState()==1 && !DDpressed && motorPower > -1) {
            motorPower-=0.01;
        }

        DRpressed=gamepad1.dpad_right;
        DLpressed=gamepad1.dpad_left;
        a_pressed=gamepad1.a;
        DUpressed=gamepad1.dpad_up;
        DDpressed=gamepad1.dpad_down;

        telemetry.addData("Position: ", testMotor.getCurrentPosition());
        telemetry.addData("Motor on: ", (motorToggler.currentState() == 1) ? true : false);

        testMotor.setPower(motorPower);
    }
}



