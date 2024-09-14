package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry.DroneB;

@TeleOp(name="Drone B Test", group="Hardware Tests")
public class DroneBTest extends OpModeEx {
    DroneB droneB;
    @Override
    public void initInternal() {
        droneB = new DroneB(hardwareMap);
        gamepad1.BUMPER_RIGHT.onPress(droneB::hold);
        gamepad1.SQUARE.onPress(droneB::nextDefinedAngle);
        gamepad1.CROSS.onPress(droneB::fire);
        gamepad1.CIRCLE.onPress(droneB::retract);
        gamepad1.TRIANGLE.onPress(() -> droneB.submitPosition(()->playSound("raw/chime.wav")));
        gamepad1.START.onPress(droneB::reset);
        droneB.init();
    }

    @Override
    protected void loopInternal() {
        droneB.update();
        telemetryPro.addData("Drone Angle", droneB.getAngle());
        telemetryPro.addData("AnglePosition", droneB.getPosition());
        telemetryPro.addData("Get Firing Position", droneB.getFiringPosition());
        telemetryPro.addData("Hold Position", droneB.getHoldPos());
    }
}
