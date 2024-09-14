package org.whitneyrobotics.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import com.qualcomm.robotcore.hardware.IMU;

public class WHSIMU {
        private double calibrationRadians = 0;

        public final IMU imu;

        public WHSIMU(HardwareMap hardwareMap){
            this(hardwareMap, "imu");
        }

        public WHSIMU(HardwareMap theMap, String name) {
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.LEFT
            ));


            imu = theMap.get(IMU.class, name);
            imu.initialize(parameters);
        }

        double heading;



        public double[] getYawPitchRoll()
        {
            YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
            double[] threeHeading = {angles.getYaw(AngleUnit.RADIANS),angles.getPitch(AngleUnit.RADIANS),angles.getRoll(AngleUnit.RADIANS)};
            return threeHeading; // -180 to 180 deg
        }

        public double getHeadingYaw(){
            heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - calibrationRadians;
            return heading;
        }

        public void zeroCalibrationOffset(){
            zeroCalibrationOffset(0.0d);
        }

        public void zeroCalibrationOffset(double offsetRadians){
            calibrationRadians = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).firstAngle - offsetRadians;
        }


        public AngularVelocity getAngularVelocity() {
            return imu.getRobotAngularVelocity(AngleUnit.RADIANS);
        }

        public void resetIMU(){
            imu.resetYaw();
        }
}
