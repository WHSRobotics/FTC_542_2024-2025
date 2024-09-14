package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

public class TaigaOdometryTesting {
    public final double WHEEL_DIAMETER = 1.5; // inches
    public final double ENCODER_DIAMETER = 1.41732; // inches
    public final double ROTATION_WHEEL_DIAMETER = 1.95; // inches
    public final double TICKS_PER_REVOLUTION = 8192; // ticks
    public final double WHEEL_TO_ENCODER_DIFFERENCE_ACCOUNTING = WHEEL_DIAMETER / ENCODER_DIAMETER;
    public final double WHEEL_TO_ROTATION_WHEEL_DIFFERENCE_ACCOUNTING = WHEEL_DIAMETER / ROTATION_WHEEL_DIAMETER;

    public final double INCHES_PER_TICK = (2 * Math.PI * WHEEL_DIAMETER) / (TICKS_PER_REVOLUTION * WHEEL_TO_ENCODER_DIFFERENCE_ACCOUNTING);
    public final double ROTATIONS_PER_TICK = (1) / (WHEEL_TO_ROTATION_WHEEL_DIFFERENCE_ACCOUNTING * TICKS_PER_REVOLUTION);

    public DcMotorEx rotation;
    public DcMotorEx xDirection;
    public DcMotorEx yDirection;

    public TaigaOdometryTesting(HardwareMap odomMap){
        rotation = odomMap.get(DcMotorEx.class, "Rotation");
        xDirection = odomMap.get(DcMotorEx.class, "X Direction");
        yDirection = odomMap.get(DcMotorEx.class, "Y Direction");
    }

    public double getXChange(){
        return xDirection.getCurrentPosition() * WHEEL_TO_ENCODER_DIFFERENCE_ACCOUNTING * INCHES_PER_TICK;
    }

    public double getYChange(){
        return yDirection.getCurrentPosition() * WHEEL_TO_ENCODER_DIFFERENCE_ACCOUNTING * INCHES_PER_TICK;
    }

    public double getRotationChange(){
        return rotation.getCurrentPosition() * ROTATIONS_PER_TICK * 360; // degrees
    }
}

