package ua.com.todd.testqrcode.database;
import java.util.ArrayList;
import java.util.List;

import ua.com.todd.testqrcode.data.QRCode;
import ua.com.todd.testqrcode.data.QRCodeDuplicateException;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QRDataBase {

	private SQLiteDatabase db;
	
	public QRDataBase(DBHelper dbHelper){
		db = dbHelper.getWritableDatabase();
	}
	
	public void writeInDB(QRCode qrCode) throws QRCodeDuplicateException{
		
		String colums[] = {DBHelper.HASH};
		
		Cursor cursor = db.query(DBHelper.QRTABLE, colums, null, null, null, null, null);
		
		if (cursor.moveToFirst()) {
	    	  
	    	  int hashColIndex       = cursor.getColumnIndex(DBHelper.HASH);
	    	  
	    	  do {
		             int hash          = cursor.getInt(hashColIndex);
		             if(qrCode.hashCode() == hash)
						throw new QRCodeDuplicateException("You have this QR code");
	    	  } while (cursor.moveToNext());
	      }
		
		ContentValues cv = new ContentValues();
		
		long date        = qrCode.getDateInMls();
		String text      = qrCode.getText();
		byte[] photo     = qrCode.getPhotoByteArray();
		double latitude  = qrCode.getLatitude();
		double longitude = qrCode.getLongitude();
		int hash         = qrCode.hashCode();
		
		cv.put(DBHelper.DATETIME, date);
		cv.put(DBHelper.DATA, text);
		cv.put(DBHelper.PHOTO, photo);
		cv.put(DBHelper.LATITUDE, latitude);
		cv.put(DBHelper.LONGITUDE, longitude);
		cv.put(DBHelper.HASH, hash);
		
		db.insert(DBHelper.QRTABLE, null, cv);
	}
	
	public List<QRCode> readAllFromDB() throws NullPointerException{
		
		List<QRCode> listQRCode = new ArrayList<QRCode>();
		Cursor cursor = db.query(DBHelper.QRTABLE, null, null, null, null, null, null);

	      if (cursor.moveToFirst()) {
	          int dateColIndex     = cursor.getColumnIndex(DBHelper.DATETIME);
	          int textColIndex     = cursor.getColumnIndex(DBHelper.DATA);
	          int photoColIndex    = cursor.getColumnIndex(DBHelper.PHOTO);
	          int latitudeColIndex = cursor.getColumnIndex(DBHelper.LATITUDE);
	          int longitudeColIndex= cursor.getColumnIndex(DBHelper.LONGITUDE);

	        do {
	             long date        = cursor.getLong(dateColIndex);
	             String text     = cursor.getString(textColIndex);
	             byte[] photo    = cursor.getBlob(photoColIndex);
	             double latitude = cursor.getDouble(latitudeColIndex);
	             double longitude= cursor.getLong(longitudeColIndex);
	             
	             QRCode qrCode = new QRCode(photo, text, date, latitude, longitude);
	             listQRCode.add(qrCode);
	        } while (cursor.moveToNext());
	      }
	      else
	    	  return null;
		
		return listQRCode;
	}
}
