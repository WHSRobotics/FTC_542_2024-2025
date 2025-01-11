//package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;
//
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//
//import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
//import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.ControlConstants;
//import org.whitneyrobotics.ftc.teamcode.Libraries.Controllers.PIDController;
//import org.whitneyrobotics.ftc.teamcode.Libraries.Motion.MotionProfileTrapezoidal;
//import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.NanoStopwatch;
//import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.Timer;
//import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.opmode.MaxVelocityTuner;
//
//public class VertPID extends OpModeEx {
//    DcMotorEx ls;
//    NanoStopwatch timer;
//    MotionProfileTrapezoidal motionProfile;
//    PIDController pid;
//    public double kP, kI, kD;
//    public ControlConstants.FeedforwardFunction kF;
//
//    public ControlConstants constants;
//
//    public int aMax, vMax;
//
//    @Override
//    public void initInternal() {
//        ls = hardwareMap.get(DcMotorEx.class, "vertical");
//
//         constants = new ControlConstants(kP, kI, kD, kF);
//         pid.setConstants(constants);
//    }
//
//    @Override
//    protected void loopInternal() {
//
//
//    }
//}
