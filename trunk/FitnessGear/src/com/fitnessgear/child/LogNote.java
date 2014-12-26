package com.fitnessgear.child;

import java.util.ArrayList;

import com.fitnessgear.MainActivity;
import com.fitnessgear.R;
import com.fitnessgear.adapter.NoteAdapter;
import com.fitnessgear.database.DatabaseUltility;
import com.fitnessgear.model.LogNoteItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LogNote extends Fragment {

	private EditText contentNote;
	private TextView timeNote;
	private ListView listNote;
	private String dayID;
	private NoteAdapter noteAdapter;
	private int noteID = 0;
	private ArrayList<LogNoteItem> listNoteItem;

	private LinearLayout logNoteLayout;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_log_note, container,
				false);
		setHasOptionsMenu(true);

		try {
			logNoteLayout = (LinearLayout) rootView
					.findViewById(R.id.logNoteLayout);

			MainActivity.db = MainActivity.dbHelper.getWritableDatabase();
			contentNote = (EditText) rootView.findViewById(R.id.contentNote);
			timeNote = (TextView) rootView.findViewById(R.id.timeNote);
			listNote = (ListView) rootView.findViewById(R.id.listNote);

			logNoteLayout.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					hideKeyboard(v);
					contentNote.clearFocus();
					return false;
				}
			});

			getData();
		} catch (Exception ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
		return rootView;
	}

	public void getData() {
		dayID = getArguments().getString("dayID");

		timeNote.setText("Note For " + dayID);

		Cursor noteCursor = MainActivity.db.rawQuery(
				"Select * From Log_Note Where day = '" + dayID + "'", null);

		try {
			listNoteItem = new ArrayList<LogNoteItem>();
			while (noteCursor.moveToNext()) {
				noteID = DatabaseUltility.GetIntColumnValue(noteCursor,
						DatabaseUltility.NoteID);
				listNoteItem.add(new LogNoteItem(dayID,
						DatabaseUltility.GetIntColumnValue(noteCursor,
								DatabaseUltility.NoteID), DatabaseUltility
								.GetColumnValue(noteCursor,
										DatabaseUltility.NoteDetail)));
			}
			noteID++;

			noteAdapter = new NoteAdapter(getActivity(), listNoteItem);
			listNote.setAdapter(noteAdapter);
			listNote.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					showDialog(listNoteItem.get(position).getNoteID());
				}
			});
		} catch (Exception ex) {
			Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG)
					.show();
		} finally {
			noteCursor.close();
		}
	}

	public static Fragment newInstance(String string) {
		// TODO Auto-generated method stub
		LogNote f = new LogNote();
		Bundle b = new Bundle();
		b.putString("dayID", string);
		f.setArguments(b);

		return f;
	}

	public void showDialog(final int noteID) {
		final EditText etContentNote = new EditText(getActivity());
		// numberWorkout.setInputType(InputType.TYPE_CLASS_NUMBER);
		InputFilter[] fArray = new InputFilter[1];
		fArray[0] = new InputFilter.LengthFilter(150);
		etContentNote.setFilters(fArray);

		Cursor noteCursor = MainActivity.db.rawQuery(
				"Select * From Log_Note Where NoteID = " + noteID
						+ " AND day = '" + dayID + "'", null);

		while (noteCursor.moveToNext()) {
			etContentNote.setText(DatabaseUltility.GetColumnValue(noteCursor,
					DatabaseUltility.NoteDetail));
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Edit Note")
				.setView(etContentNote)
				.setPositiveButton("Update Note",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								ContentValues contentValueNote = new ContentValues();
								contentValueNote.put("NoteDetail",
										etContentNote.getText().toString());
								int updateNote = MainActivity.db.update(
										"Log_Note", contentValueNote,
										"NoteID = ? AND day = ?", new String[] {
												"" + noteID, dayID });
								if (updateNote > 0) {
									getData();
								}
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
		builder.show();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.log_note, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.saveNote) {
			contentNote.clearFocus();
			ContentValues contentValueNote = new ContentValues();
			contentValueNote.put("Day", dayID);
			contentValueNote.put("NoteID", noteID);
			contentValueNote
					.put("NoteDetail", contentNote.getText().toString());
			int insertNote = (int) MainActivity.db.insert("Log_Note", null,
					contentValueNote);
			if (insertNote > 0) {
				Toast.makeText(getActivity(), "Insert Note Successfull",
						Toast.LENGTH_LONG).show();
				getData();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// An ban phim khi bam ra ben ngoai
	protected void hideKeyboard(View view) {
		InputMethodManager in = (InputMethodManager) getActivity()
				.getSystemService(getActivity().INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
