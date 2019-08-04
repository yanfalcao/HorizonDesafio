package br.com.yanfalcao.mundialsurf.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.yanfalcao.mundialsurf.model.DataBaseHelper;

public class WaveController {

    public static void createWaveTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE onda (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bateria_id INTEGER, surfista_id INTEGER," +
                "FOREIGN KEY(bateria_id) REFERENCES bateria(_id)," +
                "FOREIGN KEY(surfista_id) REFERENCES surfista(_id));");
    }

    public static List<String> selectWavesId(DataBaseHelper helper){
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

    public static long insertWave(DataBaseHelper helper, String batteryId, String surferId){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("bateria_id", batteryId);
        values.put("surfista_id", surferId);

        return db.insert("onda", null, values);

    }

    public static boolean deleteWaveByBattery(DataBaseHelper helper, String id){
        List<String> notes = NoteController.selectNotesByWave(helper, id);

        if(notes != null)
            for(String i : notes)
                NoteController.deleteNoteByWave(helper, i);

        SQLiteDatabase db = helper.getWritableDatabase();
        String where [] = new String[]{ id };

        return 0 < db.delete("onda", "bateria_id = ?", where);
    }
}
