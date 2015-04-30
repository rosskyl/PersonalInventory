package com.example.br161.personalinventory;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewItemsFragment extends Fragment {

    private RecyclerView recyclerItems;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerItems = (RecyclerView) view.findViewById(R.id.recycler_items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerItems.setLayoutManager(layoutManager);

        recyclerItems.setAdapter(new ItemAdapter());
    }//end onViewCreated method
}//end ViewItemsFragment class
