package org.whitneyrobotics.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;


@TeleOp(name = "KidsDemoDrive")
public class TestDrive extends OpModeEx {
    DcMotorEx fL,fR,bL,bR;
    @Override
    public void initInternal() {
        fL = hardwareMap.get(DcMotorEx.class, "fL");
        fR = hardwareMap.get(DcMotorEx.class, "fR");
        bL = hardwareMap.get(DcMotorEx.class, "bL");
        bR = hardwareMap.get(DcMotorEx.class, "bR");
        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    @Override
    protected void loopInternal() {

        double drive = -gamepad1.LEFT_STICK_Y.value();


        double turn = -gamepad1.RIGHT_STICK_X.value();

        double leftPower = drive + turn;

        double rightPower = drive - turn;


        fL.setPower((leftPower));
        bL.setPower((leftPower));

        fR.setPower((rightPower));
        bR.setPower((rightPower));
    }
}
