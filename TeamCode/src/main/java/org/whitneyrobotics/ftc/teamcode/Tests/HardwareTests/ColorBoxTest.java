package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.internal.files.DataLogger;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;

import java.io.IOException;

@TeleOp(name="Color Box Test")
public class ColorBoxTest extends OpModeEx {
    RevColorSensorV3 c1, c2;
    DataLogger c1dl = new DataLogger("color1-" + Math.floor(Math.random() * 1000)+".tsv");

    public ColorBoxTest() throws IOException {
    }

    /*
    BLACK = 0 (RIGHT_BUMPER)
    WHITE = 1 (CROSS)
    YELLOW = 2 (CIRCLE)
    PURPLE = 3 (TRIANGLE)
    GREEN = 4 (SQUARE)
     */

    public void logPixelData(RevColorSensorV3 c, int color) {
        NormalizedRGBA cData = c.getNormalizedColors();
        try {
            c1dl.addDataLine(cData.red, cData.blue, cData.green, cData.alpha, color);
            telemetryPro.addLine("Color "+ color +" logged.").persistent();
        } catch(IOException e) {
            RobotLog.e(e.toString());
        }
    }

    @Override
    public void initInternal() {
        c1 = hardwareMap.get(RevColorSensorV3.class, "color1");
        c2 = hardwareMap.get(RevColorSensorV3.class, "color2");
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        setBulkReadBehavior(BULK_READ_BEHAVIOR.ONE_FRAME_PER_LOOP);
        gamepad1.BUMPER_RIGHT.onPress(() -> logPixelData(c1, 0));
        gamepad1.CROSS.onPress(() -> logPixelData(c1, 1));
        gamepad1.CIRCLE.onPress(() -> logPixelData(c1, 2));
        gamepad1.TRIANGLE.onPress(() -> logPixelData(c1, 3));
        gamepad1.SQUARE.onPress(() -> logPixelData(c1, 4));
    }

    @Override
    protected void loopInternal() {
        NormalizedRGBA c1Data = c1.getNormalizedColors();
        telemetryPro.addData("C1 R", c1Data.red, LineItem.Color.RED);
        telemetryPro.addData("C1 G", c1Data.green, LineItem.Color.GREEN);
        telemetryPro.addData("C1 B", c1Data.blue, LineItem.Color.BLUE);
        telemetryPro.addData("C1 A", c1Data.alpha);

        NormalizedRGBA c2Data = c2.getNormalizedColors();
        telemetryPro.addData("C2 R", c2Data.red, LineItem.Color.RED);
        telemetryPro.addData("C2 G", c2Data.green, LineItem.Color.GREEN);
        telemetryPro.addData("C2 B", c2Data.blue, LineItem.Color.BLUE);
        telemetryPro.addData("C2 A", c2Data.alpha);
    }
    @Override
    public void stop(){
        c1dl.close();
    }
}
