package pieces;

import Main.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Pieces {
    public int col,row;
    public int xPos,yPos;
    public boolean isWhite;
    public String name;
    public int value;
    public boolean isFirstMove = true;
    BufferedImage sheet;
    {
      try{
          sheet=ImageIO.read(ClassLoader.getSystemResourceAsStream("pieces.png"));
      }  catch (IOException e){
          e.printStackTrace();
      }
    }
    protected int sheetScale = sheet.getWidth()/6;
    Image sprint;

    Board board;
    public Pieces(Board board)
    {
       this.board = board;
    }

    public boolean isValidMovement(int col,int row){return true;}
    public boolean moveCollidesWithPiece(int col,int row){return false;}

    public void pain(Graphics2D g2d){
        g2d.drawImage(sprint,xPos,yPos,null);
    }
}
