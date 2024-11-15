package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "clawtest", group = "A")
public class clawtest extends OpModeEx {
    Servo rightServo;
    Servo leftServo;

    private ClawState position = ClawState.OPEN;

    public enum ClawState{
        OPEN(0.6,0.45),
        CLOSE(1,0.27);

        public final double positionright, positionleft;

        ClawState(double positionright, double positionleft){
            this.positionright = positionright;
            this.positionleft = positionleft;
        }

    }


    public void update(){
        position = ClawState.values()[(position.ordinal() + 1) % 2];
    }

    public void run(){
        rightServo.setPosition(position.positionright);
        leftServo.setPosition(position.positionleft);
    }
    @Override
    public void initInternal() {
        rightServo = hardwareMap.servo.get("rightServo");
        leftServo = hardwareMap.servo.get("leftServo");
    }

    @Override
    protected void loopInternal() {

        gamepad1.TRIANGLE.onPress(e -> {
            update();
            run();
        });


    }
}
