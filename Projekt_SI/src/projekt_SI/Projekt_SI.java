/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_si;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Joanna
 */
public class Projekt_SI {
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Dane dane=new Dane();
        
        Main_Frame frame = new Main_Frame();
        //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 500));
        frame.getContentPane().setLayout(null);
       
        
        //Wyswietla okno
        frame.pack();
        frame.setVisible(true);
    }
    
}
