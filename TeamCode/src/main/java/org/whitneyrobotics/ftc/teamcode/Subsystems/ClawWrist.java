package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawWrist {
    public static Servo rightServo;
    public static Servo leftServo;

    public ClawState position = ClawState.UP;

    public ClawWrist(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"wristLeft");
        rightServo = hardwareMap.get(Servo.class, "wristRight");
    }

    public enum ClawState{
        UP(1,0),
        //        MID(0.35,0.55),
        DOWN(0,1);

        public double positionright, positionleft;

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