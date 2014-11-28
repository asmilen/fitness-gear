package com.fitnessgear.child;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.R.id;
import com.fitnessgear.R.layout;
import com.fitnessgear.R.menu;
import com.fitnessgear.database.DatabaseUltility;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseDetail extends Activity {

	private String exerciseID;
	private ImageView img1;
	private ImageView img2;
	private TextView exerciseName;
	private TextView txtRating;
	private TextView txtMuscle;
	private TextView txtEquipment;
	private TextView txtExerciseType;
	private TextView txtDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_detail);
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		// Bundle bundle = getIntent().getExtras();
		// exerciseID = bundle.getString("ExerciseID");
		//

		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
		exerciseName = (TextView) findViewById(R.id.exerciseName);
		txtRating = (TextView) findViewById(R.id.txtRating);
		txtMuscle = (TextView) findViewById(R.id.txtMuscle);
		txtEquipment = (TextView) findViewById(R.id.txtEquipment);
		txtExerciseType = (TextView) findViewById(R.id.txtExerciseType);
		txtDescription = (TextView) findViewById(R.id.txtDescription);
		getData();
	}

	public void getData() {
		Bundle bundle = getIntent().getExtras();
		exerciseID = bundle.getString("ExerciseID");
		Toast.makeText(getApplicationContext(), exerciseID, Toast.LENGTH_SHORT)
				.show();
		// MainActivity.db = MainActivity.dbHelper.getReadableDatabase();
		try {
			Cursor exerciseDetail = MainActivity.db
					.rawQuery(
							"Select * "
									+ "FROM Exercise,Equipment,ExerciseType,Muscles "
									+ "WHERE Exercise.ExerciseType = ExerciseType.ExerciseTypeId "
									+ "AND Exercise.MuscleTarget = Muscles.MuscleId "
									+ "AND Exercise.Equipment = Equipment.EquipmentId "
									+ "AND Exercise.ExerciseId = " + exerciseID,
							null);

			while (exerciseDetail.moveToNext()) {
				// DatabaseUltility.GetColumnValue(exerciseDetail,
				// DatabaseUltility.Image1);
				String image1 = DatabaseUltility.GetColumnValue(exerciseDetail,
						DatabaseUltility.Image1);
				byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
				Bitmap decodedByte = BitmapFactory.decodeByteArray(
						decodedString, 0, decodedString.length);

				img1.setImageBitmap(decodedByte);

				// DatabaseUltility.GetColumnValue(exerciseDetail,
				// DatabaseUltility.Image2);
				String image2 = DatabaseUltility.GetColumnValue(exerciseDetail,
						DatabaseUltility.Image2);
				decodedString = Base64.decode(image2, Base64.DEFAULT);
				decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
						decodedString.length);
				img2.setImageBitmap(decodedByte);

				exerciseName.setText(DatabaseUltility.GetColumnValue(
						exerciseDetail, DatabaseUltility.ExerciseName));
				txtRating.setText(DatabaseUltility.GetColumnValue(exerciseDetail, DatabaseUltility.Rating));
				txtMuscle.setText(DatabaseUltility.GetColumnValue(exerciseDetail, DatabaseUltility.MuscleName));
				txtEquipment.setText(DatabaseUltility.GetColumnValue(exerciseDetail, DatabaseUltility.EquipmentName));
				txtExerciseType.setText(DatabaseUltility.GetColumnValue(exerciseDetail, DatabaseUltility.ExerciseTypeName));
				txtDescription.setText(DatabaseUltility.GetColumnValue(exerciseDetail, DatabaseUltility.Description));
				
			}
		} catch (SQLiteException ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercise_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == android.R.id.home) {
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
