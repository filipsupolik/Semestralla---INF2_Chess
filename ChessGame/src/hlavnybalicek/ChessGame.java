package hlavnybalicek;

import enums.PlayerType;
import fri.shapesge.Manager;

import fri.shapesge.Square;
import movement.Suradnice;
import piece.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private boolean myKingInCheck;
    private Manager manager;
    private final BoardFrame[][] chessBoard;
    private Square[][] backgroundField;
    private ArrayList<Integer> suradnice;
    private Suradnice suradnica;
    private int pocitadlo;
    private PlayerType tahHraca;


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
        for (int i = 0; i < backgroundField.length; i++) {
            for (int j = 0; j < backgroundField[i].length; j++) {
                backgroundField[i][j] = new Square(0,0);
                int sum = i + j;
                if ((sum % 2 == 0)) {
                    backgroundField[i][j].changeColor("white");
                } else {
                    backgroundField[i][j].changeColor("black");
                }
                backgroundField[i][j].changeSize(100);
                backgroundField[i][j].moveHorizontal(j*100);
                backgroundField[i][j].moveVertical(i*100);
                backgroundField[i][j].makeVisible();
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

    public boolean isCheckMate(PlayerType playerType) {
        return true;
    }

    //Overenie ci hrac, ktory je na tahu dava sach protihracovi
    public boolean isCheck(List<List<BoardFrame>> chessBoard,PlayerType playerType, BoardFrame fromFrame) {
        boolean check = false;
        Piece king = null;
        for (int i = 0; i < chessBoard.size(); i++) {
            for (int j = 0; j < chessBoard.get(i).size(); j++) {
                //Dostanem krala opacneho k farbe hraca na tahu
                if (chessBoard.get(i).get(j).getPiece() instanceof King && !chessBoard.get(i).get(j).getPiece().getPlayerType().equals(playerType)) {
                    king = chessBoard.get(i).get(j).getPiece();
                }
            }
        }
        boolean valid = chessBoard.get(fromFrame.getY()).get(fromFrame.getX()).getPiece().isValidMove(chessBoard.get(fromFrame.getY()).get(fromFrame.getX()), chessBoard.get(king.getY()).get(king.getX()));
        boolean pieceIntersection = chessBoard.get(fromFrame.getY()).get(fromFrame.getX()).getPiece().anotherPieceInPositionIntersection(chessBoard,chessBoard.get(fromFrame.getY()).get(fromFrame.getX()), chessBoard.get(king.getY()).get(king.getX()));
        if (valid) {
            if (!pieceIntersection) {
                check = true;
                JOptionPane.showMessageDialog(null, "Sach");
            }
        }
        return check;
    }

    //Overenie ci moj kral nedostal sach
    public boolean myKinginCheck(List<List<BoardFrame>> chessBoard,PlayerType playerType) {
        boolean check = false;
        Piece king = null;
        for (int i = 0; i < chessBoard.size(); i++) {
            for (int j = 0; j < chessBoard.get(i).size(); j++) {
                //Dostanem svojho krala k farbe hraca na tahu
                if (chessBoard.get(i).get(j).getPiece() instanceof King && chessBoard.get(i).get(j).getPiece().getPlayerType().equals(playerType)) {
                    king = chessBoard.get(i).get(j).getPiece();
                }
            }
        }
        for (int i = 0; i < chessBoard.size(); i++) {
            for (int j = 0; j < chessBoard.get(i).size(); j++) {
                if (!chessBoard.get(i).get(j).getPiece().getPlayerType().equals(king.getPlayerType())) {
                    boolean validMoveOfEnemy = chessBoard.get(i).get(j).getPiece().isValidMove(chessBoard.get(i).get(j), chessBoard.get(king.getY()).get(king.getX()));
                    boolean enemyIntersection = chessBoard.get(i).get(j).getPiece().anotherPieceInPositionIntersection(chessBoard,chessBoard.get(i).get(j), chessBoard.get(king.getY()).get(king.getX()));
                    if (validMoveOfEnemy) {
                        if (!enemyIntersection) {
                            check = true;
                        }
                    }
                }
            }
        }
        return check;
    }
    public PlayerType getWinner() {
        return PlayerType.BLACK;
    }

    public void vyberSuradnice(int x , int y) {
        this.kliknutie(x , y);
    }

    public void kliknutie(int x , int y) {
        List<List<BoardFrame>> listChessBoard = this.convertTo2DList();
        this.suradnica.konvertujPlohu(x , y , 840/8);
        if (this.pocitadlo == 0) {
            this.suradnice.add(this.suradnica.getY());
            this.suradnice.add(this.suradnica.getX());
            System.out.println("Prvy klik");
            this.pocitadlo++;
        } else {
            this.pocitadlo = 0;
            this.suradnice.add(this.suradnica.getY());
            this.suradnice.add(this.suradnica.getX());

            boolean valid = this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().isValidMove(this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
            boolean pieceIntersection = this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().anotherPieceInPositionIntersection(listChessBoard,this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
            if (this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().getPlayerType().equals(this.tahHraca)) {
                if (valid && !pieceIntersection) {
                            this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().vymaz(chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                            this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().move(this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                            BoardFrame toFrame = chessBoard[this.suradnice.get(2)][this.suradnice.get(3)];
                            this.isCheck(listChessBoard, this.tahHraca, toFrame);
                            this.myKingInCheck = this.myKinginCheck(listChessBoard, this.tahHraca);
                            this.zmenHraca();
                } else if (valid && !pieceIntersection && this.myKingInCheck) {
                    this.myKingInCheck = this.myKinginCheck(listChessBoard, this.tahHraca);
                    JOptionPane.showMessageDialog(null, "Musis odstranit Sach inak sa nemozes pohnut");
                }
                System.out.println("Hrac na tahu");
                System.out.println(this.tahHraca);
            }

            System.out.println("Druhy klik");
            System.out.println(this.suradnice.get(2));
            System.out.println(this.suradnice.get(3));
            this.suradnice.clear();
        }
    }

    public PlayerType zmenHraca() {
        if (this.tahHraca.equals(PlayerType.WHITE)) {
            this.tahHraca = PlayerType.BLACK;
        } else {
            this.tahHraca = PlayerType.WHITE;
        }
        return this.tahHraca;
    }

    //Code taken from AI, chatGPT
    public List<List<BoardFrame>> convertTo2DList() {
        List<List<BoardFrame>> list = new ArrayList<>();

        for (BoardFrame[] row : this.chessBoard) {
            List<BoardFrame> rowList = new ArrayList<>();
            for (BoardFrame frame : row) {
                rowList.add(frame);
            }
            list.add(rowList);
        }

        return list;
    }
}