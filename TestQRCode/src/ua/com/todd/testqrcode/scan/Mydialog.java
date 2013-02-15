package ua.com.todd.testqrcode.scan;

import ua.com.todd.testqrcode.R;
import ua.com.todd.testqrcode.R.id;
import ua.com.todd.testqrcode.R.layout;

import com.google.zxing.Result;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.view.View;

public class Mydialog extends Dialog {

	private Result result;
	private String strOk;
	private String strCancel;
	private View.OnClickListener onClickListenerOk;
	private View.OnClickListener onClickListenerCancel;
	private Bitmap photo;
	
	public void setPositiveButton(String string, View.OnClickListener onClickListener) {
		strOk = string;
		onClickListenerOk = onClickListener;
		
	}
	
	public void setNegativeButton(String string, View.OnClickListener onClickListener) {
		strCancel = string;
		onClickListenerCancel = onClickListener;
	}
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dilog_result);
		
		ImageView imgQRCode = (ImageView)findViewById(R.id.imageView1);
		imgQRCode.setImageBitmap(photo);
		
		
		TextView textView = (TextView)findViewById(R.id.textView1);
		textView.setText(result.getText());
		Log.i("lhj", result.getRawBytes().length + "");
		
		Button buttonOk = (Button)findViewById(R.id.buttonOk);
		Button buttonCancel = (Button)findViewById(R.id.buttonTryAgain);
		buttonOk.setText(strOk);
		buttonOk.setOnClickListener(onClickListenerOk);
		buttonCancel.setText(strCancel);
		buttonCancel.setOnClickListener(onClickListenerCancel);
	}

	public Mydialog(Context context, Result result, Bitmap photo) {
		super(context);
		this.result = result;
		this.photo = photo;
	}

}
