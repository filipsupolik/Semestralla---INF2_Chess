package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

/**
 * The Empty class represents empty piece on chess board
 * It extends the Piece class and implements the specific movement logic for empty.
 */
public class Empty extends Piece {

    private String nazov;

    /**
     * Constructs Empty with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imagePath relative path to image representing empty of specific color (it can be any image)
     * @param x x-coordinate of Bishop (in array)
     * @param y y-coordinate of Bishop (in array)
     */
    public Empty(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "Empty";
    }

    /**
     * Returns result of logical operation whether empty moves along diagonal
     * @param fromFrame instance of class BoardFrame from the bishop moves
     * @param toFrame instance of class BoardFrame where the bishop moves
     * @return true if Bishop moves correct and false if not
     */
    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        return false;
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
        return false;
    }
}
