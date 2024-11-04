package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class IntakeClaw {
    public static Servo servo;
//    public static int currentPos=1;
    public Positions currentState = Positions.OPEN;
    public enum Positions{
        OPEN(0),
        CLOSED(0.35); //was 0.3 but vartype was bugging

        public double value;
        Positions(double pos){
            this.value=pos;
        }
    }
    public IntakeClaw(HardwareMap map){
        servo= map.get(Servo.class,"claw");
    }

//    public static void run(){
//        if(currentPos==1){
//            servo.setPosition(Positions.CLOSED.value);
//        }else{
//            servo.setPosition(Positions.OPEN.value);
//        }
//        currentPos*=-1;
//    }

    public void updateState(){
        currentState = Positions.values()[(currentState.ordinal() + 1) % 2];
    }

    public void run(){
//        servo.setPosition(Positions.value);
        servo.setPosition(currentState.value);
    }


}
