package br.com.yanfalcao.mundialdesurf.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE = "MundialSurf";
    private static int VERSION = 1;

    public DataBaseHelper(Context context){
        super(context, DATA_BASE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE surfista (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT, pais TEXT);");
        db.execSQL("CREATE TABLE bateria (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "surfista1 INTEGER, surfista2 INTEGER," +
                "FOREIGN KEY(surfista1) REFERENCES surfista(_id)," +
                "FOREIGN KEY(surfista2) REFERENCES surfista(_id));");
        db.execSQL("CREATE TABLE onda (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bateria_id INTEGER, surfista_id INTEGER," +
                "FOREIGN KEY(bateria_id) REFERENCES bateria(_id)," +
                "FOREIGN KEY(surfista_id) REFERENCES surfista(_id));");
        db.execSQL("CREATE TABLE nota (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "onda_id INTEGER, notaPacial1 DOUBLE, notaParcial2 DOUBLE, notaParcial3 DOUBLE," +
                "FOREIGN KEY(onda_id) REFERENCES onda(_id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
