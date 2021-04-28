/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt_si;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joanna
 */
public class Dane {
  //0-NKB
  //1-Gray 
  int kod=-1; 
  
  //rodzaj krzyzowania
  int k=-1;
  //prawdopodobienstwo mutacji krzyzowania        
  float krzyzowanie=0;
  float mutacja=0;
  
  //wielkosc populacji
  int size_population=0;

  //populacja zapisana w kodzie
  ArrayList<Osobnik> dane=new ArrayList<Osobnik>();

  

    
}
