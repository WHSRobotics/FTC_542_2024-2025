package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

public class Claw {
    Servo rightServo;
    Servo leftServo;

    private ClawState position = ClawState.OPEN;

    public Claw (HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
    }

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
}
