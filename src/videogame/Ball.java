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
public class Ball extends Item {

    private int width;
    private int height;
    private int xSpeed;
    private int ySpeed;
    private Game game;

    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.xSpeed = 1;
        this.ySpeed = 1;
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
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);

        if (getX() < 0) {
            setX(0);
            xSpeed *= -1;
        } else if (getX() > game.getWidth() - getWidth()) {
            setX(game.getWidth() - getWidth());
            xSpeed *= -1;
        }

        if (getY() < 0) {
            ySpeed *= -1;
        } else if (getY() > game.getHeight() - getHeight()) {
            ySpeed *= -1;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }

}
