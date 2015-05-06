package com.example.br161.personalinventory;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditItemFragment extends Fragment {

    private EditText etEditName;

    private EditText etEditCategory;

    private EditText etEditQuantity;

    private TextView tvButtonQuantityAdd;

    private TextView tvButtonQuantitySubtract;

    private EditText etEditDescription;

    private CheckBox cbEditIsFavorite;

    private TextView tvButtonCancel;

    private TextView tvButtonSubmit;

    private Item item;

    public EditItemFragment() {
        // Required empty public constructor
    }//end EditItemFragment

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        item = (Item) getArguments().getSerializable("item");
    }//end onCreate method

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_item, container, false);
    }//end onCreateView method

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEditName = (EditText) view.findViewById(R.id.et_edit_name);
        etEditCategory = (EditText) view.findViewById(R.id.et_edit_category);
        etEditQuantity = (EditText) view.findViewById(R.id.et_edit_quantity);
        tvButtonQuantityAdd = (TextView) view.findViewById(R.id.tv_button_quantity_add_1);
        tvButtonQuantitySubtract = (TextView) view.findViewById(R.id.tv_button_quantity_subtract_1);
        etEditDescription = (EditText) view.findViewById(R.id.et_edit_description);
        cbEditIsFavorite = (CheckBox) view.findViewById(R.id.cb_edit_favorite);
        tvButtonCancel = (TextView) view.findViewById(R.id.tv_button_cancel);
        tvButtonSubmit = (TextView) view.findViewById(R.id.tv_button_submit_edit);

        etEditName.setText(item.getName());
		etEditCategory.setText(item.getCategory());
		etEditQuantity.setText(item.getQuantity() + "");
		etEditDescription.setText(item.getDescription());
        cbEditIsFavorite.setChecked(item.isFavorite());

        tvButtonQuantityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(etEditQuantity.getText().toString());
                etEditQuantity.setText((currentQuantity + 1) + "");
            }//end onClick
        });//end tvButtonQuantityAdd.setOnClickListener

        tvButtonQuantitySubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(etEditQuantity.getText().toString());
                etEditQuantity.setText((currentQuantity - 1) + "");
            }//end onClick
        });//end tvButtonQuantitySubtract.setOnClickListener

        tvButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragmentToViewItems();
            }//end onClick
        });

        tvButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etEditName.getText().toString();
                String category = etEditCategory.getText().toString();
                String description = etEditDescription.getText().toString();
                int quantity = Integer.parseInt(etEditQuantity.getText().toString());
                boolean isFavorite = cbEditIsFavorite.isChecked();

                item.setName(name);
                item.setCategory(category);
                item.setDescription(description);
                item.setQuantity(quantity);
                item.setFavorite(isFavorite);

                changeFragmentToViewItems();
            }//end onClick
        });//end tvButtonSubmit.setOnClickListener
    }//end onViewCreated

    private void changeFragmentToViewItems() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new ViewItemsFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit();
    }//end changeFragmentToViewItems method
}//end EditItemFragment class
