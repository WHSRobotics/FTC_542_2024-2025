package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Extensions.GamepadEx.GamepadEx;

public class VerticalSlides {
    DcMotorEx vertical;
    public VerticalSlides(HardwareMap map){
        vertical = map.get(DcMotorEx.class, "vertical");
        vertical.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        vertical.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
    public void setPower(GamepadEx gm){
        vertical.setPower(gm.LEFT_STICK_Y.value());
    }
}
