package br.com.yanfalcao.mundialsurf.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.yanfalcao.mundialsurf.controller.BatteryController;
import br.com.yanfalcao.mundialsurf.controller.SurferController;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE = "MundialSurf";
    private static int VERSION = 1;

    public DataBaseHelper(Context context){
        super(context, DATA_BASE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SurferController.createSurferTable(db);
        BatteryController.createBatteryTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
