package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;


public class Knight extends Piece {

    private String nazov;

    public Knight(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
        this.nazov = "Knight";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) return false;

        if (!(Math.abs(frameFromX - frameToX) == 2 && Math.abs(frameFromY - frameToY) == 1 || Math.abs(frameFromX - frameToX) == 1 && Math.abs(frameFromY - frameToY) == 2)) {
            System.out.println("Pohyb nie je v tvare L");
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
