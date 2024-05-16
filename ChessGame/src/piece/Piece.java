package piece;

import enums.PlayerType;
import fri.shapesge.Image;
import hlavnybalicek.BoardFrame;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private int x;
    private int y;
    private Image imageOfPiece;
    private final PlayerType playerType;
    private List<Piece> ereasedPieces;

    public Piece(PlayerType playerType, String imagePath, int x,int y) {
        this.x = x;
        this.y = y;
        this.imageOfPiece = new Image(imagePath, x*100, y*100);
        this.playerType = playerType;
        this.ereasedPieces = new ArrayList<>();
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
    protected boolean leftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getX() < fromFrame.getX()) {
            for (int i = fromFrame.getX(); i > toFrame.getX(); i--) {
                if (!chessBoard.get(this.getY()).get(i -1).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    protected boolean rightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getX() > fromFrame.getX()) {
            for (int i = fromFrame.getX(); i < toFrame.getX(); i++) {
                if (!chessBoard.get(this.getY()).get(i  + 1).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    protected boolean downMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getY() > fromFrame.getY()) {
            for (int i = fromFrame.getY(); i < toFrame.getY(); i++) {
                if (!chessBoard.get(i + 1).get(this.getX()).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    protected boolean upperMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (toFrame.getY() < fromFrame.getY()) {
            for (int i = fromFrame.getY(); i > toFrame.getY(); i--) {
                if (!chessBoard.get(i - 1).get(this.getX()).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        }
        return isIntersected;
    }

    protected boolean downLeftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
            for (int i = 1; i < Math.abs(deltaX); i++) {
                if (!chessBoard.get(fromFrame.getY() + i).get(fromFrame.getX() - i).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        return isIntersected;
    }

    protected boolean downRightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
            for (int i = 1; i < Math.abs(deltaX); i++) {
                if (!chessBoard.get(fromFrame.getY() + i).get(fromFrame.getX() + i).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        return isIntersected;
    }

    protected boolean upLeftMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
            for (int i = 1; i < Math.abs(deltaX); i++) {
                if (!chessBoard.get(fromFrame.getY() - i).get(fromFrame.getX() - i).isEmpty()) {
                    isIntersected = true;
                    break;
                }
        }
        return isIntersected;
    }

    protected boolean upRightMovementIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, int deltaX) {
        boolean isIntersected = false;
            for (int i = 1; i < Math.abs(deltaX); i++) {
                if (!chessBoard.get(fromFrame.getY() - i).get(fromFrame.getX() + i).isEmpty()) {
                    isIntersected = true;
                    break;
                }
            }
        return isIntersected;
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

    public abstract boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame);

    public void vymaz(BoardFrame toFrame) {
        toFrame.getPiece().notVisible();
    }
}