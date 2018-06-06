package com.felipefvs.myent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.felipefvs.myent.R;
import com.felipefvs.myent.UserEntsActivity;
import com.felipefvs.myent.model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends BaseAdapter{

    Context context;
    List<Favorite> favorites;

    public FavoritesAdapter(Context context, List<Favorite> favorites) {
        this.context = context;
        this.favorites = favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    @Override
    public int getCount() {
        return favorites.size();
    }

    @Override
    public Object getItem(int i) {
        return favorites.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        FavoritesViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.ent_item, viewGroup, false);
            viewHolder = new FavoritesViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (FavoritesViewHolder) view.getTag();
        }

        Favorite currentItem = (Favorite) getItem(i);
        viewHolder.text.setText(currentItem.getEntName());
        viewHolder.img.setImageResource(R.drawable.ic_star_filled);

        return view;
    }

    private class FavoritesViewHolder {
        TextView text;
        ImageButton img;

        public FavoritesViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.mEntNameTextView);
            img = (ImageButton) view.findViewById(R.id.mImageButton);
        }
    }


}
