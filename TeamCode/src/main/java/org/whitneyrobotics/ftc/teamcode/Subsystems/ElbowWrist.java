package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ElbowWrist {
    Servo rightServo;
    Servo leftServo;

    public ElbowState position = ElbowState.DOWN ;

    public AutoElbowState positionAuto = AutoElbowState.UP ;


    public ElbowWrist(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"elbowLeft");
        rightServo = hardwareMap.get(Servo.class, "elbowRight");
    }

    public enum ElbowState{
        DOWN(0,1),
        UP(1,0);

        public final double positionright, positionleft;

        ElbowState(double positionright, double positionleft){
            this.positionright = positionright;
            this.positionleft = positionleft;
        }

    }


    public void update(){
        position = ElbowState.values()[(position.ordinal() + 1) % 2];
    }

    public void run(){
        rightServo.setPosition(position.positionright);
        leftServo.setPosition(position.positionleft);
    }

    public enum AutoElbowState{
        DOWN(0,1),
        UP(1,0);

        public final double positionright, positionleft;

        AutoElbowState(double positionright, double positionleft){
            this.positionright = positionright;
            this.positionleft = positionleft;
        }

    }

    public void updateAuto(){
        positionAuto = AutoElbowState.values()[(positionAuto.ordinal() + 1) % 2];
    }
    public void runAuto(){
        rightServo.setPosition(positionAuto.positionright);
        leftServo.setPosition(positionAuto.positionleft);
    }

}