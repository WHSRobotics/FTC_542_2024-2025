package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TextLine;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;

@TeleOp(name="Driver Notification Test")
@RequiresApi(api = Build.VERSION_CODES.N)
public class DriverNotificationTest extends OpModeEx {
    Gamepad.RumbleEffect endgame = new Gamepad.RumbleEffect.Builder()
            .addStep(1,1,500)
            .addStep(0,0,500)
            .addStep(1,1,500)
            .addStep(0,0,500)
            .addStep(1,1,1000)
            .build();

    Gamepad.RumbleEffect matchEnd = new Gamepad.RumbleEffect.Builder()
            .addStep(1,1,250)
            .addStep(0,0,250)
            .addStep(1,1,250)
            .addStep(0,0,250)
            .addStep(1,1,250)
            .addStep(0,0,250)
            .addStep(0.4,0.4,500)
            .addStep(0,0,500)
            .addStep(0.6,0.6,500)
            .addStep(0,0,500)
            .addStep(0.8,0.8,500)
            .addStep(0,0,500)
            .addStep(1,1,500)
            .addStep(0,0,500)
            .addStep(1,1,500)
            .addStep(0,0,500)
            .addStep(1,1,2000)
            .build();

    void setupNotifications(){


        addTemporalCallback(resolve -> {
            playSound("endgame",100f);
            gamepad1.getEncapsulatedGamepad().runRumbleEffect(endgame);
            gamepad2.getEncapsulatedGamepad().runRumbleEffect(endgame);
            telemetryPro.addItem(new TextLine("Endgame approaching soon!",true, LineItem.Color.ROBOTICS, LineItem.RichTextFormat.BOLD));
            resolve.accept(!gamepad1.getEncapsulatedGamepad().isRumbling() && gamepad2.getEncapsulatedGamepad().isRumbling());
        }, 5000);

        addTemporalCallback(resolve -> {
            telemetryPro.removeLineByCaption("Endgame approaching soon!");
            resolve.accept(true);
        },10000);

        addTemporalCallback(resolve -> {
            playSound("matchend",100f);
            gamepad1.getEncapsulatedGamepad().runRumbleEffect(matchEnd);
            gamepad2.getEncapsulatedGamepad().runRumbleEffect(matchEnd);
            telemetryPro.addItem(new TextLine("Match ends in 5 seconds!",true, LineItem.Color.FUCHSIA, LineItem.RichTextFormat.BOLD));
            resolve.accept(!gamepad1.getEncapsulatedGamepad().isRumbling() && gamepad2.getEncapsulatedGamepad().isRumbling());
        }, 12000);

        addTemporalCallback(resolve -> {
            playSound("slay",100f);
            telemetryPro.removeLineByCaption("Match ends in 5 seconds!");
            telemetryPro.addItem(new TextLine("Match has ended.", true, LineItem.Color.LIME, LineItem.RichTextFormat.ITALICS));
            resolve.accept(true);
        },19000);
    }

    @Override
    public void initInternal() {
        setupNotifications();
    }

    @Override
    protected void loopInternal() {
        telemetryPro.addData("Rumble", gamepad1.getEncapsulatedGamepad().isRumbling());
    }
}
