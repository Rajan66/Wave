package com.app.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{

    Random r = new Random();
    Handler handler;
    Spawn spawner = new Spawn();

    private int xBoundary;
    private int yBoundary;

    HUD hud;

    private int scoreKeep = 0;

    public Player(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
    }

    public Player(int x, int y, ID id, Handler handler,HUD hud){
        super(x,y,id);
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        x += velX;
        y += velY;

// There is a better way of doing this.
//        if(x <= 0 || x >= Game.WIDTH - 48){
//            x = x -velX;
//        }
//        if(y <= 0 || y >= Game.HEIGHT - 72){
//            y = y - velY;
//        }
//
        x = Game.clamp(x,0,Game.WIDTH - 48);
        y = Game.clamp(y,0,Game.HEIGHT - 72);

        collision();
        healUp();
        handler.addObject(new Trail(x,y,ID.Trail,Color.blue,32,32,0.1f,handler));
    }

    private void collision(){
        for(int i =0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 5;
                }

//                if(HUD.HEALTH == 0){a
//                    System.out.println("game over");
//                    System.exit(1);
//                }
            }
            if(tempObject.getId() == ID.HardEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 10;
                }
            }
        }
    }

    private void healUp(){

        for(int i =0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Potion){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH += 20;
                    hud.setScore(hud.getScore() + 100);
                    handler.removeObject(tempObject);
                    spawnPotion();
                    spawnEnemy();
                }
            }
        }
    }

    public void spawnEnemy(){
        scoreKeep += 100;
        if(scoreKeep >= 500) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if(Game.difficulty == 0){
                switch (hud.getLevel()) {
                    case 2:

                        handler.addObject(new BasicEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        break;
                    case 3:
                        handler.addObject(new BasicEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        handler.addObject(new BasicEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        break;
                    case 4:
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new BasicEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        break;
                    case 5:
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        break;
                    case 6:
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        break;
                    case 8:
                        handler.clearEnemies();
                        handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -100, ID.EnemyBoss, handler));
                        break;
                    default:
                        break;
                }
            }
            else if(Game.difficulty == 1){
                switch (hud.getLevel()) {
                    case 2:

                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        break;
                    case 3:
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        break;
                    case 4:
                        handler.addObject(new FastEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.BasicEnemy, handler));
                        break;
                    case 5:
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        break;
                    case 6:
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        break;
                    case 9:
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        handler.addObject(new HardEnemy(spawner.xSpawnProtector(), spawner.ySpawnProtector(), ID.FastEnemy, handler));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x,(int)y,32,32);
    }

    public void spawnPotion(){
        xBoundary = (int) spawner.xSpawnProtector();
        yBoundary = (int) spawner.ySpawnProtector();

        handler.addObject(new Potion(xBoundary,yBoundary,ID.Potion));

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int) y, 32, 32);
    }
}
