package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AutoElbowWrist {
    Servo rightServo;
    Servo leftServo;

    public ElbowPositions positionAuto = ElbowPositions.DOWN ;
    public WallPositions positionAutoWall = WallPositions.DOWN;

    public AutoElbowWrist(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(Servo.class,"elbowLeft");
        rightServo = hardwareMap.get(Servo.class, "elbowRight");
    }
    public enum ElbowPositions{
        DOWN(0,0.9),
        UP(0.8,0.2);

        public final double positionright, positionleft;

        ElbowPositions(double positionright, double positionleft){
            this.positionright = positionright;
            this.positionleft = positionleft;
        }

    }

    public enum WallPositions{
        DOWN(0.9,0.1);

        public final double positionright, positionleft;

        WallPositions(double positionright, double positionleft){
            this.positionright = positionright;
            this.positionleft = positionleft;
        }

    }
    public void wallAutoUpdate(){
        positionAutoWall = WallPositions.DOWN;
    }

    public void wallAutoRun(){
        rightServo.setPosition(positionAutoWall.positionright);
        leftServo.setPosition(positionAutoWall.positionleft);
    }

    public void updateAuto(){
        positionAuto = ElbowPositions.values()[(positionAuto.ordinal() + 1) % 2];
    }
    public void runAuto(){
        rightServo.setPosition(positionAuto.positionright);
        leftServo.setPosition(positionAuto.positionleft);
    }


}
