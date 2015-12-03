/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Andreea
 */
public class Cell {
    public static Cell cell;
    public int x; //coordonatele celulei
    public int y;
    public static int size=20;
    public boolean alive; //starea actuală
    public boolean nextRound; //starea viitoare
    
   /**
    * Constructorul clasei initializeaza coordonatele celulei
    * @param x coordonata x
    * @param y coordonata y
    */ 
    protected Cell(int x, int y){  
        this.x=x;
        this.y=y;
    }
    /**
     * Metoda statică prin care se obține o instanța a celulei
     *@param x  coordonata x
     * @param y coordonata y
     * @return cell o instanta a celulei
     */
    public static Cell getCell(int x, int y){ //metodă statică prin care se obțne o instanță a celulei
        cell=new Cell(x,y);
        return cell;
    }
    
    /**
     * Metoda setează starea curentă a celulei
     * @param alive - true, dacă celula este vie
     *              - false, dacă celula este moartă
     */
    public void setAlive(boolean alive){
        this.alive=alive;
    }
    /**
     * 
     * @return -starea curentă a celulei
     */
    public boolean isAlive(){
        return alive;
    }
    
    /**
     * Metoda setează starea celulei pentru frame-ul următor;
     * @param nextRound - true, dacă celula va fi vie
     *                  - false, dacă celula va fi moartă
     */
    public void setNextRound(boolean nextRound){
        this.nextRound=nextRound;
    }
    /**
     * se atribuie variabilei alive valoarea variabilei nextRound;
     */
    public void nextRound(){
        alive=nextRound;
    }
    /**
     * Celula se desenează sub forma unui patratel de dimensiunea size
     * @param g 
     */
    public void drawCell(Graphics g){  //creearea grafică a celulei
        g.drawRect(x*size,y*size,size,size);
        if(alive == true)
            g.setColor(Color.red);
            else
            g.setColor(Color.white);
        g.fillRect(x*size , y*size,size-1,size-1);
    }
    
}
