package com.demo.imageaigenerator24.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.StylesAdpaterBottom;
import com.demo.imageaigenerator24.model.StylesItemModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ModelFragment extends Fragment {
    private StylesAdpaterBottom adapter;
    private List<StylesItemModel> itemList;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_model, viewGroup, false);
        ArrayList arrayList = new ArrayList();
        this.itemList = arrayList;
        arrayList.add(new StylesItemModel("model_1.png", "RealVisXL V2.0"));
        this.itemList.add(new StylesItemModel("model_2.png", "COUNTERFITXL"));
        this.itemList.add(new StylesItemModel("model_3.png", "Copax TimeLessXL v5"));
        this.itemList.add(new StylesItemModel("model_4.png", "ProtoVision XL"));
        this.itemList.add(new StylesItemModel("model_5.png", "DynaVision XL"));
        this.itemList.add(new StylesItemModel("model_6.png", "InfiniAnimeXL"));
        this.itemList.add(new StylesItemModel("model_7.png", "ICBINP SECO"));
        this.itemList.add(new StylesItemModel("model_8.png", "Noosphere"));
        this.itemList.add(new StylesItemModel("model_9.png", "Manmaru Mix"));
        this.itemList.add(new StylesItemModel("model_10.png", "DreamShaper v7"));
        this.itemList.add(new StylesItemModel("model_11.png", "Sudachi v1"));
        this.itemList.add(new StylesItemModel("model_12.png", "Reliberate"));
        this.itemList.add(new StylesItemModel("model_13.png", "Deliberate"));
        this.itemList.add(new StylesItemModel("model_14.png", "InteriorDesign"));
        this.itemList.add(new StylesItemModel("model_15.png", "Product Design"));
        this.itemList.add(new StylesItemModel("model_16.png", "Portrait+"));
        this.itemList.add(new StylesItemModel("model_17.png", "Robo Diffusion"));
        this.itemList.add(new StylesItemModel("model_18.png", "Woolitize"));
        this.itemList.add(new StylesItemModel("model_19.png", "UltraVista v9"));
        this.itemList.add(new StylesItemModel("model_20.png", "PixelMagica XL"));
        this.itemList.add(new StylesItemModel("model_21.png", "Elysium Gate v3"));
        this.itemList.add(new StylesItemModel("model_22.png", "RetroTech Vision"));
        this.itemList.add(new StylesItemModel("model_23.png", "GoldenRatio Artify"));
        this.itemList.add(new StylesItemModel("model_24.png", "CosmoCraft XL"));
        this.itemList.add(new StylesItemModel("model_25.png", "Quantum View v4"));
        this.itemList.add(new StylesItemModel("model_26.png", "Evoke Emotion XL"));
        this.itemList.add(new StylesItemModel("model_27.png", "PixieDust Capture"));
        this.itemList.add(new StylesItemModel("model_28.png", "SpectraSight XL"));
        this.itemList.add(new StylesItemModel("model_29.png", "Lumina Focus v6"));
        this.itemList.add(new StylesItemModel("model_30.png", "EtherForm XL"));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        StylesAdpaterBottom stylesAdpaterBottom = new StylesAdpaterBottom(this.itemList, new StylesAdpaterBottom.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.ModelFragment.1
            @Override // com.demo.imageaigenerator24.adapter.StylesAdpaterBottom.OnItemClickListener
            public void onItemClick(StylesItemModel stylesItemModel) {
                String text = stylesItemModel.getText();
                HomeFragment homeFragment = new HomeFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("textKey_styles", text);
                homeFragment.setArguments(bundle2);
                ModelFragment.this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
            }
        });
        this.adapter = stylesAdpaterBottom;
        recyclerView.setAdapter(stylesAdpaterBottom);
        inflate.findViewById(R.id.backbtnsave).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.ModelFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ModelFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });
        return inflate;
    }
}
