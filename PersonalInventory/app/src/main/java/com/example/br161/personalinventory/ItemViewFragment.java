package com.example.br161.personalinventory;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemViewFragment extends Fragment {

    private TextView tvName;

    private TextView tvQuantity;

    private TextView tvCategory;

    private TextView tvDescription;

    private CheckBox cbFavorite;

    private TextView tvEdit;

    private TextView tvDelete;

    private ArrayList<Item> items;

    public ItemViewFragment() {
        // Required empty public constructor
    }//end ItemViewFragment method

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = ParseInventory.getAllItems();
    }//end onCreate method

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_view, container, false);
    }//end onCreateView method

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvQuantity = (TextView) view.findViewById(R.id.tv_quantity);
        tvCategory = (TextView) view.findViewById(R.id.tv_category);
        tvDescription = (TextView) view.findViewById(R.id.tv_description);
        cbFavorite = (CheckBox) view.findViewById(R.id.cb_favorite);
        tvEdit = (TextView) view.findViewById(R.id.tv_edit);
        tvDelete = (TextView) view.findViewById(R.id.tv_delete);

        tvName.setText(items.get(getArguments().getInt("position")).getName());
        tvQuantity.setText(items.get(getArguments().getInt("position")).getQuantity() + "");
        tvCategory.setText(items.get(getArguments().getInt("position")).getCategory());
        tvDescription.setText(items.get(getArguments().getInt("position")).getDescription());
        cbFavorite.setChecked(items.get(getArguments().getInt("position")).isFavorite());

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO create popup
            }//end onClick
        });//end tvDelete.setOnClickListener

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditItemFragment fragment = new EditItemFragment();

                Bundle bundle = new Bundle();

                bundle.putSerializable("item", items.get(getArguments().getInt("position")));

                fragment.setArguments(bundle);

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack("")
                        .commit();
            }//end onClick
        });//end tvEdit.setOnClickListener
    }//end onViewCreated
}//end ItemViewFragment class
