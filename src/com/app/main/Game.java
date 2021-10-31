package com.app.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    @Serial
    private static final long serialVersionUID = 5241351866166797886L;

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH /12 *9;

    private Thread thread;
    private boolean isRunning = false;
    public static boolean paused = false;
    public static int difficulty = 0; //0 - normal, 1 - hard

    private Handler handler;

    private Random r;
    private HUD hud;
    private Spawn spawner;
    private Player player;

    private Menu menu;

    public enum STATE{
        Menu,
        Help,
        Game,
        Select,
        Paused,
        End;
    }

    public static STATE gameState = STATE.Menu;

    public Game(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu( handler,hud);
        r = new Random();

        this.addMouseListener(menu);
        this.addKeyListener(new KeyInput(handler,this));

        AudioPlayer.load();
        AudioPlayer.getMusic("music").loop();
        AudioPlayer.music.setVolume(0.03f);

        new Window(WIDTH,HEIGHT,"Pixel Game", this);

        spawner = new Spawn(handler, hud);

//        if(gameState == STATE.Game){
//            handler.clearEnemies();
//            handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
//
//            handler.addObject(new BasicEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
//
//            handler.addObject(new Potion(124, 366, ID.Potion));
//        }


        if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            for (int i = 0; i < 15; i++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT) , ID.MenuParticle, handler));
            }
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public synchronized void stop()
    {
        try{
            thread.join();//kills off the thread.
            isRunning = false;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(isRunning)
                render();
            frames++;
            try {
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    //System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        stop();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }
        else if (var <= min){
            return var = min;
        }
        else
            return var;
    }

    public void tick(){
        if(gameState == STATE.Game) {
            if (!paused) {
                handler.tick();
                hud.tick();

                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.object.clear();
                    for (int i = 0; i < 15; i++) {
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                    }
                }
            }
            else{
                gameState = STATE.Paused;
            }
        }
        else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            handler.tick();
            menu.tick();
        }



    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        }
        else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Paused){
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game(); //starting the window
    }
}
