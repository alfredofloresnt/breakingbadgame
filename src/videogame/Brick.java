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
 * @author rober
 */
public class Brick extends Item{
    private int width;
    private int height;
    private Game g;
    private Ball ball;
    private int id;
    
   
    public Brick(int x, int y, int width, int height,int id,Ball ball, Game g) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.g = g;
        this.id = id;
        this.ball = ball;
    }
    
    public int getHeight() {
        return height;
    }
   
    public int getWidth() {
        return width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
   
    public void setWidth(int width) {
        this.width = width;
    }
    
    
   
    @Override
    public void tick() {   
        if (intersecta(ball)){
            g.eliminar(id);
            ball.setYspeed();
        }
        
    }
   
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public boolean intersecta(Object obj) {
        return obj instanceof Ball && getPerimetro().intersects(((Ball) obj).getPerimetro());
    }
    
    /**
     * Renders the player
     * @param g 
     */
    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.brick,getX(), getY(), getWidth(), getHeight(), null);
    }

    
}