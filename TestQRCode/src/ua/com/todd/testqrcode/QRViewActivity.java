package ua.com.todd.testqrcode;

import ua.com.todd.testqrcode.data.QRCode;
import ua.com.todd.testqrcode.scan.MyBarCodeFragment;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class QRViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrview_activity);
		
		QRCode qrCode = getIntent().getParcelableExtra(MyBarCodeFragment.QR_CODE);
		
		TextView textViewDate  = (TextView)findViewById(R.id.textViewDateTime);
		TextView textViewText  = (TextView)findViewById(R.id.textViewData);
		TextView textViewLoc   = (TextView)findViewById(R.id.textViewLocation);
		ImageView imgViewPhoto = (ImageView)findViewById(R.id.imageViewQRPhoto);
		
		textViewDate.setText(qrCode.getText());
		textViewText.setText("Date: " + qrCode.getDateISO8601());
		textViewLoc.setText("Lat: " + qrCode.getLatitude() + " Long: " + qrCode.getLongitude());
		imgViewPhoto.setImageBitmap(qrCode.getPhoto());
	}
}
