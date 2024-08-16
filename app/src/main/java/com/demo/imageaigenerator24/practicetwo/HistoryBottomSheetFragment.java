package com.demo.imageaigenerator24.practicetwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class HistoryBottomSheetFragment extends BottomSheetDialogFragment {
    private MyRecyclerViewAdapter adapter;
    private TextView emptyHistoryText;
    private List<String> historyList;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_history_bottom_sheet2, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.historyRecyclerView);
        this.emptyHistoryText = (TextView) inflate.findViewById(R.id.emptyHistoryText);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        this.adapter = myRecyclerViewAdapter;
        recyclerView.setAdapter(myRecyclerViewAdapter);
        loadHistory();
        return inflate;
    }

    private void loadHistory() {
        try {
            JSONArray jSONArray = new JSONArray(getActivity().getSharedPreferences("MyPrefs", 0).getString("history", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
            this.historyList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.historyList.add(jSONArray.getString(i));
            }
            if (this.historyList.isEmpty()) {
                this.emptyHistoryText.setVisibility(0);
                return;
            }
            this.adapter.setData(this.historyList);
            this.adapter.notifyDataSetChanged();
            this.emptyHistoryText.setVisibility(8);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
