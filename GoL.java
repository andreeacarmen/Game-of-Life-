/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

import static gol.Dimensions.HEIGHT;
import static gol.Dimensions.WIDTH;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Andreea
 */
public class GoL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
        Frame frame = Frame.setFrame("Game of life"); //se initializeaza frame-ul 
        WIDTH.value = frame.getWidth();
        HEIGHT.value=frame.getHeight();
        frame.createScreen(WIDTH.value, HEIGHT.value); 
        
        long lastFrame=System.currentTimeMillis();
        while (true){
            long currentFrame=System.currentTimeMillis();
            float dt = (float) ((currentFrame-lastFrame)/1000.0);
           
            lastFrame=currentFrame;
            
            frame.update(dt);
            frame.repaint();
            
            if (frame.game.go ==1){
                JOptionPane.showMessageDialog(frame, "Generații block");
                break;
            }
            try{
                Thread.sleep(10);
            } catch(InterruptedException e){
//                e.printStackTrace();
            }
        }
        System.exit(0);
    }
    
}
