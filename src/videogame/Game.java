/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Ball ball;
    private KeyManager keyManager;  // to manage the keyboard
    private ArrayList<Brick> bricks;
    private int cols = 4;
    private int rows = 16;
    private String[] arr;
    private Vector vec;
    
    
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
        running = false;
        keyManager = new KeyManager();
        bricks = new ArrayList<Brick>();
    }
    
    
    
    public void leerArchivo() throws IOException {
                                                          
                BufferedReader fileIn;
                try {
                        fileIn = new BufferedReader(new FileReader("C://Users//AlfredoFlores//Documents//breakingbadgame//src//videogame//prueba.txt"));
                } catch (FileNotFoundException e){
                        File puntos = new File("C://Users//AlfredoFlores//Documents//breakingbadgame//src//videogame//prueba.txt");
                        PrintWriter fileOut = new PrintWriter(puntos);
                        fileOut.println("100,demo");
                        fileOut.close();
                        fileIn = new BufferedReader(new FileReader("C://Users//AlfredoFlores//Documents//breakingbadgame//src//videogame//prueba.txt"));
                }
                String dato = fileIn.readLine();
                while(dato != null) {  
                                                        
                      arr = dato.split(",");
                      int num = (Integer.parseInt(arr[0]));
                      String nom = arr[1];
                      vec.add(new Puntaje(0,0,num, nom, this));
                      dato = fileIn.readLine();
                }
                fileIn.close();
        }
    public void grabaArchivo() throws IOException {
                                                          
                PrintWriter fileOut = new PrintWriter(new FileWriter("C://Users//AlfredoFlores//Documents//breakingbadgame//src//videogame//prueba.txt"));
                for (int i = 0; i < vec.size(); i++) {

                    Puntaje x;
                    x = (Puntaje) vec.get(i);
                    fileOut.println(x.toString());
                }
                fileOut.close();
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
    
   
    
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        vec = new Vector();
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(getWidth()/2, getHeight()-30, 1, 100, 15,  this);
         ball = new Ball(300,300,64,32,player,this);
         display.getJframe().addKeyListener(keyManager);
         int cont = 0;
         for(int i = 0 ; i < rows; i++){
             for (int j = 0 ; j < cols; j++){
                 cont++;
                 bricks.add(new Brick(5+i*60, 5+j*60, 100, 100, cont, ball, this));
             }
         }
         
    }
    
    void eliminar(int id){
        bricks.remove(0);
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
        try{
            leerArchivo();
        } catch (IOException ex) {
             System.out.println("Error en " + ex.toString());
        }
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
    
      
    
    private void tick() {
        //leerArchivo();
        keyManager.tick();
        // avancing player with colision
        player.tick();
        ball.tick();
        for(int i = 0; i < bricks.size(); i++){
            bricks.get(i).tick();
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
            ball.render(g);
            for(int i = 0; i < bricks.size(); i++){
            bricks.get(i).render(g);
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
