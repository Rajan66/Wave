package com.app.main;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;

    public float xBoundary;
    public float yBoundary;

    Random r = new Random();

    public Spawn(){

    }

    public Spawn(Handler handler,HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public int xSpawnProtector(){
        xBoundary = r.nextInt(Game.WIDTH);
        xBoundary = Game.clamp(xBoundary,30,300);

        return (int)xBoundary;
    }

    public int ySpawnProtector(){
        yBoundary = r.nextInt(Game.HEIGHT);
        yBoundary = Game.clamp(yBoundary,30,300);

        return (int) yBoundary;
    }

}
