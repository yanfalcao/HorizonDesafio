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

    public static boolean editSurfer(DataBaseHelper helper, String id, String name, String country){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", name);
        values.put("pais", country);

        int result= db.update("surfista", values, "_id = ?",new String[]{ id });

        db.close();

        return result > 0;
    }

    public static long insertSurfer(DataBaseHelper helper, String name, String country){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", name);
        values.put("pais", country);

        long result = db.insert("surfista", null, values);

        db.close();

        return result;
    }

    public static boolean deleteSurfer(DataBaseHelper helper, String id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String where [] = new String[]{ id };

        BatteryController.deleteBatteeriesBySurfer(helper, id);

        int result = db.delete("surfista", "_id = ?", where);

        db.close();

        return 0 < result;
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
