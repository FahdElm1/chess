package pieces;

import Main.Board;

import java.awt.image.BufferedImage;

public class bishop extends Pieces{
    public bishop (Board board,int col,int row,boolean isWhite) {
        super(board);
        this.row=row;
        this.col=col;
        this.xPos=col* board.titleSize;
        this.yPos=row* board.titleSize;
        this.isWhite=isWhite;
        this.name="bishop";
        this.sprint=sheet.getSubimage(2*sheetScale,isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int col,int row)
    {
        return Math.abs(col-this.col)==Math.abs(row-this.row);
    }
    public boolean moveCollidesWithPiece(int col,int row)
    {
        if(this.col>col&&this.row>row)
            for(int i=1;i < Math.abs(this.col-col); i++)
             if(board.getPiece(this.col-i,this.row-i)!=null)
                 return true;

        if(this.col<col&&this.row>row)
            for(int i=1;i < Math.abs(this.col-col); i++)
                if(board.getPiece(this.col+i,this.row-i)!=null)
        return true;

        if(this.col>col&&this.row<row)
            for(int i=1;i < Math.abs(this.col-col); i++)
                if(board.getPiece(this.col-i,this.row+i)!=null)
                    return true;

        if(this.col<col&&this.row<row)
            for(int i=1;i < Math.abs(this.col-col); i++)
                if(board.getPiece(this.col+i,this.row+i)!=null)
                    return true;

        return false;
    }
}
