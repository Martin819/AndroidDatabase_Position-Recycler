package cz.uhk.fim.brahavl1.pubdatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import cz.uhk.fim.brahavl1.pubdatabase.Pub;
import cz.uhk.fim.brahavl1.pubdatabase.PubData;

/**
 * Created by brahavl1 on 16.04.2018.
 */

//prostrednik, ktery komunikuje s databazi uvnitr androidu
public class DatabaseHelper extends SQLiteOpenHelper{


    public DatabaseHelper(Context context){
        super(context, "database", null,1); //nazev,.., verze
    }


    @Override//kdyz neni databaze
    public void onCreate(SQLiteDatabase sqlLiteDatabase) {
        sqlLiteDatabase.execSQL(PubTable.SQL_QUERY_CREATE);

        Log.w("sql","pustila se.."); //tohle píše přímo do logu
        System.out.println("poprve se spustila databaze");
    }

    @Override //kdyz delame aktualizaci databaze
    public void onUpgrade(SQLiteDatabase sqlLiteDatabase, int oldVersion, int newVersion) {
        //todo ulozit si aktualni data
        sqlLiteDatabase.execSQL(PubTable.SQL_QUERY_DELETE);
        onCreate(sqlLiteDatabase);

        //todo naplnit novou databazi
    }


    public PubData getAllPubs() {
        PubData pubData = new PubData(); // byl by seznam, ale je to tady protože mám na to tu třídu

        SQLiteDatabase database = getReadableDatabase(); // jestli chci z db cist

//        Cursor cursor = database.query() pro plnej dotaz

        Cursor cursor = database.rawQuery("SELECT * FROM " + PubTable.TABLE_NAME, null);

        while (cursor.moveToNext()) { //dokud je dalsi pozice k dispozici
            String name = cursor.getString(cursor.getColumnIndex(PubTable.COLUMN_NAME)); //do stringu name se ulozi hodnota z column name
            double lat = cursor.getDouble(cursor.getColumnIndex(PubTable.COLUMN_LAT));
            double lng = cursor.getDouble(cursor.getColumnIndex(PubTable.COLUMN_LNG));

            Pub pub = new Pub(name, lat, lng);
            pubData.add(pub);

        }


        return pubData;
    }

    public boolean savePub(Pub pub){

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PubTable.COLUMN_NAME, pub.getName());
        values.put(PubTable.COLUMN_LAT, pub.getLat());
        values.put(PubTable.COLUMN_LNG, pub.getLon());
        long id = database.insert(PubTable.TABLE_NAME,null, values);

        return id > 0;
    }
}
