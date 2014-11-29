package com.fitnessgear;




import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class Backup extends Fragment {

	private EditText edit;
	private Drawable draw;
	private ImageView image;
	
	public Backup() {
		// TODO Auto-generated constructor stub
	}
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View rootView = inflater.inflate(R.layout.fragment_backup, container, false);
	edit = (EditText) rootView.findViewById(R.id.editText1);
	draw = getResources().getDrawable(R.drawable.text_field_clear_btn);
	image = (ImageView) rootView.findViewById(R.id.imageView1);
	image.setImageDrawable(draw);
//	edit.getText()
	return rootView;
}
}
