package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class Rook extends Piece {

    public Rook(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);

    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();


        if (validChessBoardBorders(frameFromX, frameFromY, frameToX, frameToY)) {
            if (!(frameFromX == frameToX && frameFromY != frameToY || frameFromX != frameToX && frameFromY == frameToY)) {
                System.out.println("pohyb nie je v riadku alebo stlpci");
                return false;
            }
        }
        return true;
    }
}
