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
 * @author AlfredoFlores
 */
public class Asteroid extends Item{
    private int width;
    private int height;
    private int speed = 1;
    private Game game;
    private Player player;
    private boolean collisionWithPlayer = false; 

    public Asteroid(int x, int y, int width, int height,Player player,Game game) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
    }
    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Player getPlayer(){
        return player;
    }
    
    public void incrementSpeed(int x){
        speed += x;
    }
   
    @Override
    public void tick() {
        ///Follow player position
        if (getPlayer().getX()>getX()){
            setX(getX()+speed);
        } else if (getPlayer().getX()<getX()){
            setX(getX()-speed);
        }
        if (getPlayer().getY()>getY()){
            setY(getY()+speed);
        } else if (getPlayer().getY()<getY()){
            setY(getY()-speed);
        }
    }
    
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }


    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.sun, getX(), getY(), getWidth(), getHeight(), null);       
    }
    
}
