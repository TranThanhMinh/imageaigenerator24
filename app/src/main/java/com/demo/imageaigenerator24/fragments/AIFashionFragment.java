package com.demo.imageaigenerator24.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.AiFashionFragAdapter;
import com.demo.imageaigenerator24.model.InspirationFragItemModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AIFashionFragment extends Fragment {
    private AiFashionFragAdapter adapterchacracter;
    private RecyclerView recyclerViewAiCharacter;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_a_i_fashion, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_ai_fashion);
        this.recyclerViewAiCharacter = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        AiFashionFragAdapter aiFashionFragAdapter = new AiFashionFragAdapter(generateItemList(), getActivity(), new AiFashionFragAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.AIFashionFragment.1
            @Override // com.demo.imageaigenerator24.adapter.AiFashionFragAdapter.OnItemClickListener
            public void onItemClick(InspirationFragItemModel inspirationFragItemModel) {
                String text = inspirationFragItemModel.getText();
                GeneralCategoryImage2ImageFragment generalCategoryImage2ImageFragment = new GeneralCategoryImage2ImageFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("textKey", text);
                generalCategoryImage2ImageFragment.setArguments(bundle2);
                AIFashionFragment.this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, generalCategoryImage2ImageFragment).addToBackStack(null).commit();
            }
        });
        this.adapterchacracter = aiFashionFragAdapter;
        this.recyclerViewAiCharacter.setAdapter(aiFashionFragAdapter);
        inflate.findViewById(R.id.btnMenu).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.AIFashionFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AIFashionFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeatureFragment()).commit();
            }
        });
        return inflate;
    }

    private List<InspirationFragItemModel> generateItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_1_text), "fashion_image_1.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_2_text), "fashion_image_2.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_3_text), "fashion_image_3.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_4_text), "fashion_image_4.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_5_text), "fashion_image_5.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_6_text), "fashion_image_6.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_7_text), "fashion_image_7.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_8_text), "fashion_image_8.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_9_text), "fashion_image_9.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_10_text), "fashion_image_10.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_11_text), "fashion_image_11.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_12_text), "fashion_image_12.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_13_text), "fashion_image_13.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_14_text), "fashion_image_14.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_15_text), "fashion_image_15.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_16_text), "fashion_image_16.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_17_text), "fashion_image_17.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_fashion_18_text), "fashion_image_18.png"));
        return arrayList;
    }
}
