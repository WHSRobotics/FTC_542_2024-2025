package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Wrist{
    Servo wrist;


    private WristState position = WristState.OPEN;

    public Wrist(HardwareMap hardwareMap){
        wrist = hardwareMap.servo.get("wrist");
    }



    public enum WristState {
        OPEN(0),

        HALF(0.27),

        CLOSE(1);

        public final double positionnumber;

        WristState(double positionnumber){
            this.positionnumber = positionnumber;
        }

    }

    public void update(){
        position = Wrist.WristState.values()[(position.ordinal() + 1) % 2];

    }
    public void run(){
        wrist.setPosition(position.positionnumber);
    }

    public void Open(){
        wrist.setPosition(WristState.OPEN.positionnumber);
    }
    public void Half(){
        wrist.setPosition(WristState.HALF.positionnumber);

    }
    public void Close(){
        wrist.setPosition(WristState.CLOSE.positionnumber);

    }

}