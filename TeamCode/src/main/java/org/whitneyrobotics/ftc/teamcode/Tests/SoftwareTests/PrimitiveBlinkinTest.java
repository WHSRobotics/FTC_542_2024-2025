package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevSPARKMini;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name="Ooga Booga Blinkin", group="tests")
@Config
public class PrimitiveBlinkinTest extends OpModeEx {
    Servo test;
    public static double pos = 0.0;
    @Override
    public void initInternal() {
        test = hardwareMap.get(Servo.class, "test");
    }

    @Override
    protected void loopInternal() {
        test.setPosition(pos);
    }
}
