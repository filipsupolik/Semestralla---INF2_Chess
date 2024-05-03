package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class King extends Piece {

    private String nazov;

    public King(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "King";
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

        if (!(Math.abs(frameFromX - frameToX) == 1 || Math.abs(frameFromY - frameToY) == 1)) {
            System.out.println("Len jedno moze ist");
            return false;
        }

        if (toFrame.getPiece().getPlayerType().equals(fromFrame.getPiece().getPlayerType())) {
            System.out.println("Postavicka rovnakej farby");
            return false;
        }

        return true;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }
}
