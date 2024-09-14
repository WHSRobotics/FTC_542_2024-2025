package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@Config
@TeleOp(name = "elbowmotor", group = "motors")

public class ElbowMotorOpMode extends OpModeEx {

    DcMotorEx elbow_motor;
    public static double power = -0.4;
    @Override
    public void initInternal() {
        elbow_motor = hardwareMap.get(DcMotorEx.class, "slidesJoint");
    }

    @Override
    protected void loopInternal() {
        elbow_motor.setPower(power);
    }
}
