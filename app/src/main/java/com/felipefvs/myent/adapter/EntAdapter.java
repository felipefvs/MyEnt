package com.felipefvs.myent.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipefvs.myent.R;
import com.felipefvs.myent.model.Ent;
import com.felipefvs.myent.model.Favorite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FELIPESIQUEIRAB20588 on 17/11/2017.
 */

public class EntAdapter extends RecyclerView.Adapter<EntAdapter.EntViewHolder>{

    private List<Ent> mEntList;
    private List<Favorite> mUserFavorites;

    /* Callback for list item click events */
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public class EntViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView imgView;

        EntViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.mEntNameTextView);
            imgView = (ImageView) view.findViewById(R.id.mImageButton);

            //view.setOnClickListener(this);
            imgView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (view == imgView) {
                maskAsFavorite(this);
            }
        }
    }

    private OnItemClickListener mOnItemClickListener;

    private void maskAsFavorite(EntViewHolder holder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public EntAdapter(List<Favorite> userFavorites) {
        this.mUserFavorites = userFavorites;
        this.mEntList = new ArrayList<>();
    }

    public void setEnts(List<Ent> ents) {
        this.mEntList = ents;
    }

    @Override
    public EntViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ent_item, parent, false);

        return new EntViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EntViewHolder holder, int position) {

        Ent ent = mEntList.get(position);
        int entId = ent.getId();
        String entName = ent.getName();
        holder.title.setText(ent.getName());
        holder.imgView.setTag(ent.getId());

        for(Favorite f : mUserFavorites) {
            if(f.getEntId() == entId) {
                holder.imgView.setImageResource(R.drawable.ic_star_filled);
                break;
            }

        }



    }

    @Override
    public int getItemCount() {
        return mEntList.size();
    }
}
