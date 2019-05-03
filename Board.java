package checkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JPanel implements MouseListener {
    Square[][] board;
    int turn;
    Square firstSelected;
    Square secondSelected;

    public Board(){
        turn = 0;

        addMouseListener(this);
        //initialize board array
        board = new Square[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8 ; col++) {
                if(row%2 == col%2){
                    board[row][col] = new Square(row,col,1);
                }
                else{
                    board[row][col] = new Square(row,col,0);
                    if(row<3){
                        board[row][col].addPiece(new Piece(Piece.BLACK));
                    }
                    else if(row>4){
                        board[row][col].addPiece(new Piece(Piece.RED));
                    }
                }
            }
        }

        //place graphic squares
        repaint();
        setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].draw(g);
            }
            
        }
        
    }
    
    
    
    public void move(){//piece is selected, corresponds to turn,  potential destination square given
        //determine if piece is king or not
        if(!(firstSelected.getPiece().isKing())){//if not king
            //determine if legal direction of move
            if(!(firstSelected.getPiece().isKing()) && ((turn == Piece.BLACK && firstSelected.y > secondSelected.y) || (turn == Piece.RED && firstSelected.y < secondSelected.y))){//if not legal direction
                System.out.println("non-crown can't move backward");
                return;
            }
            //else do nothing
        }
        //else do nothing

        //determine if destination is diagonal to origin
        if (!(Math.pow((firstSelected.x-secondSelected.x),2) == Math.pow((firstSelected.y-secondSelected.y),2))){//if not (change in x == change in y)
            System.out.println("not a diagonal move");
            return;
        }

        else{//move is diagonal
            //determine if destination square has no piece currently present
            if(secondSelected.getPiece()!= null){// if destination has piece present
                System.out.println("cannot move to occupied square");
                return;
            }
            else{//move is to empty square
                //determine if destination is 1,2 or (other number) spaces away
                int distanceSquared = (int) (Math.pow((firstSelected.x-secondSelected.x)/Square.SIZE,2)+ Math.pow((firstSelected.y-secondSelected.y)/Square.SIZE,2));
                System.out.println(distanceSquared);
                if(distanceSquared == 2){//if destination 1 space away: (firstx-lastx)^2 + (firsty -lasty)^2 = 2
                    secondSelected.addPiece(firstSelected.getPiece());
                    firstSelected.removePiece();
                    repaint();
                }

                else if(distanceSquared == 8){//if destination 2 spaces away
                    Square s = board[(firstSelected.y + secondSelected.y)/(2*Square.SIZE)][(firstSelected.x + secondSelected.x)/(2*Square.SIZE)];
                    if(s.getPiece() == null || s.getPiece().getColor()==firstSelected.getPiece().getColor()){//attempting jump over empty or own piece
                        System.out.println("can only jump if there is an opponent piece between");
                        return;
                    }
                    else {//piece belongs to opponent
                        s.removePiece();
                        secondSelected.addPiece(firstSelected.getPiece());
                        firstSelected.removePiece();
                        repaint();
                    }
                }

                else{//move is to same square or to square further than 2 away
                    System.out.println("cannot move this many spaces");
                    return;
                }
            }

        }

        if((turn==Piece.BLACK&&(secondSelected.y/Square.SIZE)==7) || (turn==Piece.RED&&(secondSelected.y/Square.SIZE)==0)){//if reached end of board
            //crown
            secondSelected.getPiece().makeKing();
            repaint();
        }

        turn = (turn + 1)%2;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //print square clicked
        System.out.println("row "+e.getY()/Square.SIZE);
        System.out.println("Col "+e.getX()/Square.SIZE);




        Square s = board[e.getY()/Square.SIZE][e.getX()/Square.SIZE];
        if(firstSelected!= null){
            secondSelected = s;
            move();
            firstSelected = null;
        }



        else{//firstselected==null
            if(s.getPiece()==null){//trying to move nonexistent piece
                System.out.println("no piece on square");
            }
            else{
                if(s.getPiece().getColor()==turn){
                    firstSelected = s;
                }
                else{//trying to move opponent piece
                    System.out.println("not your piece");
                }

            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
