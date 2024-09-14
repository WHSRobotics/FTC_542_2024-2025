package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;

@TeleOp(name = "Color Sensor Test - 1.26.2024")
public class ColorSensorTest extends OpModeEx {
    ColorSensor top;

    int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
    final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

    float[] hsvValues = {0F, 0F, 0F};

    @Override
    public void initInternal() {
        top = hardwareMap.get(ColorSensor.class, "csTop");
        top.enableLed(true);
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("TRANSPARENCY", top.alpha(), LineItem.Color.WHITE);
        telemetryPro.addData("RED", top.red(), LineItem.Color.RED);
        telemetryPro.addData("GREEN", top.green(), LineItem.Color.GREEN);
        telemetryPro.addData("BLUE", top.blue(), LineItem.Color.BLUE);
    }
}
