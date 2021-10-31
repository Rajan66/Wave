package com.app.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        keyDown[0] = false; //W
        keyDown[1] = false; //S
        keyDown[2] = false; //D
        keyDown[3] = false; //A
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println(key);
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                    //velX used because it runs much smoother
                    // as we add the velX on each key press to the actual position (x).
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                    keyDown[3] = true;
                }
            }

        }
        if (key == KeyEvent.VK_ESCAPE){
            if(Game.gameState == Game.STATE.Paused && Game.paused) {
                Game.gameState = Game.STATE.Game;
                Game.paused = false;
            }     // if game is already paused, make paused to false.
            else if (Game.gameState == Game.STATE.Game && !Game.paused){
                    Game.paused = true;
                } // if game is not already paused and key is pressed, pause the game.


        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_W) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_S) {
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_D) {
                    keyDown[2] = false;
                }
                if (key == KeyEvent.VK_A) {
                    keyDown[3] = false;
                }
                //Vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //Horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
        }
    }
}

