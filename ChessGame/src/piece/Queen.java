package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class Queen extends Piece {

    private String nazov;
    public Queen(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "Queen";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (!(frameFromX >= 0 && frameFromX <= 7 && frameFromY >= 0 && frameFromY <= 7)) {
            System.out.println("Mimo hranice Z");
            return false;
        }

        if (!(frameToX >= 0 && frameToX <= 7 && frameToY >= 0 && frameToY <= 7)) {
            System.out.println("mimo hranice Do");
            return false;
        }

        if (!(Math.abs(frameFromX - frameToX) == Math.abs(frameFromY - frameToY)) && !(frameFromX == frameToX && frameFromY != frameToY || frameFromX != frameToX && frameFromY == frameToY)) {
            System.out.println("pohyb nie je diagonalne alebo riadku a stlpci");
            return false;
        }

        return true;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }
}
