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

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) {
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
        int deltaX = toFrame.getX() - fromFrame.getX();
        int deltaY = toFrame.getY() - fromFrame.getY();
        //pohyb hore
        return this.upperMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.downMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.rightMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.leftMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.upRightMovementIntersection(chessBoard, fromFrame, deltaX, deltaY) ||
                this.upLeftMovementIntersection(chessBoard, fromFrame, deltaX, deltaY) ||
                this.downRightMovementIntersection(chessBoard, fromFrame, deltaX, deltaY) ||
                this.downLeftMovementIntersection(chessBoard, fromFrame, deltaX, deltaY);
    }
}
