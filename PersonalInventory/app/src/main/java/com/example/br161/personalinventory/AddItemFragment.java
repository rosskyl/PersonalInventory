package com.example.br161.personalinventory;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddItemFragment extends Fragment {

    private EditText etName;

    private EditText etCategory;

    private EditText etDescription;

    private EditText etQuantity;

    private TextView tvButtonSubmit;

    private TextView tvButtonCancel;


    public AddItemFragment() {
        // Required empty public constructor
    }//end AddItemFragment method

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false);
    }//end onCreateView method

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = (EditText) view.findViewById(R.id.et_name);
        etCategory = (EditText) view.findViewById(R.id.et_category);
        etDescription = (EditText) view.findViewById(R.id.et_description);
        etQuantity = (EditText) view.findViewById(R.id.et_quantity);
        tvButtonSubmit = (TextView) view.findViewById(R.id.tv_button_submit);
        tvButtonCancel = (TextView) view.findViewById(R.id.tv_button_cancel);

        tvButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String category = etCategory.getText().toString();
                String description = etDescription.getText().toString();
                int quantity = Integer.parseInt(etQuantity.getText().toString());

                Item item = new Item(name, description, quantity, category);

                changeToViewItems();
            }//end onClick
        });//end tvButtonSubmit.setOnClickListener

        tvButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToViewItems();
            }//end onClick
        });//end tvButtonCancel.setOnClickListener
    }//end onViewCreated method

    private void changeToViewItems() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, new ViewItemsFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("")
                .commit();
    }//end changeToViewItems method
}//end AddItemFragment class
