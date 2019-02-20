/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author AlfredoFlores
 */
public class Lives extends Item{
    private int width;
    private int height;
    private Game game;
    public Lives(int x, int y, int width, int height,Game game) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    @Override
    public void tick() {
      
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.live, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}