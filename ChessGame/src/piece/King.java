package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import javax.swing.*;
import java.util.List;

public class King extends Piece {

    private String nazov;

    public King(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "King";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) return false;

        if (!(Math.abs(frameFromX - frameToX) == 1 || Math.abs(frameFromY - frameToY) == 1)) {
            System.out.println("Len jedno moze ist");
            return false;
        }

        this.checkColor(fromFrame, toFrame);

        return true;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }

    @Override
    public boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        int deltaX = toFrame.getX() - fromFrame.getX();
        int deltaY = toFrame.getY() - fromFrame.getY();
        if (deltaX > 0 && deltaY < 0) {
            return this.upRightMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        if (deltaX < 0 && deltaY < 0) {
            return this.upLeftMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        if (deltaX > 0 && deltaY > 0) {
            return this.downRightMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        if (deltaX < 0 && deltaY > 0) {
            return this.downLeftMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        return this.upperMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.downMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.rightMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.leftMovementIntersection(chessBoard, fromFrame, toFrame);
    }
}
