package org.whitneyrobotics.ftc.teamcode.OpMode;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import java.io.Serializable;

public class RobotStatus implements Serializable {
    public final static int serialVersionUID = 1;

    public static Pose2d pose = new Pose2d(0,0,0);
    public static double rawSlidesPosition;

    public void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.writeDouble(pose.getX());
        out.writeDouble(pose.getY());
        out.writeDouble(pose.getHeading());
        out.writeDouble(rawSlidesPosition);
    }

    public void readObject(java.io.ObjectInputStream in) throws java.io.IOException {
        pose = new Pose2d(in.readDouble(), in.readDouble(), in.readDouble());
        rawSlidesPosition = in.readDouble();
    }

}
