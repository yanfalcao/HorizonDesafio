package br.com.yanfalcao.mundialsurf.model;

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

    public List<Map<String, Object>> selectBatteries(String id, String surferOne, String surferTwo){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT bat._id, bat.surfista1, bat.surfista2, s1.nome as surfistaUm, s2.nome as surfistaDois " +
                "FROM bateria bat, surfista s1, surfista s2 " +
                "WHERE bat.surfista1 = s1._id " +
                "AND bat.surfista2 = s2._id"),null);

        if(! cursor.moveToNext()){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put(id, "Empty");
            item.put(surferOne, "Empty");
            item.put(surferTwo, "Empty");

            surfers.add(item);

            cursor.close();

            return surfers;
        }

        for(int i=0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put(id, cursor.getInt(cursor.getColumnIndex("_id")));
            item.put("idSurferOne", cursor.getInt(cursor.getColumnIndex("surfista1")));
            item.put("idSurferTwo", cursor.getInt(cursor.getColumnIndex("surfista2")));
            item.put(surferOne, cursor.getString(cursor.getColumnIndex("surfistaUm")));
            item.put(surferTwo, cursor.getString(cursor.getColumnIndex("surfistaDois")));

            surfers.add(item);

            cursor.moveToNext();

        }
        cursor.close();
        db.close();

        return surfers;
    }

    public List<String> selectWaves(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id FROM onda ",null);
        ArrayList<String> list = new ArrayList<String>();

        if(! cursor.moveToNext()){

            list.add("Empty");

            cursor.close();

            return list;
        }

        for(int i=0; i < cursor.getCount(); i++){
            list.add(String.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))));

            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return list;
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

    public long insertWave(String batteryId, String surferId){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("bateria_id", batteryId);
        values.put("surfista_id", surferId);

        long result = db.insert("onda", null, values);

        db.close();

        return result;

    }

    public long insertNote(String idWave, Double n1, Double n2, Double n3){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("onda_id", idWave);
        values.put("notaPacial1", n1.toString());
        values.put("notaPacial2", n2.toString());
        values.put("notaPacial3", n3.toString());

        long result = db.insert("nota", null, values);

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
