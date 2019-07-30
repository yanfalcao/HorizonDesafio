package br.com.yanfalcao.mundialdesurf.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataModel {
    private DataBaseHelper helper;
    private ArrayList<Map<String, Object>> surfers;

    public DataModel(Context context){
        helper = new DataBaseHelper(context);
        surfers = new ArrayList<Map<String, Object>>();
    }

    public List<Map<String, Object>> selectSurfers(String id, String name, String country){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM surfista" ,null);

        if(! cursor.moveToNext()){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put("id", "Empty");
            item.put("name", "Empty");
            item.put("country", "Empty");

            surfers.add(item);

            cursor.close();

            return surfers;
        }

        for(int i=0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put("id", cursor.getInt(cursor.getColumnIndex("_id")));
            item.put("name", cursor.getString(cursor.getColumnIndex("nome")));
            item.put("country", cursor.getString(cursor.getColumnIndex("pais")));

            surfers.add(item);

            cursor.moveToNext();

        }
        cursor.close();
        db.close();

        return surfers;
    }

    public long insertSurfer(String name, String country){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", name);
        values.put("pais", country);

        long result = db.insert("surfista", null, values);

        db.close();

        return result;
    }

    public long insertBattery(String surferOneId, String surferTwoId){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("surfista1", surferOneId);
        values.put("surfista2", surferTwoId);

        long result = db.insert("bateria", null, values);

        db.close();

        return result;

    }

    public boolean editSurfer(String id, String name, String country){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", name);
        values.put("pais", country);

        int result= db.update("surfista", values, "_id = ?",new String[]{ id });

        db.close();

        return result > 0;
    }

    public boolean deleteSurfer(String id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String where [] = new String[]{ id };

        db.delete("bateria", "surfista1 = ?", where);
        db.delete("bateria", "surfista2 = ?", where);

        int result = db.delete("surfista", "_id = ?", where);

        db.close();

        return 0 < result;
    }

    public void dataDestroy(){
        helper.close();
    }
}
