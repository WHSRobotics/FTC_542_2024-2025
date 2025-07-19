package org.whitneyrobotics.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class LConstants {
    static {
        OTOSConstants.angleUnit = AngleUnit.RADIANS;
        OTOSConstants.offset = new SparkFunOTOS.Pose2D(-0.5, 0, 0);
        OTOSConstants.angularScalar = 0.9914624;
        OTOSConstants.linearScalar = 0.9614384;
        OTOSConstants.linearUnit = DistanceUnit.INCH;
        OTOSConstants.hardwareMapName = "sensor_otos";
        OTOSConstants.useCorrectedOTOSClass = false;
    }
}




