package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "Meet 1 Claw Test", group = "A")
public class Clawtester extends OpModeEx {
    Servo rightServo;
    Servo leftServo;

    private ClawState position = ClawState.OPEN;

    public enum ClawState{
        OPEN(0,0.9),
        CLOSE(0.4,0.5);

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
