package ua.com.todd.testqrcode.scan;

import ua.com.todd.testqrcode.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class QRScanActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrscan_activity);
	}
}
