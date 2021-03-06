package com.app.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject{

    private Handler handler;

    Color color;
    Random r = new Random();

    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x,y,id);

        this.handler = handler;

        velX = (r.nextInt(7 - -7) + (-7));
        velY = (r.nextInt(7 - -7) + (-7));

        if(velX == 0) velX = 4;
        if(velY == 0) velY = 4;

        color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
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

        handler.addObject(new Trail(x,y,ID.Trail,color,16,16,0.05f,handler));
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
}
