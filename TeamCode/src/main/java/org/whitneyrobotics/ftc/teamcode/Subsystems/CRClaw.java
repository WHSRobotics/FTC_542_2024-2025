package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CRClaw {
    CRServo crServoLeft;
    CRServo crServoRight;
    private boolean reversed = true;

    boolean currentState;
    public CRClaw(HardwareMap map){
        crServoLeft = map.get(CRServo.class, "CRservo1");
        crServoRight = map.get(CRServo.class, "CRservo2");

    }
    public void update(){
        crServoLeft.setPower((reversed ? 1 : -1));
        crServoRight.setPower((reversed ? -1 : 1));
    }
    public void stop(){
        crServoLeft.setPower(0);
        crServoRight.setPower(0);

    }
    public void ReverseCR(boolean value){
        reversed = value;
    }

    public void setPower(boolean clockwise){
        if (clockwise){
            crServoLeft.setPower(1);
            crServoRight.setPower(-1);
        }else{
            crServoLeft.setPower(-1);
            crServoRight.setPower(1);

        }
    }
}
