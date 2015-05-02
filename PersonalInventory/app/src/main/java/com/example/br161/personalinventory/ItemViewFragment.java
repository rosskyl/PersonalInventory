package com.example.br161.personalinventory;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


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

    public ItemViewFragment() {
        // Required empty public constructor
    }//end ItemViewFragment method

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

        tvName.setText(getArguments().getString("name"));
        tvQuantity.setText(getArguments().getString("quantity") + "");
        tvCategory.setText(getArguments().getString("category"));
        tvDescription.setText(getArguments().getString("description"));
        cbFavorite.setChecked(getArguments().getBoolean("isFavorite"));

        //TODO add onClickListener to both buttons
    }//end onViewCreated
}//end ItemViewFragment class
