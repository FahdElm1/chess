package pieces;

import Main.Board;
import Main.Move;

import java.awt.image.BufferedImage;

public class king extends Pieces{
    public king (Board board,int col,int row,boolean isWhite) {
        super(board);
        this.row=row;
        this.col=col;
        this.xPos=col* board.titleSize;
        this.yPos=row* board.titleSize;
        this.isWhite=isWhite;
        this.name="king";
        this.sprint=sheet.getSubimage(0*sheetScale,isWhite ? 0 : sheetScale,sheetScale,sheetScale).getScaledInstance(board.titleSize,board.titleSize, BufferedImage.SCALE_SMOOTH);
    }

    public boolean isValidMovement(int col,int row){
        return Math.abs((col - this.col)*(row - this.row)) ==  1 || Math.abs(col - this.col)+Math.abs(row - this.row) == 1 || canCastle(col,row);
    }

    private boolean canCastle(int col,int row){
      if(this.row == row) {

          if (col == 6) {
              Pieces rook = board.getPiece(7, row);
              if (rook != null && rook.isFirstMove && isFirstMove) {
                  return board.getPiece(5, row) == null &&
                          board.getPiece(6, row) == null &&
                          !board.checkScanner.isKingChecked(new Move(board, this, 5, row));
              }
          } else if (col == 2) {
              Pieces rook = board.getPiece(0, row);
              if (rook != null && rook.isFirstMove && isFirstMove) {
                  return board.getPiece(3, row) == null &&
                          board.getPiece(2, row) == null &&
                          board.getPiece(1, row) == null &&
                          !board.checkScanner.isKingChecked(new Move(board, this, 3, row));
              }
          }
      }
        return false;
    }

}
