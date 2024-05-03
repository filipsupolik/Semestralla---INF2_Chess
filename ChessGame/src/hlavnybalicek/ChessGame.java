package hlavnybalicek;

import enums.PlayerType;
import fri.shapesge.Manager;

import fri.shapesge.Square;
import movement.Suradnice;
import piece.*;

import javax.swing.*;
import java.util.ArrayList;

public class ChessGame {
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

    public boolean isMate(PlayerType playerType) {
        return true;
    }

    public PlayerType getWinner() {
        return PlayerType.BLACK;
    }

    public void closeFileWriter() {

    }

    public void vyberSuradnice(int x , int y) {
        this.kliknutie(x , y);
    }

    public void kliknutie(int x , int y) {
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
            if (this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().getPlayerType().equals(this.tahHraca)) {
                if (kolidovanie(this.suradnice.get(0), this.suradnice.get(1), this.suradnice.get(2), this.suradnice.get(3))) {
                    if (valid) {
                        this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().move(this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)], chessBoard[this.suradnice.get(2)][this.suradnice.get(3)]);
                        this.chessBoard[this.suradnice.get(0)][this.suradnice.get(1)].getPiece().popis();
                        System.out.println("Prva");
                        this.chessBoard[this.suradnice.get(2)][this.suradnice.get(3)].getPiece().popis();
                        System.out.println("Druha");
                        this.zmenHraca();
                    }
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

    public Boolean kolidovanie(int polohaPrvejX , int polohaPrvejY , int polohaDruhejX , int polohaDruhejY) {
        Piece prva =this.chessBoard[polohaPrvejX][polohaPrvejY].getPiece();
        Piece druha =this.chessBoard[polohaDruhejX][polohaDruhejY].getPiece();

        if (chessBoard[polohaPrvejX][polohaPrvejY].getPiece() instanceof Bishop) {

        }

        if (chessBoard[polohaPrvejX][polohaPrvejY].getPiece() instanceof Rook) {

        }

        if (chessBoard[polohaPrvejX][polohaPrvejY].getPiece() instanceof Queen) {

        }
        return true;
    }

    public PlayerType zmenHraca() {
        if (this.tahHraca.equals(PlayerType.WHITE)) {
            this.tahHraca = PlayerType.BLACK;
        } else {
            this.tahHraca = PlayerType.WHITE;
        }
        return this.tahHraca;
    }
}