package piece;

import enums.PlayerType;
import fri.shapesge.Image;
import hlavnybalicek.BoardFrame;
import movement.MovementOfPiece;

import java.util.List;

/**
 * The abstract class Piece represents a general chess piece.
 * Each specific piece (e.g., Pawn, Bishop) will inherit from this class and implement its abstract methods.
 */
public abstract class Piece {
    private MovementOfPiece movementOfPiece;
    private int x;
    private int y;
    private final Image imageOfPiece;
    private final PlayerType playerType;

    public MovementOfPiece getMovementOfPiece() {
        return this.movementOfPiece;
    }

    /**
     * Constructs a Piece with the specified player type, image path, and coordinates.
     *
     * @param playerType the player type (WHITE, BLACK, or EMPTY)
     * @param imagePath the path to the image representing the piece
     * @param x the x-coordinate of the piece on the chess board
     * @param y the y-coordinate of the piece on the chess board
     */
    public Piece(PlayerType playerType, String imagePath, int x, int y) {
        this.x = x;
        this.y = y;
        this.imageOfPiece = new Image(imagePath, x * 100, y * 100);
        this.playerType = playerType;
        this.movementOfPiece = new MovementOfPiece(this);
    }
    /**
     * Gets the image of the piece.
     *
     * @return the image representing the piece
     */
    public Image getImageOfPiece() {
        return this.imageOfPiece;
    }

    /**
     * Gets the player type of the piece.
     *
     * @return the player type (WHITE, BLACK, or EMPTY)
     */
    public PlayerType getPlayerType() {
        return this.playerType;
    }
    /**
     * Makes the piece visible on the board.
     */
    public void makeVisible() {
        this.imageOfPiece.makeVisible();
    }
    /**
     * Makes the piece invisible on the board.
     */
    public void notVisible() {
        this.imageOfPiece.makeInvisible();
    }
    /**
     * Determines if the move from the source frame to the destination frame is valid.
     *
     * @param fromFrame the source BoardFrame
     * @param toFrame the destination BoardFrame
     * @return true if the move is valid, false otherwise
     */
    public abstract boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame);
    /**
     * Moves the piece from the source frame to the destination frame if the move is valid.
     *
     * @param fromFrame the source BoardFrame
     * @param toFrame the destination BoardFrame
     */
    public void move(BoardFrame fromFrame, BoardFrame toFrame) {

        if (!this.isValidMove(fromFrame, toFrame)) {
            System.out.println("nie je validny pohyb");
            return;
        }

        int x1 = toFrame.getX();
        int y1 = toFrame.getY();

        this.setX(x1);
        this.setY(y1);

        fromFrame.setPiece(new Empty(PlayerType.BLACK, "piece/b-bishop.png", fromFrame.getX(), fromFrame.getY()));
        toFrame.setPiece(this);
        toFrame.getPiece().getImageOfPiece().changePosition(x1 * 100, y1 * 100);
        toFrame.getPiece().makeVisible();
    }
    /**
     * Checks if the move is to a frame with a piece of the same color.
     *
     * @param fromFrame the source BoardFrame
     * @param toFrame the destination BoardFrame
     * @return true if the move is to a frame with a piece of the opposite color or an empty frame, false otherwise
     */
    public boolean checkColor(BoardFrame fromFrame, BoardFrame toFrame) {
        boolean oppositeColor = true;
        if (!toFrame.isEmpty()) {
            if (fromFrame.getPiece().getPlayerType().equals(toFrame.getPiece().getPlayerType())) {
                System.out.println("Rovnaka farba nemoze sa pohnut");
                oppositeColor = false;
            }
        }
        return oppositeColor;
    }
    /**
     * Checks if the coordinates are within the chess board borders.
     *
     * @param frameFromX the x-coordinate of the source frame
     * @param frameFromY the y-coordinate of the source frame
     * @param frameToX the x-coordinate of the destination frame
     * @param frameToY the y-coordinate of the destination frame
     * @return true if the coordinates are outside the chess board borders, false otherwise
     */
    protected boolean notValidChessBoardBorderss(int frameFromX, int frameFromY, int frameToX, int frameToY) {
        if (!(frameFromX >= 0 && frameFromX <= 7 && frameFromY >= 0 && frameFromY <= 7)) {
            System.out.println("Mimo hranice Z");
            return true;
        }

        if (!(frameToX >= 0 && frameToX <= 7 && frameToY >= 0 && frameToY <= 7)) {
            System.out.println("mimo hranice Do");
            return true;
        }
        return false;
    }

    /**
     * Check if there is piece between two frames in left direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param toFrame frame where checking ends
     * @return true if intersection and false if not
     */
    protected boolean leftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        return movementOfPiece.leftMovementIntersection(chessBoard, fromFrame, toFrame);
    }
    /**
     * Check if there is piece between two frames in right direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param toFrame frame where checking ends
     * @return true if intersection and false if not
     */
    protected boolean rightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        return movementOfPiece.rightMovementIntersection(chessBoard, fromFrame, toFrame);
    }
    /**
     * Check if there is piece between two frames in down direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param toFrame frame where checking ends
     * @return true if intersection and false if not
     */
    protected boolean downMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        return movementOfPiece.downMovementIntersection(chessBoard, fromFrame, toFrame);
    }
    /**
     * Check if there is piece between two frames in up direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param toFrame frame where checking ends
     * @return true if intersection and false if not
     */
    protected boolean upMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        return movementOfPiece.upMovementIntersection(chessBoard, fromFrame, toFrame);
    }
    /**
     * Check if there is piece between two frames in diagonal down left direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param deltaX difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    protected boolean downLeftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        return movementOfPiece.downLeftMovementIntersection(chessBoard, fromFrame, deltaX);
    }
    /**
     * Check if there is piece between two frames in diagonal down right direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param deltaX difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    protected boolean downRightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        return movementOfPiece.downRightMovementIntersection(chessBoard, fromFrame, deltaX);
    }
    /**
     * Check if there is piece between two frames in diagonal up left direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param deltaX difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    protected boolean upLeftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        return movementOfPiece.upLeftMovementIntersection(chessBoard, fromFrame, deltaX);
    }
    /**
     * Check if there is piece between two frames in diagonal up right direction
     * @param chessBoard converted 2D array as List
     * @param fromFrame frame where checking starts
     * @param deltaX difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    protected boolean upRightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        return movementOfPiece.upRightMovementIntersection(chessBoard, fromFrame, deltaX);
    }

    /**
     * Gets the x-coordinate of the piece.
     *
     * @return the x-coordinate of the piece
     */
    public int getX() {
        return this.x;
    }
    /**
     * Sets the x-coordinate of the piece.
     *
     * @param x the new x-coordinate of the piece
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Gets the y-coordinate of the piece.
     *
     * @return the y-coordinate of the piece
     */
    public int getY() {
        return this.y;
    }
    /**
     * Sets the y-coordinate of the piece.
     *
     * @param y the new y-coordinate of the piece
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Checks if another piece is in the position of the move.
     *
     * @param chessBoard the chess board as a 2D list of BoardFrames
     * @param fromFrame the source BoardFrame
     * @param toFrame the destination BoardFrame
     * @return true if another piece is in the position of the move, false otherwise
     */
    public abstract boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame);
    /**
     * Removes the piece from the specified frame by making it invisible.
     *
     * @param toFrame the BoardFrame from which the piece is removed
     */
    public void vymaz(BoardFrame toFrame) {
        toFrame.getPiece().notVisible();
    }
}