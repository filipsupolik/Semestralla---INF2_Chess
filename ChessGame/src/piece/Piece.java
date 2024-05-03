package piece;

import enums.PlayerType;
import fri.shapesge.Image;
import hlavnybalicek.BoardFrame;

public abstract class Piece {
    private int x;
    private int y;
    private Image imageOfPiece;
    private final PlayerType playerType;

    public Piece(PlayerType playerType, String imagePath, int x,int y) {
        this.x = x;
        this.y = y;
        this.imageOfPiece = new Image(imagePath, x*100, y*100);
        this.playerType = playerType;
    }

    public Image getImageOfPiece() {
        return imageOfPiece;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public void makeVisible() {
        this.imageOfPiece.makeVisible();
    }

    public void notVisible() {
        this.imageOfPiece.makeInvisible();
    }

    public abstract boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame);

    public void move(BoardFrame fromFrame, BoardFrame toFrame) {

        if (!isValidMove(fromFrame, toFrame)) {
            System.out.println("nie je validny pohyb");
            return;
        }

        int x = toFrame.getX();
        int y = toFrame.getY();

        this.setX(x);
        this.setY(y);

        fromFrame.setPiece(new Empty(PlayerType.BLACK, "piece/b-bishop.png", fromFrame.getX(), fromFrame.getY()));
        toFrame.setPiece(this);
        toFrame.getPiece().getImageOfPiece().changePosition(x*100, y*100);
        toFrame.getPiece().makeVisible();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public abstract void popis();
}