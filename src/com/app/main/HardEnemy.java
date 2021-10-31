package com.app.main;

import java.awt.*;
import java.util.Random;

public class HardEnemy extends GameObject{

    private Handler handler;
    Random r = new Random();

    public HardEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        velX = 10;
        velY = 10;
        this.handler = handler;
    }

    public void tick(){
        x += velX;
        y += velY;

        if( x <= 0 || x >= Game.WIDTH - 32){
            velX *= -1;
        }
        if( y <= 0 || y >= Game.HEIGHT - 52){
            velY *= -1;
        }

        handler.addObject(new Trail(x,y,ID.Trail,Color.YELLOW,16,16,0.05f,handler));
    }

    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,16,16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
}
