package com.unitfactory.mbortnic.utils;

import com.unitfactory.mbortnic.model.players.Player;

public class Util {

    public Player player;

    public static int GetMapSize(int level){
        return ((level -1) * 5 + 10 -  (level % 2));
    }

    public static boolean IsDigit(String str)
    {
        for (char c : str.toCharArray()){
            if (Character.isDigit(c) != true)
                return false;
        }
        return true;
    }

}
