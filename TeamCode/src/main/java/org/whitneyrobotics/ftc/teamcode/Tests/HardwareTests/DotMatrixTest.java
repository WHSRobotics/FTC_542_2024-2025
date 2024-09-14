package org.whitneyrobotics.ftc.teamcode.Tests.HardwareTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Libraries.Hardware.MAX7219Matrix;

@TeleOp(name = "DotMatrixTest")
public class DotMatrixTest extends OpModeEx {

    MAX7219Matrix matrix;
    @Override
    public void initInternal() {
        matrix = new MAX7219Matrix(hardwareMap, "matrixMOSI", "matrixCLK", "matrixCS");
    }

    @Override
    public void loopInternal() {
        if (getRuntime() % 6 < 1) {
            matrix.setDisplay(MAX7219Matrix.MatrixNumerals.ONE.data, false);
        } else if (getRuntime() % 6 < 2) {
            matrix.setDisplay(MAX7219Matrix.MatrixNumerals.TWO.data, false);
        } else if (getRuntime() % 6 < 3) {
            matrix.setDisplay(MAX7219Matrix.MatrixNumerals.THREE.data, false);
        } else if (getRuntime() % 6 < 4) {
//            setDisplay(generateErrorStatus(69), false);
            matrix.setDisplay(MAX7219Matrix.MatrixNumerals.FOUR.data, false);
        } else if (getRuntime() % 6 < 5) {
            matrix.setDisplay(MAX7219Matrix.MatrixNumerals.FIVE.data, false);
        } else {
            matrix.setDisplay(MAX7219Matrix.MatrixNumerals.SMILE.data, false);
        }
    }
}
