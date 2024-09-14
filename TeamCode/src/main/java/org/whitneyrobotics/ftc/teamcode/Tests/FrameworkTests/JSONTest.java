package org.whitneyrobotics.ftc.teamcode.Tests.FrameworkTests;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.json.JSONException;
import org.json.JSONObject;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.LineItem;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TextLine;
import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.RobotDataUtil;
import org.whitneyrobotics.ftc.teamcode.Libraries.JSON.WHSRobotData;
import org.whitneyrobotics.ftc.teamcode.Subsystems.WHSIMU;

@TeleOp(name="Persistent Data Store Test", group="Z")
@RequiresApi(api = Build.VERSION_CODES.N)
public class JSONTest extends OpModeEx {
    WHSIMU imu;

    @Override
    public void initInternal() {
        WHSRobotData.reset();
        imu = new WHSIMU(hardwareMap);
        initializeDashboardTelemetry(10);
        RobotDataUtil.load(WHSRobotData.class);
        imu.zeroCalibrationOffset(WHSRobotData.heading);
        gamepad1.TRIANGLE.onPress(e -> {
            RobotDataUtil.save(WHSRobotData.class);
            requestOpModeStop();
        });

        gamepad1.SQUARE.onPress(e -> imu.zeroCalibrationOffset());

        String jsonContent = ReadWriteFile.readFile(RobotDataUtil.loadFile(WHSRobotData.class.getName(), ".json"));
        telemetryPro.addItem(new TextLine(jsonContent,true, LineItem.Color.LIME));
        try {
            telemetryPro.addItem(new TextLine(new Boolean(new JSONObject(jsonContent).has("heading")).toString(), true, LineItem.Color.GRAY));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void loopInternal() {
        WHSRobotData.heading = imu.getHeadingYaw();
        telemetry.addData("Heading",WHSRobotData.heading);
        telemetryPro.addLine(RobotDataUtil.getClassWriteableFields(WHSRobotData.class)[0].getName(), LineItem.Color.ROBOTICS);
    }
}
