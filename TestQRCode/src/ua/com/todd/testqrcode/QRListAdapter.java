package ua.com.todd.testqrcode;

import java.util.List;

import ua.com.todd.testqrcode.data.QRCode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QRListAdapter extends ArrayAdapter<QRCode> {

	private List<QRCode> listQR;
	private Context context;
	private int resource;
	private int imageViewResorse;
	private int textViewResourceId;
	
	public QRListAdapter(Context context, int resource, int imageViewResorse, int textViewResourceId,
			List<QRCode> listQR) {
		super(context, resource, textViewResourceId, listQR);
		this.context            = context;
		this.resource           = resource;
		this.imageViewResorse   = imageViewResorse;
		this.textViewResourceId = textViewResourceId;
		this.listQR             = listQR;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		super.getView(position, convertView, parent);
		
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = mInflater.inflate(resource, parent, false);
		
		QRCode qrCode = listQR.get(position);
		
		ImageView imageView = (ImageView)view.findViewById(imageViewResorse);
		imageView.setImageBitmap(qrCode.getPhoto());
		
		TextView textView = (TextView)view.findViewById(textViewResourceId);
		textView.setText(qrCode.getText());
		
		view.setTag(qrCode);
		
		return view;
	}
}
