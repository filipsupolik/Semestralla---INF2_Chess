package hlavnybalicek;

import enums.PlayerType;
import fri.shapesge.Manager;
import fri.shapesge.Square;
import movement.Suradnice;
import piece.Bishop;
import piece.Empty;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 * The ChessGame class represents a game of chess, managing the game board, pieces, and player turns.
 */
public class ChessGame {
    private boolean myKingInCheck;
    private Manager manager;
    private final BoardFrame[][] chessBoard;
    private Square[][] backgroundField;
    private ArrayList<Integer> suradnice;
    private Suradnice suradnica;
    private int pocitadlo;
    private PlayerType tahHraca;

    /**
     * Constructs a new ChessGame, initializing the board and setting up the pieces.
     */
    public ChessGame () {
        this.manager = new Manager();
        this.suradnice = new ArrayList<>();
        this.suradnica = new Suradnice();
        this.chessBoard = new BoardFrame[8][8];
        this.pocitadlo = 0;
        this.manager.manageObject(this);
        this.tahHraca = PlayerType.WHITE;
        this.myKingInCheck = false;

        this.backgroundField = new Square[8][8];
        for (int i = 0; i < this.backgroundField.length; i++) {
            for (int j = 0; j < this.backgroundField[i].length; j++) {
                this.backgroundField[i][j] = new Square(0, 0);
                int sum = i + j;
                if ((sum % 2 == 0)) {
                    this.backgroundField[i][j].changeColor("white");
                } else {
                    this.backgroundField[i][j].changeColor("black");
                }
                this.backgroundField[i][j].changeSize(100);
                this.backgroundField[i][j].moveHorizontal(j * 100);
                this.backgroundField[i][j].moveVertical(i * 100);
                this.backgroundField[i][j].makeVisible();
            }
        }

        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++) {
                this.chessBoard[i][j] = new BoardFrame(j, i);
                this.chessBoard[i][j].setPiece(new Empty(PlayerType.EMPTY, "piece/b-pawn.png", j, i));
                switch (i) {
                    case 1 ->
                            this.chessBoard[i][j].setPiece(new Pawn(PlayerType.BLACK, "piece/b-pawn.png", j, i));
                    case 6 ->
                            this.chessBoard[i][j].setPiece(new Pawn(PlayerType.WHITE, "piece/w-pawn.png", j, i));
                    case 0 -> {
                        switch (j) {
                            case 0, 7 ->
                                    this.chessBoard[i][j].setPiece(new Rook(PlayerType.BLACK, "piece/b-rook.png", j, i));
                            case 1, 6 ->
                                    this.chessBoard[i][j].setPiece(new Knight(PlayerType.BLACK, "piece/b-knight.png", j, i));
                            case 2, 5 ->
                                    this.chessBoard[i][j].setPiece(new Bishop(PlayerType.BLACK, "piece/b-bishop.png", j, i));
                            case 3 ->
                                    this.chessBoard[i][j].setPiece(new Queen(PlayerType.BLACK, "piece/b-queen.png", j, i));
                            case 4 ->
                                    this.chessBoard[i][j].setPiece(new King(PlayerType.BLACK, "piece/b-king.png", j, i));
                        }
                    }
                    case 7 -> {
                        switch (j) {
                            case 0, 7 ->
                                    this.chessBoard[i][j].setPiece(new Rook(PlayerType.WHITE, "piece/w-rook.png", j, i));
                            case 1, 6 ->
                                    this.chessBoard[i][j].setPiece(new Knight(PlayerType.WHITE, "piece/w-knight.png", j, i));
                            case 2, 5 ->
                                    this.chessBoard[i][j].setPiece(new Bishop(PlayerType.WHITE, "piece/w-bishop.png", j, i));
                            case 3 ->
                                    this.chessBoard[i][j].setPiece(new Queen(PlayerType.WHITE, "piece/w-queen.png", j, i));
                            case 4 ->
                                    this.chessBoard[i][j].setPiece(new King(PlayerType.WHITE, "piece/w-king.png", j, i));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++) {
                if (!(this.chessBoard[i][j].isEmpty())) {
                    this.chessBoard[i][j].getPiece().makeVisible();
                }
            }
        }
    }

    /**
     * Checks if the specified player is in checkmate.
     *
     * @param playerType the player type to check for checkmate
     * @return true if the player is in checkmate, false otherwise
     */
    public boolean isCheckMate(PlayerType playerType) {
        // TODO: Implement checkmate logic
        return true;
    }

    /**
     * Checks if the player on turn gives check to the opponent.
     *
     * @param chessBoard the current state of the chessboard
     * @param playerType the player type to check
     * @param fromFrame  the frame from which the move is made
     * @return true if the opponent's king is in check, false otherwise
     */
    public boolean isCheck(List<List<BoardFrame>> chessBoard, PlayerType playerType, BoardFrame fromFrame) {
        boolean check = false;
        Piece king = null;
        for (List<BoardFrame> row : chessBoard) {
            for (BoardFrame frame : row) {
                // Get the opponent's king
                if (frame.getPiece() instanceof King && !frame.getPiece().getPlayerType().equals(playerType)) {
                    king = frame.getPiece();
                }
            }
        }
        boolean valid = chessBoard.get(fromFrame.getY()).get(fromFrame.getX()).getPiece().isValidMove(chessBoard.get(fromFrame.getY()).get(fromFrame.getX()), chessBoard.get(king.getY()).get(king.getX()));
        boolean pieceIntersection = chessBoard.get(fromFrame.getY()).get(fromFrame.getX()).getPiece().anotherPieceInPositionIntersection(chessBoard, chessBoard.get(fromFrame.getY()).get(fromFrame.getX()), chessBoard.get(king.getY()).get(king.getX()));
        if (valid && !pieceIntersection) {
            check = true;
            JOptionPane.showMessageDialog(null, "Check");
        }
        return check;
    }

    /**
     * Checks if the current player's king is in check.
     *
     * @param chessBoard the current state of the chessboard
     * @param playerType the player type to check
     * @return true if the player's king is in check, false otherwise
     */
    public boolean myKinginCheck(List<List<BoardFrame>> chessBoard, PlayerType playerType) {
        boolean check = false;
        Piece king = null;
        for (List<BoardFrame> row : chessBoard) {
            for (BoardFrame frame : row) {
                // Get the current player's king
                if (frame.getPiece() instanceof King && frame.getPiece().getPlayerType().equals(playerType)) {
                    king = frame.getPiece();
                }
            }
        }
        for (List<BoardFrame> row : chessBoard) {
            for (BoardFrame frame : row) {
                if (!frame.getPiece().getPlayerType().equals(king.getPlayerType())) {
                    boolean validMoveOfEnemy = frame.getPiece().isValidMove(frame, chessBoard.get(king.getY()).get(king.getX()));
                    boolean enemyIntersection = frame.getPiece().anotherPieceInPositionIntersection(chessBoard, frame, chessBoard.get(king.getY()).get(king.getX()));
                    if (validMoveOfEnemy && !enemyIntersection) {
                        check = true;
                    }
                }
            }
        }
        return check;
    }

    /**
     * Gets the winner of the game.
     *
     * @return the player type of the winner
     */
    public PlayerType getWinner() {
        return PlayerType.BLACK;
    }

    /**
     * Selects the coordinates of a square on the board.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void vyberSuradnice(int x, int y) {
        this.kliknutie(x, y);
    }

    /**
     * Handles the logic for a click on the board.
     *
     * @param x the x-coordinate of the click
     * @param y the y-coordinate of the click
     */
    public void kliknutie(int x, int y) {
        List<List<BoardFrame>> listChessBoard = this.convertTo2DList(this.chessBoard);
        this.suradnica.konvertujPlohu(x, y, 840 / 8);
        if (this.pocitadlo == 0) {
            this.suradnice.add(this.suradnica.getY());
            this.suradnice.add(this.suradnica.getX());
            System.out.println("First click");
            this.pocitadlo++;
        } else {
            this.pocitadlo = 0;
            this.suradnice.add(this.suradnica.getY());
            this.suradnice.add(this.suradnica.getX());
            if (this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().getPlayerType().equals(this.tahHraca)) {
                boolean valid = this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().isValidMove(this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], this.chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                boolean pieceIntersection = this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().anotherPieceInPositionIntersection(listChessBoard, this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], this.chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                if (valid && !pieceIntersection && this.canMovePieceKingInChess(listChessBoard)) {
                    this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().vymaz(this.chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                    this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().move(this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], this.chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                    BoardFrame toFrame = this.chessBoard[this.suradnice.get(2)][this.suradnice.get(3)];
                    this.isCheck(listChessBoard, this.tahHraca, toFrame);
                    this.myKingInCheck = this.myKinginCheck(listChessBoard, this.tahHraca);
                    this.zmenHraca();
                } else if (this.myKingInCheck) {
                    this.myKingInCheck = this.myKinginCheck(listChessBoard, this.tahHraca);
                    JOptionPane.showMessageDialog(null, "You must remove the check before making a move.");
                }
                System.out.println("Player's turn");
                System.out.println(this.tahHraca);
            }

            System.out.println("Second click");
            System.out.println(this.suradnice.get(2));
            System.out.println(this.suradnice.get(3));
            this.suradnice.clear();
        }
    }

    /**
     * Changes the player turn.
     *
     * @return the player type of the next player
     */
    public PlayerType zmenHraca() {
        if (this.tahHraca.equals(PlayerType.WHITE)) {
            this.tahHraca = PlayerType.BLACK;
        } else {
            this.tahHraca = PlayerType.WHITE;
        }
        return this.tahHraca;
    }

    /**
     * Converts the 2D array of BoardFrame objects to a list of lists of BoardFrame objects.
     *
     * @param chessBoard the 2D array to convert
     * @return the converted list of lists
     */
    public List<List<BoardFrame>> convertTo2DList(BoardFrame[][] chessBoard) {
        List<List<BoardFrame>> list = new ArrayList<>();

        for (BoardFrame[] row : chessBoard) {
            List<BoardFrame> rowList = new ArrayList<>();
            for (BoardFrame frame : row) {
                rowList.add(frame);
            }
            list.add(rowList);
        }

        return list;
    }

    /**
     * Checks if a piece can move without putting the king in check.
     *
     * @param list the current state of the chessboard
     * @return true if the piece can move without putting the king in check, false otherwise
     */
    public boolean canMovePieceKingInChess(List<List<BoardFrame>> list) {
        Piece movingPiece = this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece();

        // Iterate through all possible moves of the piece
        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++) {
                if (this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().isValidMove(this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], this.chessBoard[i][j])) {
                    // Move the piece to the possible position
                    Piece tempPiece = this.chessBoard[i][j].getPiece();
                    this.chessBoard[i][j].setPiece(movingPiece);
                    this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].setPiece(new Empty(PlayerType.EMPTY, "piece/b-bishop.png", this.suradnice.get(0), this.suradnice.get(1)));

                    // Check if this position leads to a check
                    boolean kingInCheck = this.myKinginCheck(list, this.tahHraca);

                    // Return the piece to its original position
                    this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].setPiece(movingPiece);
                    this.chessBoard[i][j].setPiece(tempPiece);

                    // If the move cancels the check, return true
                    if (!kingInCheck) {
                        return true;
                    }
                }
            }
        }

        // If no move cancels the check, return false
        return false;
    }
}
