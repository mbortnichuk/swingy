package com.unitfactory.mbortnic.utils;

public class Util {

    public static boolean isDigit(String str)
    {
        for (char c : str.toCharArray()){
            if (Character.isDigit(c) != true)
                return false;
        }
        return true;
    }

}
