package pieces;

import Main.Board;

import java.awt.image.BufferedImage;

public class Knight extends Pieces{
    public Knight(Board board,int col,int row,boolean isWhite) {
        super(board);
        this.row=row;
        this.col=col;
        this.xPos=col* board.titleSize;
        this.yPos=row* board.titleSize;
        this.isWhite=isWhite;
        this.name="knight";
        this.sprint=sheet.getSubimage(3*sheetScale,isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col,int row)
    {
        return Math.abs(col-this.col)*Math.abs(row-this.row)==2;
    }

}
