package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class King extends Piece {

    public King(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        return true;
    }
}
