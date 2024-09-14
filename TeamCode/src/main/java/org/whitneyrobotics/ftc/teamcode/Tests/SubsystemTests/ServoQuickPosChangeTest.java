package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "1.10.2024 Servo Quick Test")
public class ServoQuickPosChangeTest extends OpMode {
    Servo servo;
    Servo servo2;

    double pos1 = 0.69;
    double pos2 = 0.22;

    double[] angles = {0.84, 0.6, 0.59, 0.56, 0.54, 0.49, 0.46, 0.40, 0.38}; // 0 degrees, 12 degrees, 20 degrees, 25 degrees, 30 degrees, 38 degrees, 45 degrees, 60 degrees, 65 degrees

    boolean firstPos = true;
    boolean released = true;
    boolean releasedB = true;

    int currentAngle = 0;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "test");
        servo2 = hardwareMap.get(Servo.class, "testTwo");
    }

    @Override
    public void loop() {
        if (gamepad1.a && released){
            firstPos = !firstPos;
            released = false;
        }

        if (gamepad1.b && releasedB){
            currentAngle = (currentAngle + 1) % 10;
            releasedB = false;
        }

        if (!gamepad1.a){
            released = true;
        }

        if (!gamepad1.b){
            releasedB = true;
        }

        if (firstPos){
            servo.setPosition(pos1);
        } else if (!firstPos){
            servo.setPosition(pos2);
        }

        servo2.setPosition(angles[currentAngle]);

        telemetry.addData("Current Angle", angles[currentAngle]);
        telemetry.addData("B Pressed", gamepad1.b ? "Pressed" : "Not Pressed");
    }
}
