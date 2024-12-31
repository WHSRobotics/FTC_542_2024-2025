package org.whitneyrobotics.ftc.teamcode.Subsystems;

import android.graphics.PostProcessor;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class HorizontalServo {
    public static Servo leftLink;
    public static Servo rightLink;

    //    public static int currentPos=1;
    public Positions currentState = Positions.RETRACT;
    public enum Positions{
        RETRACT(0,0.63),
        EXTEND(0.45,0.3); //was 0.3 but vartype was bugging

        public double value1;
        public double value2;
        Positions(double pos1,double pos2){
            this.value1=pos1;
            this.value2 = pos2;
        }
    }
    public HorizontalServo(HardwareMap map){
        leftLink = map.get(Servo.class,"horizontalLeft");
        rightLink = map.get(Servo.class,"horizontalRight");
        rightLink.setDirection(Servo.Direction.FORWARD);
    }

    public void update(){
        currentState  =Positions.values()[(currentState.ordinal() + 1) % 2];
    }

    public void run(){
        leftLink.setPosition(currentState.value1);
        rightLink.setPosition(currentState.value2);
    }
}