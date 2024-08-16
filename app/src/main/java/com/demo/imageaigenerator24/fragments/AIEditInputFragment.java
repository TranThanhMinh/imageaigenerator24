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
public class AIEditInputFragment extends Fragment {
    private AiFashionFragAdapter adapterchacracter;
    private RecyclerView recyclerViewAiCharacter;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_a_i_edit_input, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_ai_edit_input);
        this.recyclerViewAiCharacter = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        AiFashionFragAdapter aiFashionFragAdapter = new AiFashionFragAdapter(generateItemList(), getActivity(), new AiFashionFragAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.AIEditInputFragment.1
            @Override // com.demo.imageaigenerator24.adapter.AiFashionFragAdapter.OnItemClickListener
            public void onItemClick(InspirationFragItemModel inspirationFragItemModel) {
                String text = inspirationFragItemModel.getText();
                GeneralCategoryImage2ImageFragment generalCategoryImage2ImageFragment = new GeneralCategoryImage2ImageFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("textKey", text);
                generalCategoryImage2ImageFragment.setArguments(bundle2);
                AIEditInputFragment.this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, generalCategoryImage2ImageFragment).addToBackStack(null).commit();
            }
        });
        this.adapterchacracter = aiFashionFragAdapter;
        this.recyclerViewAiCharacter.setAdapter(aiFashionFragAdapter);
        inflate.findViewById(R.id.btnMenu).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.AIEditInputFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AIEditInputFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeatureFragment()).commit();
            }
        });
        return inflate;
    }

    private List<InspirationFragItemModel> generateItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_1_text), "ai_edit_input_images_1.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_2_text), "ai_edit_input_images_2.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_3_text), "ai_edit_input_images_3.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_4_text), "ai_edit_input_images_4.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_5_text), "ai_edit_input_images_5.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_6_text), "ai_edit_input_images_6.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_7_text), "ai_edit_input_images_7.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_8_text), "ai_edit_input_images_8.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_9_text), "ai_edit_input_images_9.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_10_text), "ai_edit_input_images_10.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_11_text), "ai_edit_input_images_11.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_12_text), "ai_edit_input_images_12.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_13_text), "ai_edit_input_images_13.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_14_text), "ai_edit_input_images_14.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_15_text), "ai_edit_input_images_15.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_16_text), "ai_edit_input_images_16.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_17_text), "ai_edit_input_images_17.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_edit_input_18_text), "ai_edit_input_images_18.png"));
        return arrayList;
    }
}
