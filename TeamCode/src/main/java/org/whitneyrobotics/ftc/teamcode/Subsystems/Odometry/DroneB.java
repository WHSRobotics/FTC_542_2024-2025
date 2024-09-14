package org.whitneyrobotics.ftc.teamcode.Subsystems.Odometry;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Libraries.Utilities.Functions;
import org.whitneyrobotics.ftc.teamcode.Subsystems.SubsystemIterative;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

@Config
public class DroneB implements SubsystemIterative {
    private Servo angle, fire, hold;
    private double angleNumeric = 0.0;
    private double position = 0.0d;
    private double holdPos = 0.72;

    private int i = 1;

    public static String newPair = "";

    public static double firingPosition = 0d;

    //Regression constants for angles
    //See https://www.desmos.com/calculator/ow5xt1unvc
    public static double A = 0.860005;
    public static double B = -0.00786413;
    public static double C = 0.0000596671;
    public static double D = -2.9912e-7;

    public void fire(){
        firingPosition = 1.00;
    }

    public void retract(){
        position = 0.62;
        firingPosition = 0.1;
        holdPos = 0.72;
        i = 1;
    }

    public void quickPrep(){
        holdPos = 0.61;
        position = 0.42;
    }

    public static double regress(double angle){
        angle = Functions.clamp(angle, 0, 90);
        return A + B*angle + C*Math.pow(angle, 2) + D*Math.pow(angle, 3);
    }

    public void setAngle(double ang){
        double pos = regress(ang);
        angleNumeric = ang;
        position = pos;
    }

    public void nextDefinedAngle(){
        double angle = (double)anglePositions.keySet().toArray()[i];
        Double pos = anglePositions.get(angle);
        if(pos == null) return;
        i = (i+1) % anglePositions.size();
        position = pos;
        angleNumeric = angle;
    }

    public void hold(){
        if (holdPos == 0.72) {
            holdPos = 0.61;
        } else if (holdPos == 0.61){
            holdPos = 0.72;
        }
    }

    SortedMap<Double, Double> anglePositions = new TreeMap<>();
    public DroneB(HardwareMap hardwareMap){
        angle = hardwareMap.get(Servo.class, "angle");
        fire = hardwareMap.get(Servo.class, "drone");
        hold = hardwareMap.get(Servo.class, "hold");
        reset();
    }

    @Override
    public void init() {
        position = 0.62;
        holdPos = 0.72;
        retract();
        update();
    }

    @Override
    public void update() {
        this.angle.setPosition(position);
        this.fire.setPosition(firingPosition);
        this.hold.setPosition(holdPos);
    }


    public double getAngle(){
        return angleNumeric;
    }

    public double getPosition(){
        return position;
    }

    public double getHoldPos(){
        return holdPos;
    }

    public double getFiringPosition(){
        return firingPosition;
    }

    /**
     * For testing only.
     */
    public void submitPosition(){
        submitPosition(()->{});
    }

    public void submitPosition(Runnable afterDone){
        String[] pairing = newPair.split(",");
        if(pairing.length != 2) return;
        pairing = Arrays.stream(pairing).map(String::trim).toArray(String[]::new);
        try{
            double angle = Double.parseDouble(pairing[0]);
            double position = Double.parseDouble(pairing[1]);
            anglePositions.put(angle, position);
            afterDone.run();
        } catch (NumberFormatException e){
        }
        newPair = "";
    }

    @Override
    public void reset() {
        anglePositions.clear();
        anglePositions.put(0.0, 0.62);
        anglePositions.put(45.0, 0.42);
        anglePositions.put(11.0, 0.56);
        anglePositions.put(20.0, 0.52);
        anglePositions.put(30.0,0.47);
        anglePositions.put(65.0, 0.38);
        anglePositions.put(51.0, 0.40);
    }
}
