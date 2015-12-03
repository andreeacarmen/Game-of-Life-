/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import static gol.Dimensions.HEIGHT;
import static gol.Dimensions.WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 *Această clasă implementează simularea jocului.
 *Clasa implementează interfețele MouseListener, MouseMotionListener și KeyListener pentru a permite 
 * introducerea datelor, cu ajutorul tastaturii și a mouse-ului, prin interfața grafică
 * @author Andreea
 */
public class Game implements MouseListener, MouseMotionListener, KeyListener{
    
    public static Game game; //instanta jocului
    Cell[][] cells; //matricea de celule
    int width=WIDTH.value/Cell.size; //dimensiunile matricei de celule
    int height = HEIGHT.value/Cell.size;
    private int nrGeneration; 
    int go = -1; //starea în care se află jocul
    int button;
    static boolean border=true;
    
    /**
     * In constructor se initializeaza matricea de celule
     */
    private Game(){
       cells = new Cell [width][height];
       for (int i=0;i<width;i++)
            for (int j=0;j<height;j++){
                 cells[i][j]=Cell.getCell(i, j);
            }
    }
    /**
     * Metoda prin care se instantiaza clasa
     * @return o instanta a clasei
     */
    public static Game getGame(){
        game = new Game();
        return game;
    }
    /**
     * Metoda prin care se deseneaza celulele pe harta
     * @param g 
     */
    public void Draw(Graphics g){
        for (int i=0;i<width;i++)
            for (int j=0;j<height;j++){
                 cells[i][j].drawCell(g);
            }
        g.setFont(new Font("SansSerif", Font.BOLD, 25));
        g.setColor(Color.red);
        g.drawString("Generation number:"+nrGeneration, 10, 10+g.getFont().getSize());
    }
    
