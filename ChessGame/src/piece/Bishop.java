package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

public class Bishop extends Piece {

    private String nazov;
    public Bishop(PlayerType playerType, String imageSource, int x, int y) {
        super(playerType, imageSource, x, y);
        this.nazov = "Bishop";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) return false;

        if (!(Math.abs(frameFromX - frameToX) == Math.abs(frameFromY - frameToY))) {
            System.out.println("pohyb nie je diagonalne");
            return false;
        }

        return true;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }

    @Override
    public boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        return false;
    }
}
