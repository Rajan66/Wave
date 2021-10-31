package com.app.main;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject{

    private Handler handler;

    Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler){
        super(x,y,id);
        velX = (r.nextInt(5 - -5) + (-5));
        velY = 5;
        this.handler = handler;
    }

    public void tick(){
        x += velX;
        y += velY;

//        if( x <= 0 || x >= Game.WIDTH - 32){
//            velX *= -1;
//        }
//        if( y <= 0 || y >= Game.HEIGHT - 52){
//            velY *= -1;
//        }
        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail(x,y,ID.Trail,Color.LIGHT_GRAY,16,16,0.05f,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
}
