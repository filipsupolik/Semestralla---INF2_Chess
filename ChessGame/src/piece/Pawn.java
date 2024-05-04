package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

import java.util.List;

public class Pawn extends Piece {

    private boolean firstMove;
    private String nazov;

    public Pawn(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
        this.firstMove = true;
        this.nazov = "Pawn";
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (notValidChessBoardBorderss(frameFromX, frameFromY, frameToX, frameToY)) return false;

        if (Math.abs(frameFromY - frameToY) > 2) {
            System.out.println("Vacsi rozdiel ako 2");
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.BLACK) && Math.abs(frameFromY- frameToY) > 1 && !(frameFromY == 1)) {
            System.out.println("Rozdiel vacsi ako 1 po 1 tahu Cierny");
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.WHITE) && Math.abs(frameFromY- frameToY) > 1 && !(frameFromY == 6)) {
            System.out.println("Rozdiel vacsi ako 1 po 1 tahu Biely");
            return false;
        }

        if (!(toFrame.getPiece() instanceof Empty) && !(toFrame.getPiece().getPlayerType().equals(fromFrame.getPiece().getPlayerType())) && Math.abs(frameFromX - frameToX) != 1) {
            System.out.println("Ak je inej farby panacik");
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.BLACK) && frameFromY > frameToY) {
            System.out.println("neplatny tah spatny chod Cierny");
            return false;
        }

        if (fromFrame.getPiece().getPlayerType().equals(PlayerType.WHITE) && frameToY > frameFromY) {
            System.out.println("neplatny tah spatny chod Biely");
            return false;
        }

        if (Math.abs(frameFromX - frameToX) > 0 && toFrame.getPiece() instanceof Empty) {
            System.out.println("Nemoze do boku ak tam nie je iny panacik");
            return false;
        }

        return true;
    }

    @Override
    public void popis() {
        System.out.println(this.nazov);
    }

    @Override
    public boolean anotherPieceInPositionIntersection(List<List<BoardFrame>> chessBoard, BoardFrame fromFrame, BoardFrame toFrame) {
        boolean isIntersected = false;
        if (!toFrame.isEmpty()) {
            isIntersected = true;
        }
        return isIntersected;
    }
}
