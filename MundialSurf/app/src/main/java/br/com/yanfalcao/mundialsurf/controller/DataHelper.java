package br.com.yanfalcao.mundialsurf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataHelper {

    public static boolean areSame(String surferOne, String surferTwo){
        if(surferOne.equals(surferTwo) || surferOne == null || surferTwo == null)
            return false;

        return true;
    }

    public static String getIdSurferByName(String name, ArrayList<Map<String, Object>> surfers){
        Iterator i = surfers.iterator();
        while(i.hasNext()){
            HashMap<String, Object> surfer = (HashMap) i.next();
            if(surfer.get("name").equals(name))
                return surfer.get("id").toString();
        }

        return null;
    }

    public static ArrayList<String> getSurfersName(ArrayList<Map<String, Object>> surfers) {
        ArrayList<String> surfersName = new ArrayList<String>();

        Iterator i = surfers.iterator();
        while(i.hasNext()){
            HashMap<String, Object> surfer = (HashMap) i.next();
            surfersName.add((String)surfer.get("name"));
        }

        return surfersName;
    }
}
