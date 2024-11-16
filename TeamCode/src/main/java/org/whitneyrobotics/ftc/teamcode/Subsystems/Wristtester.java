package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;


@TeleOp(name = "Wrist Test", group = "A")
public class Wristtester extends OpModeEx {
    Servo wrist;


    private WristState position = WristState.OPEN;



    public enum WristState {
        OPEN(0),
        CLOSE(1);

        public final double positionnumber;

        WristState(double positionnumber){
            this.positionnumber = positionnumber;
        }

    }

    public void update(){
        position = Wristtester.WristState.values()[(position.ordinal() + 1) % 2];

    }
    public void run(){
        wrist.setPosition(position.positionnumber);
    }

    @Override
    public void initInternal() {
        wrist = hardwareMap.servo.get("wrist");
    }

    @Override
    protected void loopInternal() {
        gamepad1.SQUARE.onPress(e -> {
            update();
            run();
        });
    }


}
