package checkers;

import java.awt.*;

public class Square{
    Piece p;
    int x,y,color;

    public static final int BLACK = 0;
    public static final int RED = 1;
    public static final int SIZE = 60;

    public Square(int row, int col,int color){


        this.x = col*SIZE;
        this.y = row*SIZE;
        this.color = color;
    }

    public void draw(Graphics g){
        if(this.color == Square.RED){
            g.setColor(Color.RED);
            g.fillRect(x,y,SIZE,SIZE);
            g.setColor(Color.BLACK);
        }
        else {
            g.setColor(Color.BLACK);
            g.fillRect(x,y,SIZE,SIZE);
            g.setColor(Color.RED);
        }
        if (p!=null){
            p.draw(g,x,y,SIZE,SIZE);
        }
    }



    public Piece getPiece(){
        return p;
    }
    public void addPiece(Piece p){
        this.p = p;
    }
    public void removePiece(){
        this.p =null;
    }
    public int getColor(){
        return color;
    }
    public void kingMe(){
        this.p.makeKing();
    }

}

