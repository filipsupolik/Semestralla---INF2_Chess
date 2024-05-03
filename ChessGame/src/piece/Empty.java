package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class Empty extends Piece {

    private String nazov;
    public Empty(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "Empty";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        return false;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }
}
