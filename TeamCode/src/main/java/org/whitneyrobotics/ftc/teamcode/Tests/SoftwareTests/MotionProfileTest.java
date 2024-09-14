package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;

@TeleOp(name= "Motion Profile Test", group = "Software Tests")
@Config
public class MotionProfileTest extends OpModeEx {
    MotionProfileTrapezoidal motionProfile;
    public static double V_MAX = 20, A_MAX = 10;
    public static double GOAL = 100;
    NanoStopwatch stopwatch = new NanoStopwatch();

    @Override
    public void initInternal() {
        motionProfile = new MotionProfileTrapezoidal(V_MAX, A_MAX);
        gamepad1.A.onPress(stopwatch::reset);
        telemetryPro.useDashboardTelemetry(dashboardTelemetry);
        initializeDashboardTelemetry(25);
        telemetryPro.addData("Position",0);
        telemetryPro.addData("Velocity", 0);
        telemetryPro.addData("Acceleration", 0);
    }

    @Override
    public void startInternal(){
        stopwatch.reset();
    }

    @Override
    protected void loopInternal() {
        //Constantly reload constants (heh)
        motionProfile.setMaxAccel(A_MAX);
        motionProfile.setMaxVelocity(V_MAX);
        if (GOAL != motionProfile.getGoal()) motionProfile.setGoal(GOAL);

        if(motionProfile.isFinished(stopwatch.seconds())){
            GOAL = -GOAL;
            stopwatch.reset();
        }

        telemetryPro.addData("Position", motionProfile.positionAt(stopwatch.seconds()));
        telemetryPro.addData("Velocity", motionProfile.velocityAt(stopwatch.seconds()));
        telemetryPro.addData("Acceleration", motionProfile.accelerationAt(stopwatch.seconds()));
        telemetryPro.addData("Pos at t1", motionProfile.positionAt(V_MAX/A_MAX));
    }
}
