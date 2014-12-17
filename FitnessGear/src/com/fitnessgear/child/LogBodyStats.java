package com.fitnessgear.child;

import com.fitnessgear.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogBodyStats  extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_log_body_stats,
				container, false);

		return rootView;
	}

	public static Fragment newInstance(String string) {
		// TODO Auto-generated method stub
		LogBodyStats f = new LogBodyStats();
		Bundle b = new Bundle();
		b.putString("msg", string);
		f.setArguments(b);

		return f;
	}
}
