package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class IntakeClaw {
    public static Servo servo1;
    public static Servo servo2;

    //    public static int currentPos=1;
    public Positions currentState = Positions.OPEN;
    public enum Positions{
        OPEN(0,0.9),
        CLOSED(0.4,0.5); //was 0.3 but vartype was bugging

        public double value1;
        public double value2;

        Positions(double pos1,double pos2){
            this.value1=pos1;
            this.value2 = pos2;
        }
    }
    public IntakeClaw(HardwareMap map){
        servo1 = map.get(Servo.class,"claw1");
        servo2 = map.get(Servo.class,"claw2");

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
        servo1.setPosition(currentState.value1);
        servo2.setPosition(currentState.value2);
    }


}
