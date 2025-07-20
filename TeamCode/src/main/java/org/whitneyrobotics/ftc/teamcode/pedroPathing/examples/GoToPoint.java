package org.whitneyrobotics.ftc.teamcode.pedroPathing.examples;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.whitneyrobotics.ftc.teamcode.pedroPathing.constants.FConstants;
import org.whitneyrobotics.ftc.teamcode.pedroPathing.constants.LConstants;

@Autonomous(name = "Straight Line Test", group = "Examples")
public class GoToPoint extends OpMode {

    private Follower follower;
    private Path path;
    private int state;

    // Start and end poses: (x, y, heading)
    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    private final Pose endPose = new Pose(0, -10, Math.toRadians(90));

    @Override
    public void init() {
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);

        path = new Path(new BezierLine(new Point(startPose), new Point(endPose)));
        path.setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading());
    }

    @Override
    public void start() {
        state = 0;
        follower.followPath(path);
    }

    @Override
    public void loop() {
        follower.update();

        if (state == 0 && !follower.isBusy()) {
            telemetry.addLine("Arrived at destination.");
            state = 1;
        }

        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", Math.toDegrees(follower.getPose().getHeading()));
        telemetry.update();
    }

    @Override
    public void stop() {}
}
