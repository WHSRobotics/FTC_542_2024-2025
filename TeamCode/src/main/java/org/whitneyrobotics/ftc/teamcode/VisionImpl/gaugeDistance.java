package org.whitneyrobotics.ftc.teamcode.VisionImpl;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "gaugeDistance", group = "1")
public class gaugeDistance extends LinearOpMode{
    public static double actualDistance;

    public void runOpMode() throws InterruptedException {
        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(800, 448))
                .build();


        waitForStart();

        AprilTagDetection tag = null;
        while (!isStopRequested() && opModeIsActive()) {
            if (tagProcessor.getDetections().size() > 0) {
                tag = tagProcessor.getDetections().get(0);

                telemetry.addData("Distance from AprilTag (Y - Straight)", tag.ftcPose.y);
                telemetry.addData("Distance from AprilTag (X - RTL)", tag.ftcPose.x);
            }

            telemetry.update();
        }

        actualDistance = tag.ftcPose.y;
    }
}
