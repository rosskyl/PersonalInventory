package com.example.br161.personalinventory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by al1694bc on 4/22/2015.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener listener;

        private TextView tvname;

        private TextView tvCategory;

        private CheckBox cbFavorite;

        public ViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;

            tvname = (TextView) itemView.findViewById(R.id.tv_name);
            tvCategory = (TextView) itemView.findViewById(R.id.tv_category);
            cbFavorite = (CheckBox) itemView.findViewById(R.id.cb_favorite);

            //TODO add listener
        }//end ViewHolder method

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getPosition());
        }//end onClick

        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }//end ItemClickListener interface
    }//end ViewHolder inner class
}//end ItemAdapter class
