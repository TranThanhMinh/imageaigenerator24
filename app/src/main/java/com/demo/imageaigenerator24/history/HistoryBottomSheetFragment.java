package com.demo.imageaigenerator24.history;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.model.HistoryItem;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class HistoryBottomSheetFragment extends BottomSheetDialogFragment {
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;

    public static HistoryBottomSheetFragment newInstance() {
        return new HistoryBottomSheetFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_history_bottom_sheet, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.historyRecyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        HistoryAdapter historyAdapter = new HistoryAdapter(loadHistoryFromSharedPreferences());
        this.historyAdapter = historyAdapter;
        this.recyclerView.setAdapter(historyAdapter);
    }

    private HistoryItem loadHistoryFromSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", 0);
        Gson gson = new Gson();
        String string = sharedPreferences.getString("savedHistory", null);
        Type type = new TypeToken<HistoryItem>() { // from class: com.demo.imageaigenerator24.history.HistoryBottomSheetFragment.1
        }.getType();
        if (string != null) {
            return (HistoryItem) gson.fromJson(string, type);
        }
        return null;
    }
}
