package com.app.main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject{

    private Handler handler;

    private int timer = 80;
    private int timer2 = 50;
    Random r = new Random();

    public EnemyBoss(int x, int y, ID id, Handler handler){
        super(x,y,id);
        velX = 0;
        velY = 2;
        this.handler = handler;
    }

    public void tick(){
        x += velX;
        y += velY;

        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <=0) timer2--;

        if(timer2 <= 0){
            if(velX == 0) velX += 3;
            if(velX > 0) velX += 0.004f;
            else if (velX <0)   velX -= 0.005f;

            velX = Game.clamp(velX,-10,10);

            int spawn = r.nextInt(10);
            if(spawn == 0) handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48,ID.BasicEnemy,handler));
        }

        if(x <=0 || x >= (Game.WIDTH - 106)) velX *= -1;

//        handler.addObject(new Trail(x,y,ID.EnemyBoss,Color.red,96,96,0.05f,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,96,96);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,96,96);
    }
}
