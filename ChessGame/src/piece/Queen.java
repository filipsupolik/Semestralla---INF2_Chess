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
        boolean isIntersected = false;
        int deltaX = toFrame.getX() - fromFrame.getX();
        int deltaY = toFrame.getY() - fromFrame.getY();
        //pohyb hore
        isIntersected = this.upperMovementIntersection(chessBoard, fromFrame, toFrame, isIntersected);
        //pohyb dole
        isIntersected = this.downMovementIntersection(chessBoard, fromFrame, toFrame, isIntersected);
        //pohyb vpravo
        isIntersected = this.rightMovementIntersection(chessBoard, fromFrame, toFrame, isIntersected);
        //pohyb vlavo
        isIntersected = this.leftMovementIntersection(chessBoard, fromFrame, toFrame, isIntersected);
        //pohyb diagonalne
        //hore-vpravo
        isIntersected = this.upRightMovementIntersection(chessBoard, fromFrame, deltaX, deltaY, isIntersected);
        //hore-vlavo
        isIntersected = this.upLeftMovementIntersection(chessBoard, fromFrame, deltaX, deltaY, isIntersected);
        //dole - vpravo
        isIntersected = this.downRightMovementIntersection(chessBoard, fromFrame, deltaX, deltaY, isIntersected);
        //dole - vlavo
        isIntersected = this.downLeftIntersection(chessBoard, fromFrame, deltaX, deltaY, isIntersected);
        return isIntersected;
    }
}
