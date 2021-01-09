package br.com.yanfalcao.mundialsurf.controller;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class SurferController {

    public static void createSurferTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE surfista (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, pais TEXT);");
    }

    public static String getIdSurferByName(String name, List<Surfer> surfers){

        for (Surfer s : surfers){
            if(s.getName().equals(name))
                return String.valueOf(s.getId());
        }

        return null;
    }

    public static ArrayList<String> getSurfersName(List<Surfer> surfers) {
        ArrayList<String> surfersName = new ArrayList<String>();

        surfersName.add("Select a new surfer...");

        for(Surfer s : surfers){
            surfersName.add(s.getName());
        }

        return surfersName;
    }
}
