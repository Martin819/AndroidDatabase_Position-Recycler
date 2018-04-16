package cz.uhk.fim.brahavl1.pubdatabase.database;

import android.provider.BaseColumns;

/**
 * Created by brahavl1 on 16.04.2018.
 */

public class PubTable implements BaseColumns {

    public static final String TABLE_NAME = "pubs"; //nazev
    public static final String COLUMN_NAME = "name"; //sloupce
    public static final String COLUMN_LAT= "lat";
    public static final String COLUMN_LNG= "long";


    //vola se vzdycky kdyz tam neni databaze
    public static final String SQL_QUERY_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT, "
            + COLUMN_LAT + " REAL, "
            + COLUMN_LNG + " REAL) ";


    //vola se kdyz se meni verze databaze
    public static final String SQL_QUERY_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}

