package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

/**
 * The Rook class represents a Rook chess piece.
 * It extends the Piece class and implements the specific movement logic for Rook.
 */
public class Rook extends Piece {

    private String nazov;
    /**
     * Constructs Rook with specific player type, image path and coordinates x and y
     * @param playerType player type (WHITE or BLACK)
     * @param imagePath relative path to image representing Rook of specific color
     * @param x x-coordinate of Rook (in array)
     * @param y y-coordinate of Rook (in array)
     */
    public Rook(PlayerType playerType, String imagePath, int x, int y) {
        super(playerType, imagePath, x, y);
        this.nazov = "Rook";
    }

    /**
     * Returns result of logical operation whether Rook moves along diagonal
     * @param fromFrame instance of class BoardFrame from the Rook moves
     * @param toFrame instance of class BoardFrame where the Rook moves
     * @return true if Rook moves correct and false if not
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

        if (!(frameFromX == frameToX && frameFromY != frameToY || frameFromX != frameToX && frameFromY == frameToY)) {
            System.out.println("pohyb nie je v riadku alebo stlpci");
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
        return this.getMovementOfPiece().upMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.getMovementOfPiece().downMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.getMovementOfPiece().rightMovementIntersection(chessBoard, fromFrame, toFrame) ||
                this.getMovementOfPiece().leftMovementIntersection(chessBoard, fromFrame, toFrame);
    }

    /**
     * Returns string representation of Rook instance
     * @return color of rook
     */
    @Override
    public String toString() {
        String vypis = "";
        if (this.getPlayerType().equals(PlayerType.BLACK)) {
            vypis = "Cierna veza";
        } else {
            vypis = "Biela verza";
        }
        return vypis;
    }
}
