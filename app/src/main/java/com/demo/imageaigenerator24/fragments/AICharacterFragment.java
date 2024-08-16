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
import com.demo.imageaigenerator24.adapter.AiCharcterFragAdapter;
import com.demo.imageaigenerator24.model.InspirationFragItemModel;
import com.demo.imageaigenerator24.practicetwo.EditImageFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AICharacterFragment extends Fragment {
    private AiCharcterFragAdapter adapterchacracter;
    private RecyclerView recyclerViewAiCharacter;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_a_i_character, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_ai_character);
        this.recyclerViewAiCharacter = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        AiCharcterFragAdapter aiCharcterFragAdapter = new AiCharcterFragAdapter(generateItemList(), getActivity(), new AiCharcterFragAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.AICharacterFragment.1
            @Override // com.demo.imageaigenerator24.adapter.AiCharcterFragAdapter.OnItemClickListener
            public void onItemClick(InspirationFragItemModel inspirationFragItemModel) {
                String text = inspirationFragItemModel.getText();
                HomeNewFragment homeNewFragment = new HomeNewFragment();
                homeNewFragment.setInspirationText(text);
                FragmentTransaction beginTransaction = AICharacterFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.fragment_container, homeNewFragment);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
            }
        });
        this.adapterchacracter = aiCharcterFragAdapter;
        this.recyclerViewAiCharacter.setAdapter(aiCharcterFragAdapter);
        inflate.findViewById(R.id.btnMenu).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.AICharacterFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AICharacterFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditImageFragment()).commit();
            }
        });
        return inflate;
    }

    private List<InspirationFragItemModel> generateItemList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_1_text), "character_images_1.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_2_text), "character_images_2.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_3_text), "character_images_3.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_4_text), "character_images_4.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_5_text), "character_images_5.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_6_text), "character_images_6.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_7_text), "character_images_7.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_8_text), "character_images_8.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_9_text), "character_images_9.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_10_text), "character_images_10.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_11_text), "character_images_11.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_12_text), "character_images_12.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_13_text), "character_images_13.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_14_text), "character_images_14.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_15_text), "character_images_15.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_16_text), "character_images_16.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_17_text), "character_images_17.png"));
        arrayList.add(new InspirationFragItemModel(getString(R.string.item_character_18_text), "character_images_18.png"));
        return arrayList;
    }
}
