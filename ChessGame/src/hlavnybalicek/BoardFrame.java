package hlavnybalicek;

import piece.Empty;
import piece.Piece;

public class BoardFrame {

    private int y;
    private int x;
    private Piece piece;

    public BoardFrame(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isEmpty() {
        if (this.piece instanceof Empty) {
            return true;
        }
        return false;
    }
}