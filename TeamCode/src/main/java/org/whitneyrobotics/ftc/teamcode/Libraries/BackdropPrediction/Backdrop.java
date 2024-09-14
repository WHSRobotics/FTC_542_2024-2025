package org.whitneyrobotics.ftc.teamcode.Libraries.BackdropPrediction;

public class Backdrop {
    private static int backdropNumRows = 8;
    public enum CELL {
        BLANK,
        WHITE,
        YELLOW,
        GREEN,
        PURPLE;

        private boolean calculationConsidered = false;
        private double[][] backdropPos;

        public void consider(){
            calculationConsidered = true;
        }
        public void completeCalc(){
            calculationConsidered = false;
        }

        public void setBackdropPos(double[][] backdropPos) {
            this.backdropPos = backdropPos;
        }

        public CELL fill(){
            int chosen = (int) (Math.floor(Math.random() * (CELL.values().length - 1))) + 1;

            CELL returnableCell = CELL.values()[chosen];
            returnableCell.setBackdropPos(this.backdropPos);

            return returnableCell;
        }

        public CELL clear(){
            CELL returnableCell = CELL.BLANK;
            returnableCell.setBackdropPos(this.backdropPos);

            return returnableCell;
        }
    }
    
    public CELL[][] BackdropContents = new CELL[backdropNumRows][];
}