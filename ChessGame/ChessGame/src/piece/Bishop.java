package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class Bishop extends Piece {
    public Bishop(PlayerType playerType, String imageSource, int x, int y) {
        super(playerType, imageSource, x, y);
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        return true;
    }
}
