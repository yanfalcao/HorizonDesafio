package br.com.yanfalcao.mundialsurf.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;

public class NoteController {

    public static void createNoteTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE nota (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "onda_id INTEGER, notaParcial1 DOUBLE, notaParcial2 DOUBLE, notaParcial3 DOUBLE," +
                "FOREIGN KEY(onda_id) REFERENCES onda(_id));");
    }

    public static long insertNote(DataBaseHelper helper, String idWave, String n1, String n2, String n3){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("onda_id", idWave);
        values.put("notaParcial3", n3);
        values.put("notaParcial2", n2);
        values.put("notaParcial1", n1);

        long result = db.insert("nota", null, values);

        db.close();

        return result;

    }

    public static List<String> selectNotesByWave(DataBaseHelper helper, String id){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id FROM nota WHERE onda_id = " + id,null);
        ArrayList<String> list = new ArrayList<>();

        if(! cursor.moveToNext())
            return null;

        for(int i=0; i < cursor.getCount(); i++){
            list.add(String.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))));

            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public static boolean deleteNoteByWave(DataBaseHelper helper, String id){
        SQLiteDatabase db = helper.getWritableDatabase();
        String where [] = new String[]{ id };

        return 0 < db.delete("nota", "onda_id = ?", where);
    }
}
