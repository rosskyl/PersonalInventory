package com.example.br161.personalinventory;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewItemsFragment extends Fragment {

    private TextView tvHeadingName;

    private TextView tvHeadingCategory;

    private TextView tvHeadingQuantity;

    private TextView tvHeadingFavorite;

    private RecyclerView recyclerItems;

    private ItemAdapter adapter;

    private ArrayList<Item> items;

    private int[] sort;

    public ViewItemsFragment() {
        // Required empty public constructor
    }//end ViewItemsFragment method

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_items, container, false);
    }//end onCreateView method

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = ParseInventory.getAllItems();
        Collections.sort(items, new ItemNameComparator());

        sort = new int[3];//0 if not sorted, 1 if sorted ascending and -1 if descending
        sort[0] = 1;//sorted by name
        sort[1] = 0;//sorted by quantity
        sort[2] = 0;//sorted by category
    }//end onCreate method

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerItems = (RecyclerView) view.findViewById(R.id.recycler_items);
        tvHeadingName = (TextView) view.findViewById(R.id.tv_header_name);
        tvHeadingCategory = (TextView) view.findViewById(R.id.tv_header_category);
        tvHeadingQuantity = (TextView) view.findViewById(R.id.tv_header_quantity);
        tvHeadingFavorite = (TextView) view.findViewById(R.id.tv_header_favorite);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerItems.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(items);
        recyclerItems.setAdapter(adapter);

        //TODO setup arrows after each heading to know which way it is sorted
        tvHeadingName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort[0] == 1) {
                    Collections.sort(items, Collections.reverseOrder(new ItemNameComparator()));
                    adapter.notifyDataSetChanged();
                    sort[0] = -1;
                }//end if
                else {
                    Collections.sort(items, new ItemNameComparator());
                    adapter.notifyDataSetChanged();
                    sort[0] = 1;
                    sort[1] = 0;
                    sort[2] = 0;
                }//end else
            }//end onCLick
        });//end tvHeadingName.setOnClickListener

        tvHeadingQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort[1] == 1) {
                    Collections.sort(items, Collections.reverseOrder(new ItemQuantityComparator()));
                    adapter.notifyDataSetChanged();
                    sort[1] = -1;
                }//end if
                else {
                    Collections.sort(items, new ItemQuantityComparator());
                    adapter.notifyDataSetChanged();
                    sort[0] = 0;
                    sort[1] = 1;
                    sort[2] = 0;
                }//end else
            }//end onCLick
        });//end tvHeadingQuantity.setOnClickListener

        tvHeadingCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort[2] == 1) {
                    Collections.sort(items, Collections.reverseOrder(new ItemCategoryComparator()));
                    adapter.notifyDataSetChanged();
                    sort[2] = -1;
                }//end if
                else {
                    Collections.sort(items, new ItemCategoryComparator());
                    adapter.notifyDataSetChanged();
                    sort[0] = 0;
                    sort[1] = 0;
                    sort[2] = 1;
                }//end else
            }//end onCLick
        });//end tvHeadingCategory.setOnClickListener
    }//end onViewCreated method

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ViewItemsFragment", "onDestroy");
        for (Item item : items) {
            item.saveInBackground();
        }//end for loop
    }//end onDestroy method
}//end ViewItemsFragment class
