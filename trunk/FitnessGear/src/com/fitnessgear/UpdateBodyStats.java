package com.fitnessgear;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

public class UpdateBodyStats extends Fragment {

	public static UpdateBodyStats newInstance(String string) {
		// TODO Auto-generated method stub
		UpdateBodyStats f = new UpdateBodyStats();
		Bundle b = new Bundle();
		b.putString("msg", string);
		f.setArguments(b);

		return f;
	}
}
