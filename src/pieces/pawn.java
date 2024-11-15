package pieces;

import Main.Board;

import java.awt.image.BufferedImage;

public class pawn extends Pieces{
    public pawn (Board board,int col,int row,boolean isWhite) {
        super(board);
        this.row=row;
        this.col=col;
        this.xPos=col* board.titleSize;
        this.yPos=row* board.titleSize;
        this.isWhite=isWhite;
        this.name="pawn";
        this.sprint=sheet.getSubimage(5*sheetScale,isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int col,int row){

        int colorIndex = isWhite ?1:-1;

        if(this.col == col && row == this.row - colorIndex && board.getPiece(col,row)==null)
            return true;

        if(isFirstMove && this.col == col && row == this.row - colorIndex * 2 && board.getPiece(col,row)==null && board.getPiece(col,row*colorIndex)==null)
            return true;

        if(col == this.col - 1 && row == this.row - colorIndex && board.getPiece(col,row)!=null)
            return true;

        if(col == this.col + 1 && row == this.row - colorIndex && board.getPiece(col,row)!=null)
            return true;

        if(board.getTileNum(col,row) == board.enPassantTile && col == this.col - 1 && row == this.row - colorIndex && board.getPiece(col,row+colorIndex)!=null)
            return true;

        if(board.getTileNum(col,row) == board.enPassantTile && col == this.col + 1 && row == this.row - colorIndex && board.getPiece(col,row+colorIndex)!=null)
            return true;

        return false;
    }

}
