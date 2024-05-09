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

        if (this.notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) {
            return false;
        }

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
        // Movement direction: up-right
        isIntersected = this.upRightMovementIntersection(chessBoard, fromFrame, deltaX, deltaY);
        // Movement direction: up-left
        isIntersected = this.upLeftMovementIntersection(chessBoard, fromFrame, deltaX, deltaY);
        // Movement direction: down-right
        isIntersected = this.downRightMovementIntersection(chessBoard, fromFrame, deltaX, deltaY);
        // Movement direction: down-left
        isIntersected = this.downLeftMovementIntersection(chessBoard, fromFrame, deltaX, deltaY);
        return isIntersected;

    }
}
