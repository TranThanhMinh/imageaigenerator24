package com.demo.imageaigenerator24.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.InspirationFragAdapter;
import com.demo.imageaigenerator24.model.InspirationFragItemModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class InspirationFragment extends Fragment {
    private InspirationFragAdapter adapter;
    private RecyclerView recyclerView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_inspiration, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_inspire);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        InspirationFragAdapter inspirationFragAdapter = new InspirationFragAdapter(generateItemList(), getActivity(), new InspirationFragAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.InspirationFragment.1
            @Override // com.demo.imageaigenerator24.adapter.InspirationFragAdapter.OnItemClickListener
            public void onItemClick(InspirationFragItemModel inspirationFragItemModel) {
                String text = inspirationFragItemModel.getText();
                HomeFragment homeFragment = new HomeFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("textKeyInspiration", text);
                homeFragment.setArguments(bundle2);
                InspirationFragment.this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
            }
        });
        this.adapter = inspirationFragAdapter;
        this.recyclerView.setAdapter(inspirationFragAdapter);
        inflate.findViewById(R.id.back_btn_new).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.InspirationFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InspirationFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeatureFragment()).commit();
            }
        });
        return inflate;
    }

    private List<InspirationFragItemModel> generateItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_1_text), "inspiration_1.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_2_text), "inspiration_2.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_3_text), "inspiration_3.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_4_text), "inspiration_4.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_5_text), "inspiration_5.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_6_text), "inspiration_6.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_7_text), "inspiration_7.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_8_text), "inspiration_8.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_9_text), "inspiration_9.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_10_text), "inspiration_10.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_11_text), "inspiration_11.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_12_text), "inspiration_12.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_13_text), "inspiration_13.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_14_text), "inspiration_14.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_15_text), "inspiration_15.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_16_text), "inspiration_16.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_17_text), "inspiration_17.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_18_text), "inspiration_18.png"));
        return arrayList;
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new FeatureFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
