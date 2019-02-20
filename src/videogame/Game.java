/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private Asteroid sun;
    private KeyManager keyManager;  // to manage the keyboard
    private MouseManager mouseManager;
    private LinkedList<Lives> lives; // Player Lives
    private int livesCount;
    private boolean gameOver;
    int randomNum;
    
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     * @param livesCount  to set the player lives
     */
    public Game(String title, int width, int height,int livesCount) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.livesCount = livesCount;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        lives = new LinkedList<Lives>();
        this.gameOver = false;
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    
    public int getRandomNumber(int min, int max){
        return randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(getRandomNumber(0, getWidth()), getRandomNumber(0, getHeight())-100, 1, 100, 100,  this);
         sun = new Asteroid(getRandomNumber(0, getWidth()), getRandomNumber(0, getHeight()), 100, 100, player, this);
         for (int i = 0; i < livesCount; i++){
             lives.add(new Lives(50*i,0,50,50,this));
         }
         display.getJframe().addKeyListener(keyManager);
         display.getJframe().addMouseListener(mouseManager);
         display.getJframe().addMouseMotionListener(mouseManager);
         display.getCanvas().addMouseListener(mouseManager);
         display.getCanvas().addMouseMotionListener(mouseManager);
    }
    
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 60;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    public MouseManager getMouseManager() {
        return mouseManager;
    }    
    
    private void tick() {
        if (lives.size()>0){
        keyManager.tick();
        // avancing player with colision
        player.tick();
        sun.tick();
        for (int i = 0; i < lives.size(); i++){
            Lives live = lives.get(i);
            live.tick();
        }
        if (player.intersecta(sun)){
            Assets.punch.play();
            lives.removeLast();
            player.setX(getRandomNumber(0,width));
            player.setY(getRandomNumber(0,height));
            sun.setX(getRandomNumber(0,width-100));
            sun.setY(getRandomNumber(0,height+100));
            sun.incrementSpeed(1);
        }
        }
        else{
            gameOver = true;
        }
    }
    
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            sun.render(g);
            for (int i = 0; i < lives.size(); i++){
                Lives live = lives.get(i);
                live.render(g);
            }
            if (gameOver){
                g.drawImage(Assets.gameOver, width/2-150, height/2-100, 300, 200, null);
            }
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }

 
    


}
