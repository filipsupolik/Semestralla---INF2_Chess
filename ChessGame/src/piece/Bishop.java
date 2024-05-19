package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

/**
 * The Bishop class represents a bishop chess piece.
 * It extends the Piece class and implements the specific movement logic for bishops.
 */
public class Bishop extends Piece {

    private String nazov;

    /**
     * Constructs Bishop with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imageSource relative path to image representing Bishop of specific color
     * @param x x-coordinate of Bishop (in array)
     * @param y y-coordinate of Bishop (in array)
     */
    public Bishop(PlayerType playerType, String imageSource, int x, int y) {
        super(playerType, imageSource, x, y);
        this.nazov = "Bishop";
    }

    /**
     * Returns result of logical operation whether bishop moves along diagonal
     * @param fromFrame instance of class BoardFrame from the bishop moves
     * @param toFrame instance of class BoardFrame where the bishop moves
     * @return true if Bishop moves correct and false if not
     */
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

    /**
     * Check piece intersection with other pieces in his direction of movement
     * @param chessBoard converted 2D array to list for better working with it
     * @param fromFrame frame from check intersection with other pieces
     * @param toFrame frame to check intersection with other pieces
     * @return true or false depending on if there is intersection
     */
    //AI generated code
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
        return false;
    }
}
