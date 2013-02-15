package ua.com.todd.testqrcode;

import java.util.List;

import ua.com.todd.testqrcode.data.QRCode;
import ua.com.todd.testqrcode.data.QRCodeDuplicateException;
import ua.com.todd.testqrcode.database.DBHelper;
import ua.com.todd.testqrcode.database.QRDataBase;
import ua.com.todd.testqrcode.scan.MyBarCodeFragment;
import ua.com.todd.testqrcode.scan.QRScanActivity;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ListActivity implements OnClickListener, OnItemClickListener {

	private DBHelper dbHelper;
	private QRDataBase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity);
		Button button = (Button)findViewById(R.id.buttonScan);
		button.setOnClickListener(this);
		
		this.getListView().setOnItemClickListener(this);
		
		dbHelper = new DBHelper(this);
		db = new QRDataBase(dbHelper);
		
		
		List<QRCode> listQR = db.readAllFromDB();
		if(listQR == null){
			findViewById(R.id.textView1).setVisibility(View.VISIBLE);
			return;}
		else{
			setListAdapter(new QRListAdapter(this, R.layout.row_qr_list, R.id.imageView1, R.id.textView1, listQR));
			findViewById(R.id.textView1).setVisibility(View.INVISIBLE);}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, QRScanActivity.class);
		Log.i("start", "scan");
		startActivityForResult(intent, 0);		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		QRCode qrCode = null;
		
		try{
			qrCode = data.getParcelableExtra(MyBarCodeFragment.QR_CODE);
		}catch(NullPointerException e){
			e.printStackTrace();
			return;	}	
		
		try { 
			db.writeInDB(qrCode);
		} catch (QRCodeDuplicateException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
		List<QRCode> listQR = db.readAllFromDB();
		setListAdapter(new QRListAdapter(this, R.layout.row_qr_list, R.id.imageView1, R.id.textView1, listQR));
		findViewById(R.id.textView1).setVisibility(View.INVISIBLE);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				
		QRCode qrCode = (QRCode)view.getTag();
		Intent intent = new Intent(this, QRViewActivity.class);
		intent.putExtra(MyBarCodeFragment.QR_CODE, qrCode);
		startActivity(intent);
	}

}
