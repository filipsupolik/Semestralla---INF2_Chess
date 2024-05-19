package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;
/**
 * The Queen class represents a Queen chess piece.
 * It extends the Piece class and implements the specific movement logic for Queen.
 */
public class Queen extends Piece {

    private String nazov;
    /**
     * Constructs Queen with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imagePath relative path to image representing Queen of specific color
     * @param x x-coordinate of Queen (in array)
     * @param y y-coordinate of Queen (in array)
     */
    public Queen(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "Queen";
    }
    /**
     * Returns result of logical operation whether Queen moves along diagonal
     * @param fromFrame instance of class BoardFrame from the bishop moves
     * @param toFrame instance of class BoardFrame where the bishop moves
     * @return true if Queen moves correct and false if not
     */
    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();
        int deltaX = frameToX - frameFromX;
        int deltaY = frameToY - frameFromY;

        boolean isStraightMove = (deltaX == 0 || deltaY == 0);
        boolean isDiagonalMove = Math.abs(deltaX) == Math.abs(deltaY);

        if (!isStraightMove && !isDiagonalMove) {
            return false;
        }

        return true;
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
        int deltaX = toFrame.getX() - fromFrame.getX();
        int deltaY = toFrame.getY() - fromFrame.getY();
        if (deltaX > 0 && deltaY < 0) {
            return this.getMovementOfPiece().upRightMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        if (deltaX < 0 && deltaY < 0) {
            return this.getMovementOfPiece().upLeftMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        if (deltaX > 0 && deltaY > 0) {
            return this.getMovementOfPiece().downRightMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        if (deltaX < 0 && deltaY > 0) {
            return this.getMovementOfPiece().downLeftMovementIntersection(chessBoard, fromFrame, deltaX);
        }
        return this.getMovementOfPiece().upMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.getMovementOfPiece().downMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.getMovementOfPiece().rightMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.getMovementOfPiece().leftMovementIntersection(chessBoard, fromFrame, toFrame);
    }
}
