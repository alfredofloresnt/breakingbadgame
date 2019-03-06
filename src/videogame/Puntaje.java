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
public class Puntaje extends Item{
    private int val;
    private String nombre;
    public Puntaje(int x, int y,int val,String nombre,Game g) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.val = val;
        this.nombre = nombre;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawString(nombre, x, y);
    }
    
    
}
