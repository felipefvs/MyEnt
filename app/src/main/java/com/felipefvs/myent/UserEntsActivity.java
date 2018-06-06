package com.felipefvs.myent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.felipefvs.myent.adapter.EntAdapter;
import com.felipefvs.myent.adapter.FavoritesAdapter;
import com.felipefvs.myent.database.FirebaseInterface;
import com.felipefvs.myent.model.Ent;
import com.felipefvs.myent.model.Favorite;
import com.felipefvs.myent.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserEntsActivity extends AppCompatActivity {

    private ListView mFavoritesListView;
    FavoritesAdapter mAdapter;

    private List<User> mUserList;
    private List<Favorite> mUserFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ents);

        mFavoritesListView = (ListView)findViewById(R.id.mFavoritesListView);

        mUserList = new ArrayList<>();
        mUserFavorites = new ArrayList<>();

        mAdapter = new FavoritesAdapter(this, mUserFavorites);
        mFavoritesListView.setAdapter(mAdapter);
        mFavoritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView imgView = (ImageView)view.findViewById(R.id.mImageButton);

                FirebaseUser currUser = FirebaseInterface.getFirebaseAuth().getCurrentUser();
                String userId = currUser.getUid();

                int entId = (int) imgView.getTag();
                String entName = ((TextView)view.findViewById(R.id.mEntNameTextView)).getText().toString();

                DatabaseReference currentUserFavorites = FirebaseInterface.getFirebase().child("users").child(userId).child("favorites");

                boolean isFavorite = false;
                int row = 0;
                for(Favorite f : mUserFavorites) {
                    if(f.getEntId() == entId) {
                        isFavorite = true;
                        break;
                    }
                    row++;
                }

                Favorite f = new Favorite();

                f.setEntId(entId);
                f.setEntName(entName);

                if(!isFavorite) {
                    mUserFavorites.add(f);
                    imgView.setImageResource(R.drawable.ic_star_filled);
                } else {
                    mUserFavorites.remove(row);
                    imgView.setImageResource(R.drawable.ic_star);
                }

                currentUserFavorites.setValue(mUserFavorites);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currUser = FirebaseInterface.getFirebaseAuth().getCurrentUser();
        String userId = currUser.getUid();

        DatabaseReference usersRef = FirebaseInterface.getFirebase().child("users");

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUserList.clear();
                for(DataSnapshot u : dataSnapshot.getChildren()) {
                    User user = u.getValue(User.class);

                    mUserList.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference currentUserFavorites = FirebaseInterface.getFirebase().child("users").child(userId).child("favorites");

        currentUserFavorites.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUserFavorites.clear();
                for(DataSnapshot u : dataSnapshot.getChildren()) {
                    Favorite favorite = u.getValue(Favorite.class);

                    mUserFavorites.add(favorite);
                }

                mAdapter.setFavorites(mUserFavorites);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
