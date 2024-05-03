package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class Queen extends Piece {

    public Queen(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        return true;
    }

}
