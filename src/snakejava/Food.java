/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakejava;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author lupino
 */
public class Food {
    
    private int coordinatX;
    private int coordinatY;
    private Color clr;
    private Random rand = new Random();
    
    private int rot_level = 200;
    
    public int getCoordinatX() {
        return coordinatX;
    }

    public void setCoordinatX(int coordinatX) {
        this.coordinatX = coordinatX;
    }

    public int getCoordinatY() {
        return coordinatY;
    }

    public void setCoordinatY(int coordinatY) {
        this.coordinatY = coordinatY;
    }
    
    public Food(Snake snk){
        this.reset(snk);
    }
    
    public void reset(Snake snake){
        while(true){
            int tempX = rand.nextInt(70);
            int tempY = rand.nextInt(60);
            if(snake.isAvailable(tempX, tempY)){
                coordinatX = tempX;
                coordinatY = tempY;
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                this.clr = new Color(r,g,b);
                break;
            }
        }
    }
    
    public void resetRot(){
        this.rot_level = 200;
    }
    
    public Color getColor(){
        return this.clr;
    }
    
    public void desease(){
        this.rot_level--;
    }
    
    public int getRotLvl(){
        return this.rot_level;
    }
}
