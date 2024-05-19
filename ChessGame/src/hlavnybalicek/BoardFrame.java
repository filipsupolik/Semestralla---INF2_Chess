package hlavnybalicek;

import piece.Empty;
import piece.Piece;

/**
 * The BoardFrame class represents a single frame or cell on a game board.
 * It holds the coordinates (x, y) and a reference to a Piece.
 */
public class BoardFrame {

    /**
     * The y-coordinate of the frame on the board.
     */
    private int y;

    /**
     * The x-coordinate of the frame on the board.
     */
    private int x;

    /**
     * The piece currently occupying this frame. It can be null.
     */
    private Piece piece;

    /**
     * Constructs a BoardFrame with specified x and y coordinates.
     * Initially, the frame does not hold any piece.
     *
     * @param x the x-coordinate of the frame
     * @param y the y-coordinate of the frame
     */
    public BoardFrame(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    /**
     * Returns the piece currently occupying this frame.
     *
     * @return the piece currently in this frame, or null if the frame is empty
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Sets the piece to occupy this frame.
     *
     * @param piece the piece to place in this frame
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Returns the y-coordinate of this frame.
     *
     * @return the y-coordinate of this frame
     */
    public int getY() {
        return this.y;
    }

    /**
     * Returns the x-coordinate of this frame.
     *
     * @return the x-coordinate of this frame
     */
    public int getX() {
        return this.x;
    }

    /**
     * Checks if the frame is empty, which is defined as holding a piece of type Empty.
     *
     * @return true if the frame holds a piece of type Empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.piece instanceof Empty) {
            return true;
        }
        return false;
    }
}
