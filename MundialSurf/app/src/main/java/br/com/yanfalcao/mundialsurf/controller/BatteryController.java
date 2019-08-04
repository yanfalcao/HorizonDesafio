package br.com.yanfalcao.mundialsurf.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;

public class BatteryController {

    public static void createBatteryTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE bateria (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "surfista1 INTEGER, surfista2 INTEGER," +
                "FOREIGN KEY(surfista1) REFERENCES surfista(_id)," +
                "FOREIGN KEY(surfista2) REFERENCES surfista(_id));");
    }

    public static List<Map<String, Object>> selectBatteries(DataBaseHelper helper, String id, String surferOne, String surferTwo){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT bat._id, bat.surfista1, bat.surfista2, s1.nome as surfistaUm, s2.nome as surfistaDois " +
                "FROM bateria bat, surfista s1, surfista s2 " +
                "WHERE bat.surfista1 = s1._id " +
                "AND bat.surfista2 = s2._id"),null);
        List<Map<String, Object>> batteries = new ArrayList<Map<String, Object>>();

        if(! cursor.moveToNext()){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put(id, "Empty");
            item.put(surferOne, "Empty");
            item.put(surferTwo, "Empty");

            batteries.add(item);

            cursor.close();

            return batteries;
        }

        for(int i=0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put(id, cursor.getInt(cursor.getColumnIndex("_id")));
            item.put("idSurferOne", cursor.getInt(cursor.getColumnIndex("surfista1")));
            item.put("idSurferTwo", cursor.getInt(cursor.getColumnIndex("surfista2")));
            item.put(surferOne, cursor.getString(cursor.getColumnIndex("surfistaUm")));
            item.put(surferTwo, cursor.getString(cursor.getColumnIndex("surfistaDois")));

            batteries.add(item);

            cursor.moveToNext();

        }
        cursor.close();
        db.close();

        return batteries;
    }

    public static long insertBattery(DataBaseHelper helper, String surferOneId, String surferTwoId){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("surfista1", surferOneId);
        values.put("surfista2", surferTwoId);

        long result = db.insert("bateria", null, values);

        db.close();

        return result;

    }
}