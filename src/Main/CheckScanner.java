package Main;

import pieces.Pieces;

public class CheckScanner {

    Board board;

    public CheckScanner(Board board){
        this.board = board;
    }

    public boolean isKingChecked(Move move){

        Pieces king = board.findKing(move.piece.isWhite);
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;

        if(board.selectedPiece!=null && board.selectedPiece.name.equals("king")){
            kingCol = move.newCol;
            kingRow = move.newRow;
        }

        return  hitByRook(move.newCol, move.newRow, king,kingCol,kingRow,0,1)||
                hitByRook(move.newCol, move.newRow, king,kingCol,kingRow,1,0)||
                hitByRook(move.newCol, move.newRow, king,kingCol,kingRow,0,-1)||
                hitByRook(move.newCol, move.newRow, king,kingCol,kingRow,-1,0)||

                hitByBishop(move.newCol, move.newRow, king,kingCol,kingRow,-1,-1)||
                hitByBishop(move.newCol, move.newRow, king,kingCol,kingRow,1,-1)||
                hitByBishop(move.newCol, move.newRow, king,kingCol,kingRow,1,1)||
                hitByBishop(move.newCol, move.newRow, king,kingCol,kingRow,-1,1)||

                hitByKnight(move.newCol, move.newRow, king,kingCol,kingRow)||
                hitByPawn(move.newCol, move.newRow, king,kingCol,kingRow)||
                hitByKing(king,kingCol,kingRow);
    }

    private boolean hitByRook(int col,int row,Pieces king,int kingCol,int kingRow,int colVal,int rowVal){
        for(int i=1;i<8;i++){
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row){
                break;
            }

             Pieces pieces = board.getPiece(kingCol + (i * colVal),kingRow + (i * rowVal));
            if(pieces!=null && pieces != board.selectedPiece){
                if(!board.sameTeam(pieces, king) && (pieces.name.equals("rook") || pieces.name.equals("queen"))){
                    return true;
                }
                break;
            }

        }
        return false;
    }

    private boolean hitByBishop(int col,int row,Pieces king,int kingCol,int kingRow,int colVal,int rowVal){
        for(int i=1;i<8;i++){
            if(kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row){
                break;
            }

            Pieces pieces = board.getPiece(kingCol - (i * colVal),kingRow - (i * rowVal));
            if(pieces!=null && pieces != board.selectedPiece){
                if(!board.sameTeam(pieces, king) && (pieces.name.equals("bishop") || pieces.name.equals("queen"))){
                    return true;
                }
                break;
            }

        }
        return false;
    }

    private boolean hitByKnight(int col,int row,Pieces king,int kingCol,int kingRow){
           return checkKnight(board.getPiece(kingCol - 1,kingRow - 2),king,col,row) ||
                  checkKnight(board.getPiece(kingCol + 1,kingRow - 2),king,col,row) ||
                  checkKnight(board.getPiece(kingCol + 2,kingRow - 1),king,col,row) ||
                  checkKnight(board.getPiece(kingCol + 2,kingRow + 1),king,col,row) ||
                  checkKnight(board.getPiece(kingCol + 1,kingRow + 2),king,col,row) ||
                  checkKnight(board.getPiece(kingCol - 1,kingRow + 2),king,col,row) ||
                  checkKnight(board.getPiece(kingCol - 2,kingRow + 1),king,col,row) ||
                  checkKnight(board.getPiece(kingCol - 2,kingRow - 1),king,col,row) ;
    }

    private boolean checkKnight(Pieces p,Pieces k,int col,int row){
        return p!=null && !board.sameTeam(p,k) && p.name.equals("knight") && !(p.col == col && p.row == row);
    }

    private boolean hitByKing(Pieces king,int kingCol,int kingRow){
         return checkKing(board.getPiece(kingCol - 1,kingRow - 1),king) ||
                checkKing(board.getPiece(kingCol + 1,kingRow - 1),king) ||
                checkKing(board.getPiece(kingCol,kingRow - 1),king) ||
                checkKing(board.getPiece(kingCol - 1,kingRow),king) ||
                checkKing(board.getPiece(kingCol + 1,kingRow),king) ||
                checkKing(board.getPiece(kingCol - 1,kingRow + 1),king) ||
                checkKing(board.getPiece(kingCol + 1,kingRow + 1),king) ||
                checkKing(board.getPiece(kingCol,kingRow + 1),king);
    }

    private boolean checkKing(Pieces p,Pieces k){
        return p!=null && !board.sameTeam(p,k)&& p.name.equals("king");
    }

    private boolean hitByPawn(int col,int row,Pieces king,int kingCol, int kingRow){
        int colrVal = king.isWhite ? -1 : 1;
        return checkPawn(board.getPiece(kingCol + 1,kingRow + colrVal),king,col,row) ||
                checkPawn(board.getPiece(kingCol - 1,kingRow + colrVal),king,col,row);
    }

    private boolean checkPawn(Pieces p,Pieces k,int col,int row){
        return p != null && !board.sameTeam(p,k) && p.name.equals("pawn")&& !(p.col == col && p.row == row);
    }

}
