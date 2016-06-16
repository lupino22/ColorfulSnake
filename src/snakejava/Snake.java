/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakejava;

//import java.util.List;

/**
 *
 * @author lupino
 */
import dataStructures.ArrayLinearList;
import java.awt.Color;
public class Snake {
    private ArrayLinearList bodyX;
    private ArrayLinearList bodyY;
    private ArrayLinearList eatenFoodX;
    private ArrayLinearList eatenFoodY;
    private ArrayLinearList colorList;
    private ArrayLinearList eatenColorList;
    private int currentDirection = 2;
    private int speed = 8;
    private Color clr = Color.RED;
    
    private int size;
    public Snake(int headX, int headY, int size){
        this.bodyX = new ArrayLinearList();
        this.bodyY = new ArrayLinearList();
        this.eatenFoodX = new ArrayLinearList();
        this.eatenFoodY = new ArrayLinearList();
        this.colorList = new ArrayLinearList();
        this.eatenColorList = new ArrayLinearList();
        this.size = size;
        for(int i = 0; i <= size; i++){
            bodyX.add(bodyX.size(), headX);
            bodyY.add(bodyY.size(), headY-i);
            colorList.add(colorList.size(), clr);
        }
    }
    public void moveUp(){
        if(this.currentDirection != 2){
            this.currentDirection = 1;
        }
    }
    public void moveDown(){
        if(this.currentDirection != 1){
            this.currentDirection = 2;
        }
    }
    public void moveRight(){
        if(this.currentDirection != 4){
            this.currentDirection = 3;
        }
    }
    public void moveLeft(){
        if(this.currentDirection != 3){
            this.currentDirection = 4;
        }
    }
    
    public Color getColor(int x){
        return (Color) this.colorList.get(x);
    }
    
    public void move(){
        int temp = 0,temp2 = 0;
        int l_temp = (int)this.bodyY.get(bodyY.size()-1), l_temp2 = (int)this.bodyX.get(bodyX.size()-1);
        boolean temp3 = this.isGrowth();
        switch(this.currentDirection){
            case 1: //up
                temp = (int) this.bodyY.get(0);
                temp2 = (int) this.bodyX.get(0);
                temp--;
                this.bodyY.remove(this.bodyY.size()-1);
                this.bodyX.remove(this.bodyX.size()-1);
                this.bodyY.add(0, temp);
                this.bodyX.add(0, temp2);
                break;
            case 2: //down
                temp = (int) this.bodyY.get(0);
                temp2 = (int) this.bodyX.get(0);
                temp++;
                this.bodyY.remove(this.bodyY.size()-1);
                this.bodyX.remove(this.bodyX.size()-1);
                this.bodyY.add(0, temp);
                this.bodyX.add(0, temp2);
                break;
            case 3: //right
                temp = (int) this.bodyY.get(0);
                temp2 = (int) this.bodyX.get(0);
                temp2++;
                this.bodyY.remove(this.bodyY.size()-1);
                this.bodyX.remove(this.bodyX.size()-1);
                this.bodyY.add(0, temp);
                this.bodyX.add(0, temp2);
                break;
            case 4: //left
                temp = (int) this.bodyY.get(0);
                temp2 = (int) this.bodyX.get(0);
                temp2--;
                this.bodyY.remove(this.bodyY.size()-1);
                this.bodyX.remove(this.bodyX.size()-1);
                this.bodyY.add(0, temp);
                this.bodyX.add(0, temp2);
                break;
        }
        if(temp3){
            this.bodyY.add(this.bodyY.size(), l_temp);
            this.bodyX.add(this.bodyX.size(), l_temp2);
            this.colorList.add(this.colorList.size(), eatenColorList.get(eatenColorList.size()-1));
            eatenColorList.remove(eatenColorList.size()-1);
        }
    }
    
    public boolean isEatingFood(Food food){
        if((int)this.bodyX.get(0) == food.getCoordinatX() && (int)this.bodyY.get(0) == food.getCoordinatY()){
            this.eat(food);
            food.reset(this);
            return true;
        }
        return false;
    }
    public boolean isConflict(){
        for(int i = 1; i < this.bodyX.size(); i++){
            if(this.bodyX.get(i) == this.bodyX.get(0) && 
                    this.bodyY.get(i) == this.bodyY.get(0)){
                return true;
            }
        }
        return false;
    }
    private boolean isGrowth(){
        if(eatenFoodX.size() != 0){
            if(this.bodyX.get(bodyX.size()-1) == this.eatenFoodX.get(eatenFoodX.size()-1) && 
                    this.bodyY.get(bodyY.size()-1) == this.eatenFoodY.get(eatenFoodY.size()-1)){
                eatenFoodX.remove(eatenFoodX.size()-1);
                eatenFoodY.remove(eatenFoodY.size()-1);
                return true;
            }
        }
        return false;
    }
    private void eat(Food food){
        this.eatenFoodX.add(0, food.getCoordinatX());
        this.eatenFoodY.add(0, food.getCoordinatY());
        eatenColorList.add(0, food.getColor());
    }
    public boolean isAvailable(int x, int y){
        for(int i = 0; i < this.bodyX.size(); i++){
            if((int)this.bodyX.get(i) == x && (int)this.bodyY.get(i) == y){
                return false;
            }
        }
        return true;
    }
    public int snakeSize(){
        return this.size;
    }
    public void speedUp(){
        this.speed++;
    }
    public void speedDown(){
        this.speed--;
    }
    public int getSpeed(){
        return this.speed;
    }
    public ArrayLinearList getBodyX(){
        return this.bodyX;
    }
    public ArrayLinearList getBodyY(){
        return this.bodyY;
    }
}
