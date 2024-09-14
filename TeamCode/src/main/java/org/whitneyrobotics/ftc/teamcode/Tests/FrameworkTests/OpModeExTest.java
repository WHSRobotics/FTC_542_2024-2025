package org.whitneyrobotics.ftc.teamcode.Tests.FrameworkTests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.ProgressBarLine;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.SliderDisplayLine;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TextLine;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.Functions;

@TeleOp(name="Better Telemetry Test")
@RequiresApi(api = Build.VERSION_CODES.N)
public class OpModeExTest extends OpModeEx {

    private double progress = 0.0d;
    private double scroll = 0.0;

    private double power = 0.0d;

    public double getProgress(){
        return getRuntime();
    }

    @Override
    public void initInternal() {
        gamepad1.A.onPress(event -> telemetryPro.toggleLineNumbers());

        initializeDashboardTelemetry(50);

        addTemporalCallback(resolve -> {
            playSound("slay",50.0f);
            resolve.accept(true);
        }, 2000);
        addTemporalCallback(resolve -> {
            telemetryPro.addLine("Hello!", LineItem.Color.RED);
            resolve.accept(true);
        }, 5000);

        ProgressBarLine timeProgress = new ProgressBarLine("Time",this::getProgress, LineItem.Color.FUCHSIA).setScale(20);
        telemetryPro.addItem(timeProgress);

        SliderDisplayLine scrollSlider = new SliderDisplayLine("scrollSlider",() -> scroll, LineItem.Color.AQUA);
        scrollSlider.setScale(1000);
        telemetryPro.addItem(scrollSlider);

        SliderDisplayLine rightToggle = new SliderDisplayLine("Right Trigger", () -> (double) gamepad1.RIGHT_TRIGGER.value(), LineItem.Color.LIME);
        telemetryPro.addItem(rightToggle);
        Double.valueOf(0.1f);
        gamepad1.RIGHT_STICK_X.onInteraction(event -> {
            //betterTelemetry.addLine(String.format("Stick moved to %s", event.floatVal), LineItem.Color.ROBOTICS);
            scroll = Functions.clamp((scroll + event.floatVal) * (gamepad1.CIRCLE.value() ? 5 : 1) ,0,1000);
        });

        gamepad1.SQUARE.onButtonHold(event -> {
            telemetryPro.addItem(new TextLine(String.format("Button held!",event.consecutivePresses), true, LineItem.Color.FUCHSIA, LineItem.RichTextFormat.BOLD));
        });

        Gamepad og = gamepad1.getEncapsulatedGamepad();
        og.setLedColor(255,221,0,5000);
    }

    @Override
    protected void loopInternal() {
        //double magnitude = Math.abs(gamepad1.RIGHT_STICK_X.value());
        //gamepad1.getEncapsulatedGamepad().rumble(magnitude, magnitude, 10);
    }
}
