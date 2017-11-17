package com.felipefvs.myent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipefvs.myent.R;
import com.felipefvs.myent.model.Ent;

/**
 * Created by FELIPESIQUEIRAB20588 on 17/11/2017.
 */

public class EntAdapter extends RecyclerView.Adapter<EntAdapter.EntViewHolder>{

    private final int mNumberEnts;
    private Ent[] mEnts;

    @Override
    public EntAdapter.EntViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.ent_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);

        return new EntAdapter.EntViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntAdapter.EntViewHolder holder, int position) {

        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public EntAdapter(int numberEnts)
    {
        this.mNumberEnts = numberEnts;
        mEnts = new Ent[numberEnts];
    }

    public void setEnts(Ent[] ents)
    {
        mEnts = ents;
        notifyDataSetChanged();
    }

    public class EntViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;

        public EntViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.mEntNameTextView);
        }

        @Override
        public void onClick(View view) {

        }

        void bind (int index) {

            Ent ent = mEnts[index];

            name.setText(ent.getName());

        }
    }
}
