package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

public class Rook extends Piece {

    private String nazov;
    public Rook(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "Rook";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (notValidChessBoardBorderss(frameFromX,frameFromY,frameToX, frameToY)) return false;

        if (!(frameFromX == frameToX && frameFromY != frameToY || frameFromX != frameToX && frameFromY == frameToY)) {
            System.out.println("pohyb nie je v riadku alebo stlpci");
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

    @Override
    public String toString() {
        String vypis = "";
        if (this.getPlayerType().equals(PlayerType.BLACK)) {
            vypis = "Cierna veza";
        } else {
            vypis = "Biela verza";
        }
        return vypis;
    }
}
