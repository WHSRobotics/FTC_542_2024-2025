package org.whitneyrobotics.ftc.teamcode.Constants;

public enum Alliance {
    RED(1, Math.PI/2),BLUE(-1, -Math.PI/2);
    public final int allianceCoefficient;
    public final double headingAngle;
    Alliance(int allianceCoefficient, double headingAngle){
        this.allianceCoefficient = allianceCoefficient;
        this.headingAngle = headingAngle;
    }
}
