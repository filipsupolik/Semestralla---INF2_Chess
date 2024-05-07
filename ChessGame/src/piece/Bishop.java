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

    //AI generated code
    @Override
    public boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;

        int deltaX = toFrame.getX() - fromFrame.getX();
        int deltaY = toFrame.getY() - fromFrame.getY();

// Check if the movement is diagonal
        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            // Movement direction: up-right
            if (deltaX > 0 && deltaY < 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (!chessBoard.get(fromFrame.getY() - i).get(fromFrame.getX() + i).isEmpty()) {
                        isIntersected = true;
                        break;
                    }
                }
            }
            // Movement direction: up-left
            else if (deltaX < 0 && deltaY < 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (!chessBoard.get(fromFrame.getY() - i).get(fromFrame.getX() - i).isEmpty()) {
                        isIntersected = true;
                        break;
                    }
                }
            }
            // Movement direction: down-right
            else if (deltaX > 0 && deltaY > 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (!chessBoard.get(fromFrame.getY() + i).get(fromFrame.getX() + i).isEmpty()) {
                        isIntersected = true;
                        break;
                    }
                }
            }
            // Movement direction: down-left
            else if (deltaX < 0 && deltaY > 0) {
                for (int i = 1; i < Math.abs(deltaX); i++) {
                    if (!chessBoard.get(fromFrame.getY() + i).get(fromFrame.getX() - i).isEmpty()) {
                        isIntersected = true;
                        break;
                    }
                }
            }
        }

        return isIntersected;

    }
}
