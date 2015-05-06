package com.example.br161.personalinventory;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by al1694bc on 4/22/2015.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<Item> items;

    private FragmentManager fragmentManager;

    public ItemAdapter(ArrayList<Item> items, FragmentManager fragmentManager) {
        this.items = items;
        this.fragmentManager = fragmentManager;
    }//end ItemAdapter method

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);

        ViewHolder.ItemClickListener listener = new ViewHolder.ItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                ItemViewFragment fragment = new ItemViewFragment();

                Bundle bundle = new Bundle();

                bundle.putSerializable("item", items.get(position));

                fragment.setArguments(bundle);

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }//end onItemClick
        };//end ViewHolder.ItemClickListener listener = new ViewHolder.ItemClickListener()

        ViewHolder.CheckboxChangedListener checkListener = new ViewHolder.CheckboxChangedListener() {
            @Override
            public void onCheckboxChecked(View view, boolean isChecked, int position) {
                items.get(position).setFavorite(isChecked);
                items.get(position).saveInBackground();
            }//end onCheckBoxChecked
        };//end ViewHolder.CheckboxChangedListener checkListener = new ViewHolder.CheckboxChangedListener()

        ViewHolder viewHolder = new ViewHolder(view, listener, checkListener);

        return viewHolder;
    }//end onCreateViewHolder

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(items.get(position).getName());
        holder.tvQuantity.setText(items.get(position).getQuantity() + "");
        holder.tvCategory.setText(items.get(position).getCategory());

        if (items.get(position).isFavorite()) {
            holder.cbFavorite.setChecked(true);
        }//end if
        else {
            holder.cbFavorite.setChecked(false);
        }//end else

    }//end onBindViewHolder method

    @Override
    public int getItemCount() {
        return items.size();
    }//end getItemCount method

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener {

        private ItemClickListener clickListener;

        private CheckboxChangedListener checkedListener;

        private TextView tvName;

        private TextView tvQuantity;

        private TextView tvCategory;

        private CheckBox cbFavorite;

        public ViewHolder(View itemView, ItemClickListener listener, CheckboxChangedListener checkboxChangedListener) {
            super(itemView);
            this.clickListener = listener;
            this.checkedListener = checkboxChangedListener;


            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvQuantity = (TextView) itemView.findViewById(R.id.tv_quantity);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            cbFavorite = (CheckBox) itemView.findViewById(R.id.cb_favorite);

            LinearLayout linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            linearLayout.setOnClickListener(this);

            cbFavorite.setOnCheckedChangeListener(this);
        }//end ViewHolder method

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }//end onClick

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            checkedListener.onCheckboxChecked(buttonView, isChecked, getPosition());
        }

        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }//end ItemClickListener interface

        public interface CheckboxChangedListener {
            void onCheckboxChecked(View view, boolean isChecked, int position);
        }
    }//end ViewHolder inner class
}//end ItemAdapter class
