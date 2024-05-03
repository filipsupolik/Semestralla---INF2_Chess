package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;


public class Knight extends Piece {

    public Knight(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        return true;
    }


}
