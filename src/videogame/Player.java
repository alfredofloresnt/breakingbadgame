/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Alfredo Flores
 */
public class Player extends Item{
    private int width;
    private int height;
    private Game game;
    private int speed;
    /**
     * 
     * @param x player pos at x
     * @param y player pos at y
     * @param direction player direction -1 or 1
     * @param width width the player
     * @param height heigth of the player
     * @param game game object
     */
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 1;
    }

    

    public int getWidth() {
        return width;
    }

    
    
    public int getHeight() {
        return height;
    }    

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().left){
            setX(getX()-speed);
        }
        if (game.getKeyManager().right){
            setX(getX()+speed);
        }
        if (getX()<0){
            setX(0);
        }
        else if (getX()>game.getWidth()-100){
            setX(game.getWidth()-100);
        }
       

        
        
    }
    
   
    
    
    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
            g.setColor(Color.blue);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
