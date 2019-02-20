/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;


public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage sun; 
    public static BufferedImage playerCollide; 
    public static BufferedImage live;
    public static BufferedImage gameOver;
    public static SoundClip punch;

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        sun = ImageLoader.loadImage("/images/sun.png");
        player = ImageLoader.loadImage("/images/planet.png");
        playerCollide = ImageLoader.loadImage("/images/playerCollide.png");
        live = ImageLoader.loadImage("/images/live.png");
        gameOver = ImageLoader.loadImage("/images/gameover.png");
        punch = new SoundClip("/sounds/punch.wav");
    }
    
}
