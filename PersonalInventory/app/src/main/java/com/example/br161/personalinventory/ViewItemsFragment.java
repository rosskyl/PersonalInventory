package com.example.br161.personalinventory;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    private RecyclerView recyclerItems;

    private ProgressBar progressBar;

    private ImageView ivNameSort;

    private ImageView ivQuantitySort;

    private ImageView ivCategorySort;

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

        sort = new int[3];//0 if not sorted, 1 if sorted ascending and -1 if descending

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        sort[0] = preferences.getInt("nameSort", 1);//sorted by name
        sort[1] = preferences.getInt("quantitySort", 0);//sorted by quantity
        sort[2] = preferences.getInt("categorySort", 0);//sorted by category
    }//end onCreate method

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerItems = (RecyclerView) view.findViewById(R.id.recycler_items);
        tvHeadingName = (TextView) view.findViewById(R.id.tv_header_name);
        tvHeadingCategory = (TextView) view.findViewById(R.id.tv_header_category);
        tvHeadingQuantity = (TextView) view.findViewById(R.id.tv_header_quantity);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        ivNameSort = (ImageView) view.findViewById(R.id.iv_name_sort);
        ivCategorySort = (ImageView) view.findViewById(R.id.iv_category_sort);
        ivQuantitySort = (ImageView) view.findViewById(R.id.iv_quantity_sort);

        new LoadItemsTask().execute();
    }//end onViewCreated method

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("nameSort", sort[0]);
        editor.putInt("quantitySort", sort[1]);
        editor.putInt("categorySort", sort[2]);

        editor.commit();
    }//end onDestroy method

    private void setUpOnClickListeners() {
        tvHeadingName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sort[0] == 1) {
                    Collections.sort(items, Collections.reverseOrder(new ItemNameComparator()));
                    adapter.notifyDataSetChanged();
                    sort[0] = -1;
                    ivNameSort.setVisibility(View.VISIBLE);
                    ivNameSort.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                }//end if
                else {
                    Collections.sort(items, new ItemNameComparator());
                    adapter.notifyDataSetChanged();
                    sort[0] = 1;
                    sort[1] = 0;
                    sort[2] = 0;
                    ivNameSort.setVisibility(View.VISIBLE);
                    ivNameSort.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                    ivQuantitySort.setVisibility(View.INVISIBLE);
                    ivCategorySort.setVisibility(View.INVISIBLE);
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
                    ivQuantitySort.setVisibility(View.VISIBLE);
                    ivQuantitySort.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                }//end if
                else {
                    Collections.sort(items, new ItemQuantityComparator());
                    adapter.notifyDataSetChanged();
                    sort[0] = 0;
                    sort[1] = 1;
                    sort[2] = 0;
                    ivQuantitySort.setVisibility(View.VISIBLE);
                    ivQuantitySort.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                    ivNameSort.setVisibility(View.INVISIBLE);
                    ivCategorySort.setVisibility(View.INVISIBLE);
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
                    ivCategorySort.setVisibility(View.VISIBLE);
                    ivCategorySort.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                }//end if
                else {
                    Collections.sort(items, new ItemCategoryComparator());
                    adapter.notifyDataSetChanged();
                    sort[0] = 0;
                    sort[1] = 0;
                    sort[2] = 1;
                    ivCategorySort.setVisibility(View.VISIBLE);
                    ivCategorySort.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
                    ivNameSort.setVisibility(View.INVISIBLE);
                    ivQuantitySort.setVisibility(View.INVISIBLE);
                }//end else
            }//end onCLick
        });//end tvHeadingCategory.setOnClickListener
    }//end setUpOnClickListeners method

    class LoadItemsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            items = ParseInventory.getAllItems();

            return null;
        }//end doInBackground method

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

            recyclerItems.setLayoutManager(layoutManager);

            adapter = new ItemAdapter(items, getFragmentManager());
            recyclerItems.setAdapter(adapter);

            recyclerItems.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            if (sort[0] == 1) {
                Collections.sort(items, new ItemNameComparator());
                ivNameSort.setVisibility(View.VISIBLE);
                ivNameSort.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
            }//end if
            else if (sort[0] == -1) {
                Collections.sort(items, Collections.reverseOrder(new ItemNameComparator()));
                ivNameSort.setVisibility(View.VISIBLE);
                ivNameSort.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
            }//end else if
            else if (sort[1] == 1) {
                Collections.sort(items, new ItemQuantityComparator());
                ivQuantitySort.setVisibility(View.VISIBLE);
                ivQuantitySort.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
            }//end else if
            else if (sort[1] == -1) {
                Collections.sort(items, Collections.reverseOrder(new ItemQuantityComparator()));
                ivQuantitySort.setVisibility(View.VISIBLE);
                ivQuantitySort.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
            }//end else if
            else if (sort[2] == 1) {
                Collections.sort(items, new ItemCategoryComparator());
                ivCategorySort.setVisibility(View.VISIBLE);
                ivCategorySort.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);
            }//end else if
            else if (sort[2] == -1) {
                Collections.sort(items, Collections.reverseOrder(new ItemCategoryComparator()));
                ivCategorySort.setVisibility(View.VISIBLE);
                ivCategorySort.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
            }//end else if

            setUpOnClickListeners();
        }//end onPostExecute method
    }//end LoadItemsTask inner class

}//end ViewItemsFragment class
