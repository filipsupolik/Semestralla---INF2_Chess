package piece;

import enums.PlayerType;
import hlavnybalicek.BoardFrame;

public class Pawn extends Piece {

    private int counter;
    private boolean firstMove;

    public Pawn(PlayerType playerType, String imagePath, int x, int y) {
        super( playerType, imagePath, x, y);
        this.counter = 0;
        this.firstMove = true;
    }

    @Override
    public boolean isValidMove(BoardFrame fromFrame, BoardFrame toFrame) {
        int frameFromX = fromFrame.getX();
        int frameFromY = fromFrame.getY();
        int frameToX = toFrame.getX();
        int frameToY = toFrame.getY();

        if (validChessBoardBorders(frameFromX, frameFromY, frameToX, frameToY)) {

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
        }
        return true;
    }
}
