package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeServo {
    public static Servo intakeServo;

    public Positions currentState = Positions.OPEN;

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
}
