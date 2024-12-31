

package org.whitneyrobotics.ftc.teamcode.Tests.SubsystemTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

@TeleOp(name="AutoLiveTest", group="Tests")
public class AutoLiveTest extends OpModeEx {
    private NanoHTTPD nanoServer;
    private Servo autoServo;
    private Servo secondServo;
    private double servoPosition = 0.0;

    @Override
    public void initInternal() {
        autoServo = hardwareMap.get(Servo.class,"elbowLeft");
        secondServo = hardwareMap.get(Servo.class,"elbowRight");
        try {
            nanoServer = new NanoHTTPD("192.168.43.1",8043) {
                @Override
                public Response serve(IHTTPSession session) {
                    String msg = "<html><body><h1>Hello server</h1>\n";
                    Map<String, String> parms = session.getParms();
                    if (parms.get("slider") != null) {
                        servoPosition = Double.parseDouble(parms.get("slider")) / 100.0;
                        if (servoPosition < 0.0) {
                            servoPosition = 0.0;
                        } else if (servoPosition > 1.0) {
                            servoPosition = 1.0;
                        }
                        autoServo.setPosition(servoPosition);
                        secondServo.setPosition(servoPosition);
                    }
                    msg += "<form><input type='range' name='slider' min='0' max='100' value='" + (servoPosition * 100) + "' onchange='submit()'></form>\n";
                    msg += "<p>Servo position: " + servoPosition + "</p>";
                    return newFixedLengthResponse(msg + "</body></html>\n");
                }
            };
            nanoServer.start();
        } catch (IOException ioe) {
        }
    }

    @Override
    public void loopInternal() {
        telemetryPro.addData("Current Pos:",autoServo.getPosition());

        // No need to call serve() here, NanoHTTPD will handle requests internally
    }
}
