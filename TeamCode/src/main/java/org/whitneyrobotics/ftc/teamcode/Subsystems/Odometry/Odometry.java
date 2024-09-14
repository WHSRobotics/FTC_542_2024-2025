package org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry;

import org.whitneyrobotics.ftc.teamcode.Libraries.Geometry.Coordinate;

public interface Odometry {
    void update();
    void setInitialPose(Coordinate coordinate);
}
