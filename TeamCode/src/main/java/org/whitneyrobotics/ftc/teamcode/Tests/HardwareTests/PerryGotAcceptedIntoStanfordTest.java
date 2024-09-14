package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@TeleOp(name = "ServoTest", group = "TeleOp")
public class PerryGotAcceptedIntoStanfordTest extends OpMode {

    private Servo testServo;

    @Override
    public void init() {
        // Initialize the servo
        testServo = hardwareMap.servo.get("testServo");

        // You may need to adjust the servo name "testServo" based on your actual configuration

        // Set the servo position to make it spin during initialization
        testServo.setPosition(0); // 0.5 is the middle position; adjust as needed
    }
    @Override
    public void loop() {
        // No need for continuous loop in this case
    }
}
