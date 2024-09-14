package org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.whitneyrobotics.ftc.teamcode.Libraries.Geometry.LinAlg.Vector;
import org.whitneyrobotics.ftc.teamcode.Libraries.Geometry.Coordinate;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HWheelOdometry implements Odometry {

    private EncoderConverter leftTracker, rightTracker, lateralTracker;

    private double lastLeftReading, lastRightReading, lastLateralReading = 0;
    private Coordinate currentCoord = new Coordinate(0,0,0);

    public final double trackWidth;
    private double lateralOffset;

    public HWheelOdometry(EncoderConverter leftTrackerPort, EncoderConverter rightTrackerPort, EncoderConverter lateralTrackerPort, double trackWidth, double lateralOffset){
        leftTracker = leftTrackerPort;
        rightTracker = rightTrackerPort;
        leftTracker.setDirection(Encoder.Direction.REVERSE);
        lateralTracker = lateralTrackerPort;
        this.trackWidth = trackWidth;
        this.lateralOffset = lateralOffset;
    }

    @Override
    public void setInitialPose(Coordinate coordinate){
        this.currentCoord = coordinate;
    }

    public void resetEncoders(){
        leftTracker.resetEncoder();
        rightTracker.resetEncoder();
        lateralTracker.resetEncoder();
    }

    @Override
    public void update() {
        double currentLeft = leftTracker.getCurrentPosition();
        double currentRight = rightTracker.getCurrentPosition();
        double currentLateral = lateralTracker.getCurrentPosition();

        double deltaL = currentLeft - lastLeftReading;
        double deltaR = currentRight - lastRightReading;
        double deltaLateral = currentLateral - lastLateralReading;

        double deltaTheta =  (deltaL-deltaR)/trackWidth;
        double newTheta = currentCoord.getHeading() + deltaTheta;

        //local translation vector
        double localOffsetX, localOffsetY;
        if(deltaTheta == 0){
            localOffsetX = deltaLateral;
            localOffsetY = deltaR;
        } else {
            double chordLength = 2*Math.sin(deltaTheta/2);
            localOffsetX = chordLength*((deltaLateral/deltaTheta)+lateralOffset);
            localOffsetY = chordLength*((deltaR/deltaTheta)+(trackWidth/2));
        }
        this.currentCoord.setHeading(newTheta);
         Vector rotatedOffset = new Vector(localOffsetX,localOffsetY).rotate(currentCoord.getHeading());
        this.currentCoord.setX(this.currentCoord.getX() + rotatedOffset.get(0,0));
        this.currentCoord.setY(this.currentCoord.getY() + rotatedOffset.get(1,0));
        lastLeftReading = currentLeft;
        lastRightReading = currentRight;
        lastLateralReading = currentLateral;
    }

    public Coordinate getCurrentPosition(){return Coordinate.copy(currentCoord);}
}
