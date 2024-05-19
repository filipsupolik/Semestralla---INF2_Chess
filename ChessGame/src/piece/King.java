package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

/**
 * The Bishop class represents a king chess piece.
 * It extends the Piece class and implements the specific movement logic for king.
 */
public class King extends Piece {

    private String nazov;
    /**
     * Constructs king with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imagePath relative path to image representing King of specific color
     * @param x x-coordinate of King (in array)
     * @param y y-coordinate of King (in array)
     */
    public King(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "King";
    }
    /**
     * Returns result of logical operation whether king moves one frame to all directions
     * @param fromFrame instance of class BoardFrame from the king moves
     * @param toFrame instance of class BoardFrame where the king moves
     * @return true if Bishop moves correct and false if not
     */
    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) {
            return false;
        }
        if (!(Math.abs(frameFromX - frameToX) == 1 || Math.abs(frameFromY - frameToY) == 1)) {
            System.out.println("Len jedno moze ist");
            return false;
        }

        this.checkColor(fromFrame, toFrame);

        return true;
    }

    /**
     * Check king intersection with other pieces in his direction of movement
     * @param chessBoard converted 2D array to list for better working with it
     * @param fromFrame first frame where to check intersection from
     * @param toFrame last frame where to check intersection to
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
