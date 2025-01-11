package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.GamepadEx;

public class intakeServo {
    public static Servo intakeServo;

    public Positions currentState = Positions.OPEN;

    public boolean override = false;

    public int count=0;

    public enum Positions {
        OPEN(0),
        CLOSED(0.5);

        public double value1;

        Positions(double pos1) {
            this.value1 = pos1;
        }
    }

    public intakeServo(HardwareMap map) {
        intakeServo = map.get(Servo.class, "intakeServo");
    }

    public void updateState() {
        currentState = Positions.values()[(currentState.ordinal() + 1) % 2];
    }

    public void run() {
        intakeServo.setPosition(currentState.value1);
    }


    public void OPEN() {
        intakeServo.setPosition(Positions.OPEN.value1);
    }

    public void CLOSED() {
        intakeServo.setPosition(Positions.CLOSED.value1);
    }
    public void beamBreakUpdate(boolean beamState, GamepadEx gp){
        if (!(override)) {
            //beam is not broken.
            if (beamState && count==0) {
                currentState = Positions.CLOSED;
                count=1;
            }
            gp.TRIANGLE.onPress(() -> {
                if (currentState == Positions.CLOSED) {
                    currentState = Positions.OPEN;
                }
            });
            if(!(beamState)){
                count=0;
            }
        }else{
            gp.TRIANGLE.onPress(()->{
                currentState= Positions.values()[(currentState.ordinal() + 1) % 2];
            });
        }
    }
    public void setOverride(GamepadEx gp){
        gp.CIRCLE.onPress(()->{
            override=!(override);
        });
    }
    public boolean getOverride(){
        return override;
    }
}
