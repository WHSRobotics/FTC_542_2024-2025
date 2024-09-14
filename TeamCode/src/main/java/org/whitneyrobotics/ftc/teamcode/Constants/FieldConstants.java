package org.whitneyrobotics.ftc.teamcode.Constants;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import static org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.UnitConversion.DistanceUnit.TILE_WIDTH;
import static org.whitneyrobotics.ftc.teamcode.Constants.RobotDetails.ROBOT_LENGTH;
public class FieldConstants {
    public enum FieldSide {
        BACKSTAGE, AUDIENCE
    }

    public static final double BACKDROP_EDGE_FROM_WALL = 11.25;
    public static final double PIXEL_WIDTH = 3;

    //TODO: Switch headings if starting robot backwards
    public enum StartingTiles {
        BLUE_A2(new Pose2d(TILE_WIDTH.toInches(-1.5),
                TILE_WIDTH.toInches(3)-ROBOT_LENGTH/2,
                Math.PI/2)),
        BLUE_A4(new Pose2d(TILE_WIDTH.toInches(0.5),
                TILE_WIDTH.toInches(3)-ROBOT_LENGTH/2,
            Math.PI/2)),
        RED_F2(new Pose2d(
                TILE_WIDTH.toInches(-1.5),
                TILE_WIDTH.toInches(-3)+ROBOT_LENGTH/2,
                -Math.PI/2
        )),
        RED_F4(new Pose2d(
                TILE_WIDTH.toInches(0.5),
                TILE_WIDTH.toInches(-3)+ROBOT_LENGTH/2,
                -Math.PI/2
        ));
        public final Pose2d pose;
        StartingTiles(Pose2d pose){
            this.pose = pose;
        }
    }
}
