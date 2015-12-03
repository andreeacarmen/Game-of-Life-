/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.lang.String;
import javax.swing.JOptionPane;
/**
 *Clasa Frame extinde clasa JFrame si construieste fereastra in care se va desfasura jocul
 * @author Andreea
 */
public class Frame extends JFrame{
    private static Frame f;
    private Screen screen;
    public Game game;
    private float UPDATETIME=0.5f; //timpul după care se va face update la frame
    private float tslu; //timpul de la ultimul update
    
    private Frame(String s){
         super(s);
         setExtendedState(MAXIMIZED_BOTH); 
         String input = JOptionPane.showInputDialog(this,"Enter cell size"); //fereastra de input pentru stabilirea 
                                                                              //dimensiunii celulei
         String input2 = JOptionPane.showInputDialog(this,"With border? yes or no"); //ferestra de input pentru stabilirea
         try{                                                                          //modului de joc 
             if(input2.equals("yes")){
                Game.border=true;
                }
             if(input2.equals("no"))
                Game.border=false;
         }
         catch (Exception e){
             System.out.println("Invalid option");
             System.exit(0);
         }
         try{
         Cell.size=Integer.parseInt(input);
         }
         catch(Exception e){
             System.out.println("Invalid size");
             System.exit(0);
         }
         
    }
    
    /**
     * Constructorul initializeaza o fereastra in care se va simula Game of Life
     * @param s string ce reprezinta numele ferestrei
     * @return 
     */
    public static Frame setFrame(String s){
        f=new Frame(s);
        
     //   f.setSize(width.value, height.value);
        f.setVisible(true);
        f.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);
        return f;
    }
    
    public void createScreen(int width,int height){
        screen=new Screen();
        game = Game.getGame();
        addMouseListener(game);
        addMouseMotionListener(game);
        addKeyListener(game);
        screen.setBounds(0,0,width,height);
        add(screen);
    }
    
    @Override
    public void repaint(){
        screen.repaint();
    }
    
    /**
     * Metoda trece la frame-ul urmator. Jocul se updateaza daca timpul de la realizarea ultimului update 
     * este mai mare decat UPDATETIME
     * @param dt - timpul dintre 2 frame-uri consecutive
     */
    public void update(float dt){
        tslu+=dt;
        if(tslu>UPDATETIME){
            game.update();
            tslu=0;
        }
    }
    
    private  class Screen extends JLabel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
           game.Draw(g);
        }
    }
    
   
       
}
