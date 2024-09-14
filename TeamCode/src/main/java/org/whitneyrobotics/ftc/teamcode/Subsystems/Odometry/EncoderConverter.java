package org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class EncoderConverter {
    private Encoder encoder;
    private Double wheelDiameter, ticksPerRev;
    private DistanceUnit unit = DistanceUnit.MM;
    private boolean isREVEncoder = false;
    private EncoderConverter(DcMotorEx encoderMotor, double wheelDiameter, double ticksPerRev, DistanceUnit unit, boolean isREVThroughBoreEncoder){
        encoder = new Encoder(encoderMotor);
        isREVEncoder = isREVThroughBoreEncoder;
        this.wheelDiameter = wheelDiameter;
        this.ticksPerRev = ticksPerRev;
        this.unit = unit;
    }

    public double getCurrentPosition(){
        return this.getCurrentRawPosition()*((wheelDiameter*Math.PI)/(ticksPerRev));
    }

    public double getTangentialVelocity(){
        return wheelDiameter/2 * getCurrentVelocity(AngleUnit.RADIANS);
    }

    public double getCurrentRawPosition(){
        return encoder.getCurrentPosition();
    }

    public double getCurrentVelocity(){
        return isREVEncoder ? encoder.getCorrectedVelocity() : encoder.getRawVelocity();
    }

    public double getCurrentVelocity(AngleUnit angleUnit){
        return encoder.getMotor().getVelocity(angleUnit);
    }

    public void setDirection(Encoder.Direction d){
        this.encoder.setDirection(d);
    }

    public void resetEncoder(){
        this.encoder.resetEncoder();
    }

    public static class EncoderConverterBuilder {
        private Double wheelDiameter, ticksPerRev;
        private DistanceUnit unit = DistanceUnit.MM;
        private boolean isRevEncoder = false;
        private DcMotorEx encoderMotor;

        public EncoderConverterBuilder setEncoderMotor(DcMotorEx motor){
            this.encoderMotor = motor;
            return this;
        }
        public EncoderConverterBuilder setUnit(DistanceUnit unit){
            this.unit = unit;
            return this;
        }
        public EncoderConverterBuilder setWheelDiameter(double diameter){
            this.wheelDiameter = diameter;
            return this;
        }
        public EncoderConverterBuilder setTicksPerRev(double ticksPerRev){
            this.ticksPerRev = ticksPerRev;
            return this;
        }
        public EncoderConverterBuilder setRevEncoder(boolean isRevEncoder){
            this.isRevEncoder = isRevEncoder;
            return this;
        }
        public EncoderConverter build(){
            if(encoderMotor == null){
                throw new IllegalArgumentException("Motor with paired encoder must be supplied.");
            }
            if(wheelDiameter == null || ticksPerRev == null){
                throw new IllegalArgumentException("No wheel diameter or ticks per revolution supplied");
            }
            return new EncoderConverter(encoderMotor, wheelDiameter, ticksPerRev, unit, isRevEncoder);
        }
    }
}
