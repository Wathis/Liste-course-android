package com.wathis.listecourse.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wathis.listecourse.R;
import com.wathis.listecourse.models.Memo;

import java.util.LinkedList;
import java.util.List;

public class NouvelleNoteFragment extends Fragment {

    private Button buttonAdd;
    private EditText titreEditText;
    private EditText descriptionEditText;
    static private NouvelleNoteFragment sharedInstance;

    static public NouvelleNoteFragment newInstance() {
        if (sharedInstance == null) {
            sharedInstance = new NouvelleNoteFragment();
        }
        return sharedInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_nouvelle_note, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titreEditText = (EditText) view.findViewById(R.id.titleMemo);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionMemo);
        buttonAdd = (Button) view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titre = titreEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                if (titre != null && description != null)  {
                    Memo.sharedInstance().newMemo(titre,description);
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}