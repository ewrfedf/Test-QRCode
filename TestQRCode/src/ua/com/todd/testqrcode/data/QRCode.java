package ua.com.todd.testqrcode.data;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class QRCode implements Parcelable{
	
	private long date;
	private Bitmap photo;
	private String text;
	private double latitude;
	private double longitude;
	
	public QRCode(Bitmap photo, String text, long date, Location location){
		this.date      = date;
		this.latitude  = location.getLatitude();
		this.longitude = location.getLongitude();
		this.photo     = photo;
		this.text      = text;
	}
	
	public QRCode(byte[] photo, String text, long datetime, double latitude, double longitude){
		this.date      = datetime;
		this.latitude  = latitude;
		this.longitude = longitude;
		this.photo     = BitmapFactory.decodeByteArray(photo, 0, photo.length);
		this.text      = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QRCode other = (QRCode) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	public long getDateInMls() {
		return date;
	}
	
	public String getDateISO8601(){
		Date date = new Date(this.date);
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
	}

	public Bitmap getPhoto() {
		return photo;
	}
	
	public byte[] getPhotoByteArray() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		return byteArray;
	}

	public String getText() {
		return text;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "QRCode [date=" + date + ", photo=" + photo + ", text=" + text
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeParcelable(photo, arg1);
		parcel.writeLong(date);
		parcel.writeString(text);
		parcel.writeDouble(latitude);
		parcel.writeDouble(longitude);
	}
	
	public static final Parcelable.Creator<QRCode> CREATOR = new Parcelable.Creator<QRCode>() {
		
		@Override
	    public QRCode createFromParcel(Parcel in) {
	      return new QRCode(in);
	    }
		@Override
	    public QRCode[] newArray(int size) {
	      return new QRCode[size];
	    }
	  };

	  private QRCode(Parcel parcel) {
		  this.photo     = parcel.readParcelable(null);
		  this.date      = parcel.readLong();
		  this.text      = parcel.readString();
		  this.latitude  = parcel.readDouble();
		  this.longitude = parcel.readDouble();
	  }

}
