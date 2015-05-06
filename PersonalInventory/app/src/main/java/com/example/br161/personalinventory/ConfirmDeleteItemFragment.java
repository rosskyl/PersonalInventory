package com.example.br161.personalinventory;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.ParseException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmDeleteItemFragment extends DialogFragment {

    private Item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        item = (Item) getArguments().getSerializable("item");
    }//end onCreate method

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(getString(R.string.dialog_delete) + " " + item.getName() + "?")
                .setPositiveButton(R.string.button_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        item.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                Context context = activity.getApplicationContext();
                                CharSequence text = item.getName() + " successfully deleted";
                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();

                                activity.getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_fragment_container, new ViewItemsFragment())
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                        .commit();
                            }//end done
                        });//end item.deleteInBackground
                    }//end onClick
                })//end .setPositiveButton
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }//end onClick
                });//end .setNegativeButton
        // Create the AlertDialog object and return it
        return builder.create();
    }//end onCreateDialog method
}//end ConfirmDeleteItemFragment class
