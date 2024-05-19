package movement;

import hlavnybalicek.BoardFrame;
import piece.Piece;

import java.util.List;

public class MovementOfPiece {
    private final Piece piece;

    public MovementOfPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Check if there is piece between two frames in left direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param toFrame    frame where checking ends
     * @return true if intersection and false if not
     */
    public boolean leftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getX() < fromFrame.getX()) {
            for (int i = fromFrame.getX(); i > toFrame.getX(); i--) {
                if (!chessBoard.get(piece.getY()).get(i - 1).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in right direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param toFrame    frame where checking ends
     * @return true if intersection and false if not
     */
    public boolean rightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getX() > fromFrame.getX()) {
            for (int i = fromFrame.getX(); i < toFrame.getX(); i++) {
                if (!chessBoard.get(piece.getY()).get(i + 1).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in down direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param toFrame    frame where checking ends
     * @return true if intersection and false if not
     */
    public boolean downMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getY() > fromFrame.getY()) {
            for (int i = fromFrame.getY(); i < toFrame.getY(); i++) {
                if (!chessBoard.get(i + 1).get(piece.getX()).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in up direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param toFrame    frame where checking ends
     * @return true if intersection and false if not
     */
    public boolean upMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getY() < fromFrame.getY()) {
            for (int i = fromFrame.getY(); i > toFrame.getY(); i--) {
                if (!chessBoard.get(i - 1).get(piece.getX()).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in diagonal down left direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param deltaX     difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    public boolean downLeftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
        for (int i = 1; i < Math.abs(deltaX); i++) {
            if (!chessBoard.get(fromFrame.getY() + i).get(fromFrame.getX() - i).isEmpty()) {
                isIntersected = true;
                break;
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in diagonal down right direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param deltaX     difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    public boolean downRightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
        for (int i = 1; i < Math.abs(deltaX); i++) {
            if (!chessBoard.get(fromFrame.getY() + i).get(fromFrame.getX() + i).isEmpty()) {
                isIntersected = true;
                break;
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in diagonal up left direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param deltaX     difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    public boolean upLeftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
        for (int i = 1; i < Math.abs(deltaX); i++) {
            if (!chessBoard.get(fromFrame.getY() - i).get(fromFrame.getX() - i).isEmpty()) {
                isIntersected = true;
                break;
            }
        }
        return isIntersected;
    }

    /**
     * Check if there is piece between two frames in diagonal up right direction
     *
     * @param chessBoard converted 2D array as List
     * @param fromFrame  frame where checking starts
     * @param deltaX     difference between frames in column
     * @return true if intersection and false if not
     */
    //cast kodu generovana AI
    public boolean upRightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
        for (int i = 1; i < Math.abs(deltaX); i++) {
            if (!chessBoard.get(fromFrame.getY() - i).get(fromFrame.getX() + i).isEmpty()) {
                isIntersected = true;
                break;
            }
        }
        return isIntersected;
    }
}