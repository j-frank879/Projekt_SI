/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_si;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Joanna
 */
public class Project_SI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Data data = new Data();

        Main_Frame frame = new Main_Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 500));
        frame.getContentPane().setLayout(null);

        //Display window
        frame.pack();
        frame.setVisible(true);
    }
    
}
