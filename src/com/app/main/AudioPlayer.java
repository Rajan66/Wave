package com.app.main;

import org.newdawn.slick.*;
import java.util.*;

public class AudioPlayer {

    static Music music;
    private static Sound sound;

    public static Map<String, Sound> soundMap = new HashMap<String,Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load(){
        try{
            music = new Music("res\\haiku.wav");
            musicMap.put("music", music);

            sound = new Sound("res\\click.wav");

            soundMap.put("menu_sound",sound);

        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public void setVolume(){

    }

    public static Music getMusic(String key){
        return musicMap.get(key);
    }

    public static Sound getSound(String key){
        return soundMap.get(key);
    }
}
