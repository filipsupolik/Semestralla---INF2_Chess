package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

/**
 * The Knight class represents a knight chess piece.
 * It extends the Piece class and implements the specific movement logic for knights.
 */
public class Knight extends Piece {

    private String nazov;
    /**
     * Constructs Knight with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imagePath relative path to image representing Knight of specific color
     * @param x x-coordinate of Knight (in array)
     * @param y y-coordinate of Knight (in array)
     */
    public Knight(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
        this.nazov = "Knight";
    }
    /**
     * Returns result of logical operation whether knight moves along diagonal
     * @param fromFrame instance of class BoardFrame from the knight moves
     * @param toFrame instance of class BoardFrame where the knight moves
     * @return true if knight moves correct and false if not
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

        if (!(Math.abs(frameFromX - frameToX) == 2 && Math.abs(frameFromY - frameToY) == 1 || Math.abs(frameFromX - frameToX) == 1 && Math.abs(frameFromY - frameToY) == 2)) {
            System.out.println("Pohyb nie je v tvare L");
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
        return false;
    }

}
