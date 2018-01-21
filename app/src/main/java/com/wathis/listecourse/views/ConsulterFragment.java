package com.wathis.listecourse.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wathis.listecourse.R;
import com.wathis.listecourse.models.Memo;

import java.util.LinkedList;
import java.util.List;

public class ConsulterFragment extends Fragment {


    private DatabaseReference mDatabase;
    private List<Memo> memos;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public static Fragment newInstance() {
        Fragment frag = new ConsulterFragment();
        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consulter, container, false);
    }


    private void writeNewMemo(String title, String description) {
        String key = mDatabase.child("memo").push().getKey();
        Memo memo = new Memo(title,description);
        DatabaseReference ref = mDatabase.child("memo/" + key);
        ref.setValue(memo);
    }

    private void readMemos() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memos.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Memo memo = snap.getValue(Memo.class);
                    memos.add(memo);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {System.out.println("Erreur");}
        };
        DatabaseReference mPostReference = mDatabase.child("memo");
        mPostReference.addListenerForSingleValueEvent(postListener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        memos = new LinkedList<Memo>();
        recyclerView = view.findViewById(R.id.recyclerView);
        readMemos();
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CustomAdapter(memos);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}