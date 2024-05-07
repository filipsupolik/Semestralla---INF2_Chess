package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

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

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) return false;

        return true;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }

    @Override
    public boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        //pohyb hore
        if (toFrame.getY() < fromFrame.getY()) {
            for (int i = fromFrame.getY(); i > toFrame.getY(); i--) {
                if (!chessBoard.get(i - 1).get(this.getX()).isEmpty()) {
                    isIntersected = true;
                }
            }
        }
        //pohyb dole
        if (toFrame.getY() > fromFrame.getY()) {
            for (int i = fromFrame.getY(); i < toFrame.getY(); i++) {
                if (!chessBoard.get(i + 1).get(this.getX()).isEmpty()) {
                    isIntersected = true;
                }
            }
        }
        //pohyb vpravo
        if (toFrame.getX() > fromFrame.getX()) {
            for (int i = fromFrame.getX(); i < toFrame.getX(); i++) {
                if (!chessBoard.get(this.getY()).get(i  + 1).isEmpty()) {
                    isIntersected = true;
                }
            }
        }
        //pohyb vlavo
        if (toFrame.getX() < fromFrame.getX()) {
            for (int i = fromFrame.getX(); i > toFrame.getX(); i--) {
                if (!chessBoard.get(this.getY()).get(i -1).isEmpty()) {
                    isIntersected = true;
                }
            }
        }
        return isIntersected;
    }
}
