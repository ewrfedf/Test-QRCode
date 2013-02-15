package ua.com.todd.testqrcode.scan;

import ua.com.todd.testqrcode.data.QRCode;

import com.abhi.barcode.fragment.library.BarCodeFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.view.View;

public class MyBarCodeFragment extends BarCodeFragment {

	public static final String QR_CODE = "qrCode";
	@Override
	public Dialog createDialog(int mWhat) {
		
		LocationManager locationManager =
		        (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		final Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		final long time         = System.currentTimeMillis();
		
		final Mydialog dialog = new Mydialog(getActivity(),lastResult, this.getPhoto());
		
		dialog.setPositiveButton("Ok", new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent data   = new Intent();
				QRCode qrCode = new QRCode(getPhoto(), lastResult.getText(), time, location);
				data.putExtra(QR_CODE, qrCode);
				getActivity().setResult(Activity.RESULT_OK, data);
				getActivity().finish();
			}
			
		});
		
		dialog.setNegativeButton("Scan Again", new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				startCameraCampure();
				dialog.dismiss();
			}
			
		});
		return dialog;
	}
}
