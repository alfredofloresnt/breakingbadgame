/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author AlfredoFlores
 */

public class MapGen extends Item {
private int cols;
private int rows;
private LinkedList<Barrel> barrels;
    public MapGen(int x, int y,int cols,int rows) {
        super(x, y);
        this.cols = cols;
        this.rows = rows;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
