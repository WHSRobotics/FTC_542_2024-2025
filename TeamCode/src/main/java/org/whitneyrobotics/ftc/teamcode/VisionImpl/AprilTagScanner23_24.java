package org.whitneyrobotics.ftc.teamcode.VisionImpl;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTagScanner23_24 {
    public AprilTagProcessor tagProcessor;

    //private final VisionPortal visionPortal;
    private double lastDistance;

    private  List<AprilTagDetection> currentDetections;

    private AprilTagDetection desiredTag;

    public AprilTagScanner23_24(){
        lastDistance = -1;

        /*
        visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, webcam))
                .setCameraResolution(new Size(800, 448))
                .build();

         */
    }

    public void update(){
        currentDetections = tagProcessor.getDetections();
    }


    public Double getLastDistance(){return lastDistance;}

    public Double gaugeDistance(){
        AprilTagDetection tag;
        if (tagProcessor.getDetections().size() > 0) {
            tag = tagProcessor.getDetections().get(0);
            lastDistance = tag.ftcPose.y;
            return tag.ftcPose.y;
        }
        lastDistance = -1;
        return -1.0;
    }

}
