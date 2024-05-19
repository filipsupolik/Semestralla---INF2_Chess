package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;
/**
 * The Pawn class represents a Pawn chess piece.
 * It extends the Piece class and implements the specific movement logic for Pawns.
 */
public class Pawn extends Piece {

    private final boolean firstMove;
    private final String nazov;
    /**
     * Constructs Pawn with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imagePath relative path to image representing Pawn of specific color
     * @param x x-coordinate of Pawn (in array)
     * @param y y-coordinate of Pawn (in array)
     */
    public Pawn(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
        this.firstMove = true;
        this.nazov = "Pawn";
    }
    /**
     * Returns result of logical operation whether Pawn moves along diagonal
     * @param fromFrame instance of class BoardFrame from the Pawn moves
     * @param toFrame instance of class BoardFrame where the Pawn moves
     * @return true if Pawn moves correct and false if not
     */
    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();
        int deltaX = frameToX - frameFromX;
        int deltaY = frameToY - frameFromY;

        if (this.notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) {
            return false;
        }

        if (Math.abs(frameFromY - frameToY) > 2) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.BLACK) && Math.abs(frameFromY - frameToY) > 1 && !(frameFromY == 1)) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.WHITE) && Math.abs(frameFromY - frameToY) > 1 && !(frameFromY == 6)) {
            return false;
        }

        if (deltaX == -1 && deltaY == 1 && toFrame.isEmpty()) {
            return false;
        }

        if (deltaX == 1 && deltaY == 1 && toFrame.isEmpty()) {
            return false;
        }
        if (deltaX == -1 && deltaY == -1 && toFrame.isEmpty()) {
            return false;
        }

        if (deltaX == 1 && deltaY == -1 && toFrame.isEmpty()) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.BLACK) && frameFromY > frameToY) {
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.WHITE) && frameToY > frameFromY) {
            return false;
        }

        return Math.abs(frameFromX - frameToX) <= 0 || !(toFrame.getPiece() instanceof Empty);
    }

    /**
     * Check piece intersection with other pieces in his direction of movement
     * @param chessBoard converted 2D array to list for better working with it
     * @param fromFrame frame from check intersection with other pieces
     * @param toFrame frame to check intersection with other pieces
     * @return true or false depending on if there is intersection
     */
    @Override
    public boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        return this.getMovementOfPiece().upMovementIntersection(chessBoard, fromFrame, toFrame) || this.getMovementOfPiece().downMovementIntersection(chessBoard, fromFrame, toFrame);
    }
}
