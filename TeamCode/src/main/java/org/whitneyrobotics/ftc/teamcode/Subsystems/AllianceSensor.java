package org.whitneyrobotics.ftc.teamcode.Subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.whitneyrobotics.ftc.teamcode.Constants.Alliance;

import java.util.function.Consumer;

public class AllianceSensor {
    private TouchSensor magnet;

    private boolean previousState;

    private Consumer<Boolean> onChangeAction = (state) -> {};

    public void onChange(@NonNull Consumer<Boolean> action) {
        onChangeAction = action;
    }

    public AllianceSensor(HardwareMap hardwareMap){
        this.magnet = hardwareMap.get(TouchSensor.class, "magnet");
    }

    public void update(){
        boolean state = magnet.isPressed();
        if(state != previousState){
            onChangeAction.accept(state);
            previousState = state;
        }
    }

    public boolean isRedAlliance(){
        return magnet.isPressed();
    }

}