   /**
    * Metoda realizeaza un update al jocului. Se numara vecinii vii ai fiecarei celule si, in functie
    * de numarul acestora se actualizeaza starea celulei.
    * Dacă noua configuratie a jocului este aceeași cu cea de dinainte înseamnă că s-a ajuns 
    * la o generație de tip block și jocul se oprește
    */
    public void update(){
        if (go==0){ //daca jocul este in derulare 
           Cell[][] copycells = new Cell[width][height]; //se retine configuratia initiala a celulelor
           for (int i=0;i<width;i++)
                for (int j=0;j<height;j++){
                    copycells[i][j]=Cell.getCell(i,j);
                    copycells[i][j].alive=cells[i][j].alive;
                }
            
            nrGeneration++; //se incrementeaza numarul de generatii
            for (int i=0;i<width;i++) // se numara vecinii in functie de pozitia celulei si modul de joc
                for (int j=0;j<height;j++){
                    int neighbourCount =0;
                    if(border==true){ //daca jocul este "cu granita"
                        int ax=i-1;
                        int ay=j-1;
                        int bx=i+1;
                        int by=j+1;
                        if(ax>=0&&bx<width&&ay>=0&&by<height){ //daca celula se afla in centrul hartii
                             if (cells[ax][ay].isAlive())
                                 neighbourCount++;
                             if (cells[ax][by].isAlive())
                                 neighbourCount++;
                             if (cells[ax][j].isAlive())
                                 neighbourCount++;
                             if (cells[bx][by].isAlive())
                                 neighbourCount++;
                             if (cells[bx][j].isAlive())
                                 neighbourCount++;
                             if (cells[bx][ay].isAlive())
                                 neighbourCount++;
                             if (cells[i][ay].isAlive())
                                 neighbourCount++;
                             if (cells[i][by].isAlive())
                                 neighbourCount++;
                        }
                        if(i==0){ //daca celula se afla pe marginea din stanga
                            if (j==0){ //daca celula se afla in coltul din stanga sus
                                if(cells[i][by].isAlive())
                                    neighbourCount++;
                                if(cells[bx][by].isAlive())
                                    neighbourCount++;
                                if(cells[bx][j].isAlive())
                                    neighbourCount++;
                            }
                            if(j==height-1){ //daca celula se afla in coltul din stanga jos
                                if(cells[i][ay].isAlive())
                                    neighbourCount++;
                                if(cells[bx][ay].isAlive())
                                    neighbourCount++;
                                if(cells[bx][j].isAlive())
                                    neighbourCount++;
                            }
                            if (j>0&&j<height-1){
                                if(cells[bx][j].isAlive())
                                    neighbourCount++;
                                if(cells[i][ay].isAlive())
                                    neighbourCount++;
                                if(cells[i][by].isAlive())
                                    neighbourCount++;
                                if(cells[bx][by].isAlive())
                                    neighbourCount++;
                                if(cells[bx][ay].isAlive())
                                    neighbourCount++;
                            }
                        }
                        if(i==width-1){ //daca celula se afla pe marginea din dreapta
                            if (j==0){ //daca celula se afla in coltul din dreapta sus
                                if(cells[i][by].isAlive())
                                    neighbourCount++;
                                if(cells[ax][by].isAlive())
                                    neighbourCount++;
                                if(cells[ax][j].isAlive())
                                    neighbourCount++;
                            }
                            if(j==height-1){ //daca celula se afla in coltul din dreapta jos
                                if(cells[i][ay].isAlive())
                                    neighbourCount++;
                                if(cells[ax][ay].isAlive())
                                    neighbourCount++;
                                if(cells[ax][j].isAlive())
                                    neighbourCount++;
                            }
                            if (j>0&&j<height-1){
                                if(cells[ax][j].isAlive())
                                    neighbourCount++;
                                if(cells[i][ay].isAlive())
                                    neighbourCount++;
                                if(cells[i][by].isAlive())
                                    neighbourCount++;
                                if(cells[ax][by].isAlive())
                                    neighbourCount++;
                                if(cells[ax][ay].isAlive())
                                    neighbourCount++;
                            }
                        } 
                        if(i>0&&i<width-1){ 
                            if(j==0){ //daca celula se afla pe marginea superioara
                                if(cells[ax][j].isAlive())
                                    neighbourCount++;
                                if(cells[ax][by].isAlive())
                                    neighbourCount++;
                                if(cells[bx][j].isAlive())
                                    neighbourCount++;
                                if(cells[i][by].isAlive())
                                    neighbourCount++;
                                if(cells[bx][by].isAlive())
                                    neighbourCount++;
                            }

                            if(j==height-1){ //daca celula se afla pe marginea inferioara
                                 if(cells[ax][j].isAlive())
                                    neighbourCount++;
                                if(cells[ax][ay].isAlive())
                                    neighbourCount++;
                                if(cells[bx][j].isAlive())
                                    neighbourCount++;
                                if(cells[i][ay].isAlive())
                                    neighbourCount++;
                                if(cells[bx][ay].isAlive())
                                    neighbourCount++;
                            }

                        }

                    }

                    else{ //daca jocul se desfasoara "fara granita"
                        int ax=i-1;
                        int ay=j-1;
                        int bx=i+1;
                        int by=j+1;
                        if(ax<0)
                            ax=width-1;
                        if(ay<0)
                            ay=height-1;
                        if(bx>width-1)
                            bx=0;
                        if(by>height-1)
                            by=0;
                        if (cells[ax][ay].isAlive())
                                 neighbourCount++;
                        if (cells[ax][by].isAlive())
                             neighbourCount++;
                        if (cells[ax][j].isAlive())
                             neighbourCount++;
                        if (cells[bx][by].isAlive())
                             neighbourCount++;
                        if (cells[bx][j].isAlive())
                             neighbourCount++;
                        if (cells[bx][ay].isAlive())
                             neighbourCount++;
                        if (cells[i][ay].isAlive())
                             neighbourCount++;
                        if (cells[i][by].isAlive())
                             neighbourCount++;
                      
                    }
                     if (neighbourCount <2 || neighbourCount>3) //se verifica regulile jocului
                         cells[i][j].setNextRound(false);
                     if (neighbourCount==3)
                             cells[i][j].setNextRound(true); 
                     if(neighbourCount==2&&cells[i][j].isAlive()==true)
                         cells[i][j].setNextRound(true);
                }
            for (int i=0;i<width;i++) //se updateaza starile celulelor
                for (int j=0;j<height;j++)
                    cells[i][j].nextRound();
            
           int ok=1; //se verifica daca cele 2 generatii consecutive sunt la fel
            for (int i=0;i<width;i++)
                for (int j=0;j<height;j++)
                    if(copycells[i][j].alive!=cells[i][j].nextRound)
                        ok=0;
            if (ok==1) //in caz afirmativ, jocul se termina
                go=1; 
        }
    }
    
/**
 * Metoda permite stabilirea configuratiei jocului. Prin apasarea mouseului pe o celula aceasta isi va modifica starea
 * @param e 
 */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(go==-1){
            
            int mx=e.getX()/Cell.size;
            int my=(e.getY()/Cell.size)-1;
            if (e.getY()<Cell.size+20)
                my=0;
            if (my<0)
                my=0;
            if(button==1)
                cells[mx][my].setAlive(true);
              else
                cells[mx][my].setAlive(false);
            
        }
    }
/**
 * Metoda permite stabilirea configuratiei jocului. Prin apasarea mouseului pe o celula aceasta isi va modifica starea
 * Acest lucru se intampla doar daca jocul este pe pauza
 * @param e 
 */
    @Override
    public void mouseMoved(MouseEvent e) {
        if(go==-1){
            int mx=e.getX()/Cell.size; //coordonatele celulei pe care s-a dat click
            int my=(e.getY()/Cell.size)-1;
            if (e.getY()<Cell.size+20)
                my=0;
            if(button==1)
                    cells[mx][my].setAlive(true);
                else
                    if(button!=-1)
                        cells[mx][my].setAlive(false);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }
/**
 * Metoda permite punerea pe pauza a jocului prin apasarea tastei SPACE.
 * Pornirea jocului se face tot prin apasarea tastei SPACE
 * @param e 
 */
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE);
            if(go==-1)
                go=0;
            else
                go=-1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }
/**
 * Metoda permite stabilirea configuratiei jocului. Prin apasarea mouseului pe o celula aceasta isi va modifica starea
 * @param e 
 */
    @Override
    public void mousePressed(MouseEvent e) {
        button=e.getButton();
        if(go==-1){
            
            int mx=e.getX()/Cell.size;
            int my=(e.getY()/Cell.size)-1;
            if (e.getY()<Cell.size+20)
                my=0;
            if(button==1){
                if(cells[mx][my].isAlive()==false)
                    cells[mx][my].setAlive(true);
                else
                cells[mx][my].setAlive(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button=-1;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
