package com.app.main;

import java.awt.*;
import java.util.Random;

public class Potion extends GameObject{

    Handler handler = new Handler();
    Random r = new Random();

    private int width = 500;
    private int height = 500;

    public Potion(){

    }

    public Potion(int x, int y, ID id){
        super (x,y,id);

    }

    public void tick(){


    }

    public void render(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval((int)x,(int)y,15,15);
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,15,15);
    }
}
