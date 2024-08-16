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
public class StylesFragment extends Fragment {
    private StylesAdpaterBottom adapter;
    private List<StylesItemModel> itemList;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_styles, viewGroup, false);
        ArrayList arrayList = new ArrayList();
        this.itemList = arrayList;
        arrayList.add(new StylesItemModel("style_image_1.png", "GTA"));
        this.itemList.add(new StylesItemModel("style_image_2.png", "ANIME V2"));
        this.itemList.add(new StylesItemModel("style_image_3.png", "COSMIC"));
        this.itemList.add(new StylesItemModel("style_image_4.png", "DISNEY"));
        this.itemList.add(new StylesItemModel("style_image_5.png", "STUDIO GHIBLI"));
        this.itemList.add(new StylesItemModel("style_image_6.png", "POLAROID"));
        this.itemList.add(new StylesItemModel("style_image_7.png", "POP ART"));
        this.itemList.add(new StylesItemModel("style_image_8.png", "NO FAUVISM"));
        this.itemList.add(new StylesItemModel("style_image_9.png", "SKETCH"));
        this.itemList.add(new StylesItemModel("style_image_10.png", "PAINTING"));
        this.itemList.add(new StylesItemModel("style_image_11.png", "CUBISM"));
        this.itemList.add(new StylesItemModel("style_image_12.png", "STICKER"));
        this.itemList.add(new StylesItemModel("style_image_13.png", "ILLUSTRATION"));
        this.itemList.add(new StylesItemModel("style_image_14.png", "PAPERCUT STYLE"));
        this.itemList.add(new StylesItemModel("style_image_15.png", "COMIC BOOK"));
        this.itemList.add(new StylesItemModel("style_image_16.png", "LOGO"));
        this.itemList.add(new StylesItemModel("style_image_17.png", "ICON"));
        this.itemList.add(new StylesItemModel("style_image_18.png", "RENDER"));
        this.itemList.add(new StylesItemModel("style_image_19.png", "IMPRESSIONISM"));
        this.itemList.add(new StylesItemModel("style_image_20.png", "GRAFFITI"));
        this.itemList.add(new StylesItemModel("style_image_21.png", "SURREALISM"));
        this.itemList.add(new StylesItemModel("style_image_22.png", "MANGA"));
        this.itemList.add(new StylesItemModel("style_image_23.png", "WATERCOLOR"));
        this.itemList.add(new StylesItemModel("style_image_24.png", "DIGITAL ART"));
        this.itemList.add(new StylesItemModel("style_image_25.png", "RENAISSANCE"));
        this.itemList.add(new StylesItemModel("style_image_26.png", "ABSTRACT"));
        this.itemList.add(new StylesItemModel("style_image_27.png", "POP CULTURE"));
        this.itemList.add(new StylesItemModel("style_image_28.png", "MINIMALIST"));
        this.itemList.add(new StylesItemModel("style_image_29.png", "NEON"));
        this.itemList.add(new StylesItemModel("style_image_30.png", "VINTAGE"));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        StylesAdpaterBottom stylesAdpaterBottom = new StylesAdpaterBottom(this.itemList, new StylesAdpaterBottom.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.StylesFragment.1
            @Override // com.demo.imageaigenerator24.adapter.StylesAdpaterBottom.OnItemClickListener
            public void onItemClick(StylesItemModel stylesItemModel) {
                String text = stylesItemModel.getText();
                HomeFragment homeFragment = new HomeFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("textKey_styles", text);
                homeFragment.setArguments(bundle2);
                StylesFragment.this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).addToBackStack(null).commit();
            }
        });
        this.adapter = stylesAdpaterBottom;
        recyclerView.setAdapter(stylesAdpaterBottom);
        inflate.findViewById(R.id.backbtnsave).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.StylesFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StylesFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });
        return inflate;
    }
}
