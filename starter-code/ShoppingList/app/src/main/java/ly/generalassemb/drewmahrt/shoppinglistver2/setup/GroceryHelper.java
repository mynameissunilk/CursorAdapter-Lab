package ly.generalassemb.drewmahrt.shoppinglistver2.setup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunil on 7/12/16.
 */
public class GroceryHelper extends SQLiteOpenHelper {
    private static GroceryHelper sInstance;


    private static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "shopping_list";
    public static final String TABLE_NAME = "GROCERY_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "ITEM_NAME";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String TABLE_STRING  = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + "INTEGER PRIMARY KEY, " +
            COL_NAME + "NAME," +
            COL_DESC  + "TEXT," +
            COL_PRICE + "TEXT," +
            COL_TYPE + "TEXT)";

    public static final String[] ITEM_COLS = {COL_ID,COL_NAME,COL_DESC,COL_PRICE,COL_TYPE};



    private GroceryHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION );
    }

    public static GroceryHelper getInstance(Context context){
        if(sInstance == null) {
            sInstance = new GroceryHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getNames(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =  db.query(TABLE_NAME, null,null,null,null,null,null);
        return cursor;
    }

}
