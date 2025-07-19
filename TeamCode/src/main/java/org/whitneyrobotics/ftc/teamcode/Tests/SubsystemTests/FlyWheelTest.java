package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name = "FlyWheel")
public class FlyWheelTest extends OpModeEx {
    DcMotorEx fly1;
    DcMotorEx fly2;
    @Override
    public void initInternal() {
        fly1 = hardwareMap.get(DcMotorEx.class,"fly1");
        fly2 = hardwareMap.get(DcMotorEx.class,"fly2");

    }

    @Override
    protected void loopInternal() {
        fly1.setPower(0.6);
        fly2.setPower(0.6);
    }
}
