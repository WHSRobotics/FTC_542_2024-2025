package org.whitneyrobotics.ftc.teamcode.Libraries.BackdropPrediction;

public class BackdropRow {
    private Backdrop.CELL[] cellsPresentInRow;

    public BackdropRow (double rowNum) {
        cellsPresentInRow = (rowNum % 2 == 0) ? new Backdrop.CELL[6] : new Backdrop.CELL[7];
    }

    public void clearRow () {
        for (int i = 0; i < cellsPresentInRow.length; i++){
            continue;
        }
    }
}
