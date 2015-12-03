/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gol;

/**
 *
 * @author Andreea
 */
public enum Dimensions {
    WIDTH(400), HEIGHT(300);
    int value;
    
    private Dimensions(int value){
        this.value=value;
    }
    
}
