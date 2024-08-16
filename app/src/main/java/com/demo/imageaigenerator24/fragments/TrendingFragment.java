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
import com.demo.imageaigenerator24.adapter.TrendingFragAdapter;
import com.demo.imageaigenerator24.model.InspirationFragItemModel;
import com.demo.imageaigenerator24.practicetwo.EditImageFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TrendingFragment extends Fragment {
    private TrendingFragAdapter adaptertrending;
    private RecyclerView recyclerViewTrending;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_trending, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_trending);
        this.recyclerViewTrending = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        this.recyclerViewTrending.setHasFixedSize(true);
        this.recyclerViewTrending.setAdapter(this.adaptertrending);
        TrendingFragAdapter trendingFragAdapter = new TrendingFragAdapter(generateItemList(), getActivity(), new TrendingFragAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.TrendingFragment.1
            @Override // com.demo.imageaigenerator24.adapter.TrendingFragAdapter.OnItemClickListener
            public void onItemClick(InspirationFragItemModel inspirationFragItemModel) {
                String text = inspirationFragItemModel.getText();
                HomeNewFragment homeNewFragment = new HomeNewFragment();
                homeNewFragment.setInspirationText(text);
                FragmentTransaction beginTransaction = TrendingFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.fragment_container, homeNewFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
        });
        this.adaptertrending = trendingFragAdapter;
        this.recyclerViewTrending.setAdapter(trendingFragAdapter);
        inflate.findViewById(R.id.back_btn_new).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.TrendingFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TrendingFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditImageFragment()).commit();
            }
        });
        return inflate;
    }

    private List<InspirationFragItemModel> generateItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_1_text), "trending_images_1.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_2_text), "trending_images_2.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_3_text), "trending_images_3.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_4_text), "trending_images_4.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_5_text), "trending_images_5.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_6_text), "trending_images_6.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_7_text), "trending_images_7.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_8_text), "trending_images_8.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_9_text), "trending_images_9.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_10_text), "trending_images_10.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_11_text), "trending_images_11.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_12_text), "trending_images_12.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_13_text), "trending_images_13.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_14_text), "trending_images_14.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_15_text), "trending_images_15.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_16_text), "trending_images_16.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_17_text), "trending_images_17.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_trending_18_text), "trending_images_18.png"));
        return arrayList;
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new EditImageFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
