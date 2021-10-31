package com.app.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Handler handler;
    private HUD hud;

    Random r = new Random();

    public Menu(Handler handler, HUD hud) {

        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();

        if (Game.gameState == Game.STATE.Menu) {
            //Play Button
            if (mouseOver(mX, mY, 210, 130, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Select;
                return;
                //A return statement stops the execution of the method right there, and returns its value.
                //Hence, the compiler will raise an error if there is code below return.

            }
            //Help Button
            if (mouseOver(mX, mY, 210, 230, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Help;
            }
            //Quit Button
            if (mouseOver(mX, mY, 210, 330, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                System.exit(1);
            }
        }

        if (Game.gameState == Game.STATE.Select) {
            //Normal Button
            if (mouseOver(mX, mY, 210, 130, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();

                Game.gameState = Game.STATE.Game;

                handler.clearEnemies();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));

                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));

                handler.addObject(new Potion(124, 366, ID.Potion));

                Game.difficulty = 0;
            }
            //Insane Button
            if (mouseOver(mX, mY, 210, 230, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Game;

                handler.clearEnemies();
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));

                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));

                handler.addObject(new Potion(124, 366, ID.Potion));

                Game.difficulty = 1;
            }
            if (mouseOver(mX, mY, 210, 330, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Menu;
            }
        }
        //Back Button for Help
        if(Game.gameState ==Game.STATE.Help) {
            if (mouseOver(mX, mY, 210, 330, 180, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Menu;
                return;
                //A return statement stops the execution of the method right there, and returns its value.
                //Hence, the compiler will raise an error if there is code below return.
            }
        }
        if(Game.gameState ==Game.STATE.End){
            //Menu Button
            if (mouseOver(mX, mY, 230, 230, 150, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Menu;

                hud.setLevel(1);
                hud.setScore(0);
            }
        //Try Again Button
            if (mouseOver(mX, mY, 230, 330, 150, 64)) {
                AudioPlayer.getSound("menu_sound").play();
                Game.gameState = Game.STATE.Game;

                hud.setLevel(1);
                hud.setScore(0);
                handler.clearEnemies();

                if(Game.difficulty == 0) {
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
                    handler.addObject(new BasicEnemy((r.nextInt(Game.WIDTH)), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                    handler.addObject(new Potion(124, 366, ID.Potion));

                    Game.difficulty = 0;
                }
                else if (Game.difficulty == 1){
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
                    handler.addObject(new Potion(124, 366, ID.Potion));

                    Game.difficulty = 1;
                }
            }
        }
        if(Game.gameState == Game.STATE.Paused){
            //Menu Button
            if(mouseOver(mX, mY,210, 130, 180, 64)){
                AudioPlayer.getSound("menu_sound").play();
                Game.paused = false;
                handler.object.clear();
                Game.gameState = Game.STATE.Menu;
                hud.setScore(0);
                hud.setLevel(1);

                if (Game.gameState == Game.STATE.Menu || Game.gameState == Game.STATE.Help || Game.gameState == Game.STATE.End || Game.gameState == Game.STATE.Select){
                    for (int i = 0; i < 15; i++) {
                        handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT) , ID.MenuParticle, handler));
                    }
                }
                return;
            }
            //Restart Button
            if (mouseOver(mX,mY,210, 230, 180, 64)){
                AudioPlayer.getSound("menu_sound").play();
                Game.paused = false;
                handler.object.clear();
                hud.setLevel(1);
                hud.setScore(0);
                Game.gameState = Game.STATE.Game;

                if(Game.difficulty == 0) {
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
                    handler.addObject(new BasicEnemy((r.nextInt(Game.WIDTH)), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                    handler.addObject(new Potion(124, 366, ID.Potion));

                    Game.difficulty = 0;
                }
                else if (Game.difficulty == 1){
                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, hud));
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
                    handler.addObject(new Potion(124, 366, ID.Potion));

                    Game.difficulty = 1;
                }

            }
            //Quit Button
            if(mouseOver(mX,mY,210, 330, 180, 64)){
                AudioPlayer.getSound("menu_sound").play();
                System.exit(1);
            }
        }
    }


    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mX, int mY, int x, int y, int width, int height){
        if(mX > x && mX < x + width){
            //if mouse is within these given boundary return true else false.
            if(mY > y && mY < y + height){
                return true;
            }
            else return false;
        }
        else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(Game.gameState == Game.STATE.Menu){
            Font font = new Font("arial", Font.BOLD, 50);
            Font font2 = new Font("arial", Font.BOLD, 40);

            g.setFont(font);
            g.setColor(Color.CYAN);
            g.drawString("Wave", 240, 90);

            g.setColor(Color.GREEN);
            g.drawRect(210, 130, 180, 64);
            g.setFont(font2);
            g.drawString("Play", 260, 180);

            g.setColor(Color.ORANGE);
            g.setFont(font2);
            g.drawRect(210, 230, 180, 64);
            g.drawString("Help", 260, 280);


            g.setColor(Color.red);
            g.setFont(font2);
            g.drawRect(210, 330, 180, 64);
            g.drawString("Quit", 260, 380);


        }
        else if(Game.gameState == Game.STATE.Help){
            Font font = new Font("arial", Font.BOLD, 50);
            Font font2 = new Font("arial", Font.BOLD, 40);
            Font font3 = new Font("arial", Font.ITALIC, 20);

            g.setFont(font);
            g.setColor(Color.CYAN);
            g.drawString("Help", 240, 90);

            g.setFont(font2);
            g.setColor(Color.BLUE);
            g.drawRect(210, 330, 180, 64);
            g.drawString("Back", 250, 380);

            g.setFont(font3);
            g.setColor(Color.MAGENTA);

            g.drawString("Use W,A,S,D to move and dodge enemies.", 80,160);
            g.drawString("Esc to pause the game.", 80,200);
            g.drawString("Ikr, boss is kinda meh.", 80,240);
            g.drawString("If the game crashes, just restart it and it should run fine.", 80,280);
            g.drawString("Thanks for playing! Good Day <3!", 80,320);
        }
        else if(Game.gameState == Game.STATE.End){
            Font font = new Font("arial", Font.BOLD, 50);
            Font font2 = new Font("arial", Font.BOLD, 23);
            Font font4 = new Font("arial", Font.BOLD, 30);
            Font font3 = new Font("arial", Font.ITALIC, 30);

            g.setFont(font);
            g.setColor(Color.CYAN);
            g.drawString("Game Over!", 180, 90);


            g.setFont(font3);
            g.setColor(Color.WHITE);
            g.drawString("GG Noob! You lost with a score of "+ hud.getScore(), 60,150);

            g.setFont(font4);
            g.setColor(Color.BLUE);
            g.drawString("Menu", 264, 275);
            g.drawRect(230, 230, 150, 64);


            g.setFont(font2);
            g.setColor(Color.ORANGE);
            g.drawString("Try Again?", 250, 370);
            g.drawRect(230, 330, 150, 64);
        }
        else if(Game.gameState == Game.STATE.Select){
            Font font = new Font("arial", Font.BOLD, 50);
            Font font2 = new Font("arial", Font.BOLD, 40);

            g.setFont(font);
            g.setColor(Color.CYAN);
            g.drawString("Difficulty", 240, 90);

            g.setColor(Color.GREEN);
            g.drawRect(210, 130, 180, 64);
            g.setFont(font2);
            g.drawString("Normal", 230, 180);

            g.setColor(Color.ORANGE);
            g.setFont(font2);
            g.drawRect(210, 230, 180, 64);
            g.drawString("Insane", 240, 280);

            g.setColor(Color.red);
            g.setFont(font2);
            g.drawRect(210, 330, 180, 64);
            g.drawString("Back", 250, 380);
        }
        else if(Game.gameState == Game.STATE.Paused){
            Font font = new Font("arial", Font.BOLD, 50);
            Font font2 = new Font("arial", Font.BOLD, 40);
            Font font3 = new Font("arial", Font.BOLD, 45);

            g.setFont(font);
            g.setColor(Color.MAGENTA);
            g.drawString("Paused", 210, 90);

            g.setColor(Color.BLUE);
            g.drawRect(210, 130, 180, 64);
            g.setFont(font3);
            g.drawString("Menu", 240, 180);

            g.setColor(Color.ORANGE);
            g.setFont(font2);
            g.drawRect(210, 230, 180, 64);
            g.drawString("Restart", 230, 280);

            g.setColor(Color.red);
            g.setFont(font3);
            g.drawRect(210, 330, 180, 64);
            g.drawString("Quit", 250, 380);
        }
    }

}
