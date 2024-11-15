package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDFController;

public class Ascend {
    private static DcMotorEx slides;
    private static DcMotorEx rotator;
    private PIDFController rotatorController;
    private ControlConstants rotatorConstants;
    private PIDFController slidesController;
    private ControlConstants slidesConstants;
    private double rotatorDesired;
    private double slidesDesired;
    public Ascend(HardwareMap hardwareMap){
        slides = hardwareMap.get(DcMotorEx.class, "slides");
        rotator = hardwareMap.get(DcMotorEx.class, "rotator");
        rotatorConstants = new ControlConstants(1,1,1);
        rotatorController = new PIDFController(rotatorConstants);
        slidesConstants = new ControlConstants(1,1,1);
        slidesController = new PIDFController(slidesConstants);
        rotatorDesired = 0;
        slidesDesired = 0;
    }

    public void slidesInputPower(double power){
        rotatorDesired = power;
    }

    public void rotatorInputPower(double Rpower){
        slidesDesired = Rpower * 0.05;
    }
    public void update(){
        rotatorController.calculate(rotatorDesired-rotator.getCurrentPosition(), rotator.getCurrentPosition(), rotator.getVelocity());
        rotator.setPower(rotatorController.getOutput());
        slidesController.calculate(slidesDesired-slides.getCurrentPosition(), slides.getCurrentPosition(), slides.getVelocity());
        slides.setPower(slidesController.getOutput());
    }
}