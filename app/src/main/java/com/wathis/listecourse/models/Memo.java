package com.wathis.listecourse.models;

import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wathis.listecourse.views.ConsulterFragment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mathisdelaunay on 18/01/2018.
 */

public class Memo {

    private String titre;
    private String description;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private DatabaseReference mDatabase;

    static private Memo instance;

    static public Memo sharedInstance() {
        if (instance == null) {
            instance = new Memo();
        }
        return instance;
    }

    public Memo() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Memo(String titre, String description) {
        this.titre = titre;
        this.description = description;
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public List<Memo> loadMemos(final NotifyDelegate handler){
        final List memos = new LinkedList<Memo>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memos.clear();
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    Memo memo = snap.getValue(Memo.class);
                    memos.add(memo);
                }
                handler.notifyChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {System.out.println("Erreur");}
        };
        DatabaseReference mPostReference = mDatabase.child("memo");
        mPostReference.addListenerForSingleValueEvent(postListener);
        return memos;
    }

    public void newMemo(String title, String description) {
        String key = mDatabase.child("memo").push().getKey();
        Memo memo = new Memo(title,description);
        DatabaseReference ref = mDatabase.child("memo/" + key);
        ref.setValue(memo);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titre", titre);
        result.put("description", description);
        return result;
    }

}
