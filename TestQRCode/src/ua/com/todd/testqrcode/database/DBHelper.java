package ua.com.todd.testqrcode.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	
	public static final String ID       = "id";
	public static final String DATETIME = "dTime";
	public static final String DATA     = "data";
	public static final String PHOTO    = "img";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE= "longitude";
	public static final String HASH     = "hash";
	
	public static final String QRTABLE  = "qrtable";
	
	public static final String INT    = "integer";
	public static final String TEXT   = "text";
	public static final String BLOB   = "blob";
	public static final String DOUBLE = "double";
	
	public static final String DATABASE_REQUEST = "create table " + QRTABLE + " ("
	          + ID + " " + INT + " primary key autoincrement," 
	          + DATETIME + " " + INT + ","
	          + DATA + " " + TEXT + ","
	          + PHOTO + " " + BLOB + ","
	          + LATITUDE + " " + DOUBLE + ","
	          + LONGITUDE + " " + DOUBLE + ","
	          + HASH + " " + INT + ");";

	public DBHelper(Context context) {
		super(context, "QRDataBase", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
			db.execSQL(DATABASE_REQUEST);
		}catch(SQLException e){
			Log.i("DATABASE_REQUEST", DATABASE_REQUEST);
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
