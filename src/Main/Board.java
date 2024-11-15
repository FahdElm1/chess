package Main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int titleSize = 90;
    int cols=8;
    int rows=8;
    ArrayList<Pieces> piecesList = new ArrayList<>();

    public Pieces selectedPiece;
    Input input=new Input(this);

    public CheckScanner checkScanner = new CheckScanner(this);
    public int enPassantTile = -1;

    public Board(){
        this.setPreferredSize(new Dimension(cols*titleSize,rows*titleSize));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }
    public Pieces getPiece(int col,int row){
        for(Pieces piece : piecesList){
            if(piece.col==col && piece.row==row)
            {
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move){

        if(move.piece.name.equals("pawn")){
            movePawn(move);
        }else if(move.piece.name.equals("king")){
            moveKing((move));
        }

        move.piece.col=move.newCol;
        move.piece.row=move.newRow;
        move.piece.xPos=move.newCol*titleSize;
        move.piece.yPos=move.newRow*titleSize;

        move.piece.isFirstMove = false;

        capture(move.capture);

    }

    private void moveKing(Move move){

        if(Math.abs(move.piece.col - move.newCol) == 2){
            Pieces rook;
            if(move.piece.col < move.newCol){
                rook = getPiece(7,move.piece.row);
                rook.col = 5;
            } else {
                rook = getPiece(0,move.piece.row);
                rook.col = 3;
            }
            rook.xPos = rook.col * titleSize;
        }

    }

    private void movePawn(Move move) {

        int colorIndex = move.piece.isWhite ? 1 : -1;

        if(getTileNum(move.newCol,move.newRow) == enPassantTile) {
          move.capture = getPiece(move.newCol,move.newRow + colorIndex);
        }
        if(Math.abs(move.piece.row - move.newRow)==2){
            enPassantTile = getTileNum(move.newCol,move.newRow + colorIndex);
        }else {
            enPassantTile = -1;
        }

        colorIndex = move.piece.isWhite ? 0 : 7;
        if(move.newRow == colorIndex){
            promotePawn(move);
        }

    }

    private void promotePawn(Move move) {
        piecesList.add(new queen(this,move.newCol,move.newRow,move.piece.isWhite));
        capture(move.piece);
    }

    public void capture(Pieces pieces){
        piecesList.remove(pieces);
    }

    public boolean isValidMove(Move move){
        if(sameTeam(move.piece,move.capture))
        {
            return false;
        }
        if(!move.piece.isValidMovement(move.newCol,move.newRow)) {
            return false;
        }
        if(move.piece.moveCollidesWithPiece(move.newCol,move.newRow)){
            return false;
        }
        if(checkScanner.isKingChecked(move)){
            return false;
        }
        return true;
    }

    public boolean sameTeam(Pieces p1,Pieces p2){
        if(p1==null||p2==null)
        {
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }

    public int getTileNum(int col, int row){
        return row * rows + col;
    }

    Pieces findKing(boolean isWhite){
        for(Pieces pieces:piecesList){
            if(isWhite == pieces.isWhite && pieces.name.equals("king")){
                return pieces;
            }
        }
        return null;
    }

    public void addPieces(){
         piecesList.add(new Knight(this,1,0,false));
         piecesList.add(new Knight(this,1,7,true));
         piecesList.add(new Knight(this,6,0,false));
         piecesList.add(new Knight(this,6,7,true));

         piecesList.add(new bishop(this,2,0,false));
         piecesList.add(new bishop(this,2,7,true));
         piecesList.add(new bishop(this,5,0,false));
         piecesList.add(new bishop(this,5,7,true));

         piecesList.add(new rook(this,0,0,false));
         piecesList.add(new rook(this,0,7,true));
         piecesList.add(new rook(this,7,0,false));
         piecesList.add(new rook(this,7,7,true));

         piecesList.add(new queen(this,3,0,false));
         piecesList.add(new queen(this,3,7,true));

         piecesList.add(new king(this,4,0,false));
         piecesList.add(new king(this,4,7,true));

         for(int i=0;i<8;i++)
         piecesList.add(new pawn(this,i,1,false));
        for(int i=0;i<8;i++)
             piecesList.add(new pawn(this,i,6,true));

    }
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d=(Graphics2D) g;
        for(int i=0;i<cols;i++)
        for(int j=0;j<rows;j++)
        {
            g2d.setColor((i+j) %2==0 ? new Color(232, 236, 203) : new Color(118, 152, 84));
            g2d.fillRect(i*titleSize,j*titleSize,titleSize,titleSize);
        }
         if(selectedPiece!=null)
        for(int i=0;i<cols;i++)
            for(int j=0;j<rows;j++)
            {
                if(isValidMove(new Move(this,selectedPiece,i,j)))
                {
                    g2d.setColor(new Color(153, 201, 195, 171));
                    g2d.fillRect(i*titleSize,j*titleSize,titleSize,titleSize);
                }
            }

        for(Pieces pieces : piecesList)
        {
            pieces.pain(g2d);
        }
    }
}
