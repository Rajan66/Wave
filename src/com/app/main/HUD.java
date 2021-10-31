package com.app.main;

import java.awt.*;

public class HUD {

    public static float HEALTH = 100;
    private float greenValue = 255;

    public int score = 0;
    public int level = 1;

    public void tick(){

        HEALTH = Game.clamp(HEALTH,0,100);

        greenValue = Game.clamp(greenValue,0,255);

        greenValue = HEALTH * 2; // basically when health decreases the greenValue also decreases hence the color change.
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(10,10,200,15);
        g.setColor(new Color(155,(int)greenValue,0));
        g.fillRect(10,10,(int)HEALTH * 2, 15);
        g.setColor(Color.white);
        g.drawRect(10,10,200,15);

        g.drawString("Score: " + getScore(),20,40);
        g.drawString("Level: " + level,20,52);
    }

    public  void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public  void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }



}
