package com.demo.imageaigenerator24.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.CustomPagerAdapter;
import com.demo.imageaigenerator24.adapter.InspirationAdapter;
import com.demo.imageaigenerator24.interafaces.OnItemClickListenerinspiration;
import com.demo.imageaigenerator24.model.InspirationItem;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class FeatureFragment extends Fragment implements OnItemClickListenerinspiration {
    private static final long AUTO_SLIDE_DELAY = 1000;
    private InspirationAdapter adapter;
    LinearLayout indicatorLayout;
    public CustomPagerAdapter pagerAdapter;
    SharedPrefsHelper prefs;
    private RecyclerView recyclerView;
    private Timer timer;
    public ViewPager viewPager;
    public int currentPage = 0;
    final int delay = 2000;
    final Handler handler = new Handler();
    int[] imageResources = new int[0];
    public int[] layouts = {R.layout.slide_ai_fashion, R.layout.slide_ai_character, R.layout.slide_ai_edit_input, R.layout.slide_ai_hd_images};

    static int access$408(FeatureFragment featureFragment) {
        int i = featureFragment.currentPage;
        featureFragment.currentPage = i + 1;
        return i;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_feature_new, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.prefs = SharedPrefsHelper.getInstance(getContext());
        this.viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        final ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.imageViewFlipper);
        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
        this.handler.postDelayed(new Runnable() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.1
            @Override // java.lang.Runnable
            public void run() {
                ViewFlipper viewFlipper2 = viewFlipper;
                viewFlipper2.setDisplayedChild((viewFlipper2.getDisplayedChild() + 1) % viewFlipper.getChildCount());
                FeatureFragment.this.handler.postDelayed(this, 2000L);
            }
        }, 2000L);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int currentItem = FeatureFragment.this.viewPager.getCurrentItem();
                if (currentItem == 0) {
                    FeatureFragment.this.openAIFashionFragment();
                    return;
                }
                if (currentItem == 1) {
                    FeatureFragment.this.openAICharacterFragment();
                } else if (currentItem == 2) {
                    FeatureFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AIEditInputFragment()).commit();
                } else if (currentItem == 3) {
                    FeatureFragment.this.openAIHDImageFragment();
                }
            }
        };
        view.findViewById(R.id.btn_trending).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FeatureFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrendingFragment()).commit();
            }
        });
        view.findViewById(R.id.tryBtn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FeatureFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrendingFragment()).commit();
            }
        });
        this.indicatorLayout = (LinearLayout) view.findViewById(R.id.indicatorLayout);
        for (int i = 0; i < this.layouts.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.indicator_unselected);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(8, 0, 8, 0);
            imageView.setLayoutParams(layoutParams);
            this.indicatorLayout.addView(imageView);
        }
        updateIndicator(this.currentPage);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                FeatureFragment.this.currentPage = i2;
                FeatureFragment.this.updateIndicator(i2);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
                if (i2 != 0) {
                    return;
                }
                if (FeatureFragment.this.currentPage == FeatureFragment.this.pagerAdapter.getCount() - 1) {
                    FeatureFragment.this.viewPager.setCurrentItem(0, false);
                    FeatureFragment.this.currentPage = 0;
                } else if (FeatureFragment.this.currentPage == 0) {
                    FeatureFragment.this.viewPager.setCurrentItem(FeatureFragment.this.pagerAdapter.getCount() - 1, false);
                    FeatureFragment featureFragment = FeatureFragment.this;
                    FeatureFragment.this.currentPage = featureFragment.pagerAdapter.getCount() - 1;
                }
            }
        });
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getActivity(), this.layouts, onClickListener);
        this.pagerAdapter = customPagerAdapter;
        this.viewPager.setAdapter(customPagerAdapter);
        startAutoSlide();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        InspirationAdapter inspirationAdapter = new InspirationAdapter(getContext(), generateItemList(), this);
        this.adapter = inspirationAdapter;
        this.recyclerView.setAdapter(inspirationAdapter);
    }

    public void updateIndicator(int i) {
        for (int i2 = 0; i2 < this.indicatorLayout.getChildCount(); i2++) {
            ImageView imageView = (ImageView) this.indicatorLayout.getChildAt(i2);
            if (i2 == i) {
                imageView.setImageResource(R.drawable.indicator_selected);
            } else {
                imageView.setImageResource(R.drawable.indicator_unselected);
            }
        }
    }

    private List<InspirationItem> generateItemList() {
        return new ArrayList();
    }

    public void openAIFashionFragment() {
        AIFashionFragment aIFashionFragment = new AIFashionFragment();
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, aIFashionFragment);
        beginTransaction.commit();
    }

    public void openAICharacterFragment() {
        AICharacterFragment aICharacterFragment = new AICharacterFragment();
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, aICharacterFragment);
        beginTransaction.commit();
    }

    private void openAIEditInputFragment() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditYourImageWithModelFeatureBtnFragment()).addToBackStack(null).commit();
    }

    public void openAIHDImageFragment() {
        AIHDImageFragment aIHDImageFragment = new AIHDImageFragment();
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, aIHDImageFragment);
        beginTransaction.commit();
    }

    private void startAutoSlide() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.6
            @Override // java.lang.Runnable
            public void run() {
                if (FeatureFragment.this.currentPage == FeatureFragment.this.layouts.length) {
                    FeatureFragment.this.currentPage = 0;
                }
                FeatureFragment.this.viewPager.setCurrentItem(FeatureFragment.access$408(FeatureFragment.this), true);
            }
        };
        Timer timer = new Timer();
        this.timer = timer;
        timer.schedule(new TimerTask() { // from class: com.demo.imageaigenerator24.fragments.FeatureFragment.7
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                handler.post(runnable);
            }
        }, AUTO_SLIDE_DELAY, AUTO_SLIDE_DELAY);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.demo.imageaigenerator24.interafaces.OnItemClickListenerinspiration
    public void onItemClick(InspirationItem inspirationItem) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InspirationFragment()).commit();
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ExitFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
