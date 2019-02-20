/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Alfredo Flores
 */
public class Player extends Item{

    private int direction; 
    private int width;
    private int height;
    private int speed;
    private Game game;
    private boolean collision;
    private boolean isVertical;
    private int acceleration;
    private int timer;
    private int timerDelay=50;
    private Asteroid sun;
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
        this.direction = direction;
        this.isVertical = false;
        this.width = width;
        this.height = height;
        this.speed = 1;
        this.game = game;

    }

    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed =  speed;
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
        if (game.getMouseManager().isIzquierdo() && getX()+100>game.getMouseManager().getX() && getX()-100<game.getMouseManager().getX() && getY()+100>game.getMouseManager().getY() && getY()-100<game.getMouseManager().getY()){
            setX(game.getMouseManager().getX());
            setY(game.getMouseManager().getY());
        }

        // reset x position and y position if colision and change directionof the player
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
            collision = true;
            direction *=-1;
        }
        else if (getX() <= -30) {
            setX(-30);
            collision = true;
            direction *=-1;
            
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            collision = true;
            direction *=-1;
        }
        else if (getY() <= -20) {
            setY(-20);
            collision = true;
            direction *=-1;
        }
        
    }
    
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersecta(Object obj) {
        return obj instanceof Asteroid && getPerimetro().intersects(((Asteroid) obj).getPerimetro());
    }
    
    
    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);     
    }
}
