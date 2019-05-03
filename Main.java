package checkers;


import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame checkers = new JFrame();
        checkers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkers.setSize(496,518);
        checkers.setResizable(false);

        checkers.add(new Board());
        checkers.setBackground(Color.MAGENTA);
        checkers.setVisible(true);
    }
}