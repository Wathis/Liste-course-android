package com.wathis.listecourse.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wathis.listecourse.R;
import com.wathis.listecourse.models.Memo;
import com.wathis.listecourse.models.NotifyDelegate;

import java.nio.channels.CompletionHandler;
import java.util.List;

public class ConsulterFragment extends Fragment implements NotifyDelegate {

    private List<Memo> memos;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private CompletionHandler handler;

    static private ConsulterFragment sharedInstance;

    static public ConsulterFragment newInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ConsulterFragment();
        }
        return sharedInstance;
    }

    public void notifyChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consulter, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        memos = Memo.sharedInstance().loadMemos(this);
        recyclerView = view.findViewById(R.id.recyclerView);
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