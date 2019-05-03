package checkers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Piece extends JPanel {
    private int color;
    private Image image = null;
    private boolean isKing;

    public static final int BLACK = 0;
    public static final int RED = 1;

    public Piece(int color){
        this.color = color;
        isKing = false;

        try {
            if(this.color == BLACK){
                image = ImageIO.read(new File("images/black.gif"));
            }
            else{
                image = ImageIO.read(new File("images/red.gif"));
            }


        }catch (IOException e){}

    }

    public void draw(Graphics g, int x, int y, int height, int width){
        g.drawImage(image,x+10,y+10,(int)(width*0.7),(int)(height*0.7),null);
    }
    public int getColor(){
        return color;
    }
    public void makeKing(){
        isKing = true;
        try {
            if(color == BLACK){
                image = ImageIO.read(new File("images/blackcrown.gif"));
            }
            else{
                image = ImageIO.read(new File("images/redcrown.gif"));
            }
        }catch (IOException e){

        }
    }
    public boolean isKing(){
        return isKing;
    }

}
