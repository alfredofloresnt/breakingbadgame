/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author antoniomejorado
 */
public class MouseManager  implements MouseListener, MouseMotionListener {
    private boolean izquierdo;          // to check if left has been pushed
    private boolean derecho;            // to check if right has been pushed
    private boolean drag;              // to check if mouse is being dragged
    private int x;                      // to get x position of the mouse
    private int y;                      // to get y position of the mouse
    private int h;
    public MouseManager() {
        izquierdo = false;
        derecho = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIzquierdo() {
        return izquierdo;
    }

    public boolean isDerecho() {
        return derecho;
    }
    public boolean isDragged() {
        return drag;
    }

    public void setIzquierdo(boolean izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
        izquierdo = true;
        x = e.getX();
        y = e.getY();
        }
    }

    
    
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (izquierdo){
            x = e.getX();
            y = e.getY();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
            
            
        
    }
}
