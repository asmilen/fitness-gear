package com.fitnessgear;

import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class FilterPlanDialogFragment extends DialogFragment {
	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ArrayList<String> mSelectedItems = new ArrayList<String>();  // Where we track the selected items
		mSelectedItems.add("Duong");
		mSelectedItems.add("Minh");
		mSelectedItems.add("Nam");
		mSelectedItems.add("Thanh");
		mSelectedItems.add("Linh");
		mSelectedItems.add("Hoang");
		mSelectedItems.add("Huy");
		mSelectedItems.add("Trang");
		mSelectedItems.add("Nga");
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
//	    builder.setTitle("Filter Main Goal")
//	    // Specify the list array, the items to be selected by default (null for none),
//	    // and the listener through which to receive callbacks when items are selected
//	           .setSingleChoiceItems(mSelectedItems, -1, new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					mSelectedItems.get(which).toString().toLowerCase(Locale.getDefault());
//					
//				}
//			})(R.array.toppings, null,
//	                      new DialogInterface.OnMultiChoiceClickListener() {
//	               @Override
//	               public void onClick(DialogInterface dialog, int which,
//	                       boolean isChecked) {
//	                   if (isChecked) {
//	                       // If the user checked the item, add it to the selected items
//	                       mSelectedItems.add(which);
//	                   } else if (mSelectedItems.contains(which)) {
//	                       // Else, if the item is already in the array, remove it 
//	                       mSelectedItems.remove(Integer.valueOf(which));
//	                   }
//	               }
//	           })
//	    // Set the action buttons
//	           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//	               @Override
//	               public void onClick(DialogInterface dialog, int id) {
//	                   // User clicked OK, so save the mSelectedItems results somewhere
//	                   // or return them to the component that opened the dialog
//	                   ...
//	               }
//	           })
//	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//	               @Override
//	               public void onClick(DialogInterface dialog, int id) {
//	                   ...
//	               }
//	           });

	    return builder.create();
	}
}
