package br.com.yanfalcao.mundialsurf.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;
import br.com.yanfalcao.mundialsurf.model.surfer.Surfer;

public class SurferController {

    public static void createSurferTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE surfista (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, pais TEXT);");
    }

    public static List<Map<String, Object>> selectSurfers(DataBaseHelper helper, String id, String name, String country){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM surfista" ,null);
        List<Map<String, Object>> surfers = new ArrayList<Map<String, Object>>();

        if(! cursor.moveToNext()){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put(id, "Empty");
            item.put(name, "Empty");
            item.put(country, "Empty");

            surfers.add(item);

            cursor.close();

            return surfers;
        }

        for(int i=0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put(id, cursor.getInt(cursor.getColumnIndex("_id")));
            item.put(name, cursor.getString(cursor.getColumnIndex("nome")));
            item.put(country, cursor.getString(cursor.getColumnIndex("pais")));

            surfers.add(item);

            cursor.moveToNext();

        }
        cursor.close();
        db.close();

        return surfers;
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
