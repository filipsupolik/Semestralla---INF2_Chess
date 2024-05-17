package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove;
    private String nazov;

    public Pawn(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
        this.firstMove = true;
        this.nazov = "Pawn";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();
        int deltaX = frameToX - frameFromX;
        int deltaY = frameToY - frameFromY;

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) return false;

        if (Math.abs(frameFromY - frameToY) > 2) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.BLACK) && Math.abs(frameFromY- frameToY) > 1 && !(frameFromY == 1)) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.WHITE) && Math.abs(frameFromY- frameToY) > 1 && !(frameFromY == 6)) {
            return false;
        }

        if (deltaX == -1 && deltaY == 1 && toFrame.isEmpty()){
            return false;
        }

        if (deltaX == 1 && deltaY == 1 && toFrame.isEmpty()){
            return false;
        }

        if (deltaX == -1 && deltaY == -1 && toFrame.isEmpty()){
            return false;
        }

        if (deltaX == 1 && deltaY == -1 && toFrame.isEmpty()){
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.BLACK) && frameFromY > frameToY) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.WHITE) && frameToY > frameFromY) {
            return false;
        }

        if (Math.abs(frameFromX - frameToX) > 0 && toFrame.getPiece() instanceof Empty) {
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
        return this.upperMovementIntersection(chessBoard, fromFrame, toFrame) || this.downMovementIntersection(chessBoard, fromFrame, toFrame);
    }
}
