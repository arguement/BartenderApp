package com.williams.jordan.bartenderapp;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<String> mArrayList = new ArrayList();

    public static void add(String var){
        mArrayList.add(var);
    }
    public static ArrayList<String> getmArrayList(){
        return  mArrayList;
    }
}
