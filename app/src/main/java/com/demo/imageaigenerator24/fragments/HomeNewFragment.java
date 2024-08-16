package com.demo.imageaigenerator24.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.ads.MyApplication;
import com.demo.imageaigenerator24.practicetwo.CollectedData;
import com.demo.imageaigenerator24.practicetwo.DataAdapter;
import com.demo.imageaigenerator24.practicetwo.DataModel;
import com.demo.imageaigenerator24.practicetwo.Image2ImageFragment;
import com.demo.imageaigenerator24.practicetwo.SharedViewModel;
import com.demo.imageaigenerator24.practicetwo.SpeechToTextListener;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HomeNewFragment extends Fragment implements SpeechToTextListener, DataAdapter.OnGenerateClickListener, DataAdapter.OnImage2ImageClickListener {
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static CollectedData collectedData = new CollectedData();
    View arrow_choose_settings;
    public DataAdapter dataAdapter;
    public DrawerLayout drawerLayout;
    ImageView imageView2ImageBtn;
    ImageView lock_layout;
    private NavigationView navView;
    SharedPrefsHelper prefs;
    RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private String inspirationText = null;
    private String outputFormat = "png";

    public void setInspirationText(String str) {
        this.inspirationText = str;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("checkiyty", "onCreateView: HomefragmentNEw");
        return layoutInflater.inflate(R.layout.fragment_home_new, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.d("checkingonceaty", "onViewCreated: Homenrefragemnt ");
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        clearBase64ImageInViewModel();
        this.imageView2ImageBtn = (ImageView) view.findViewById(R.id.image2image_btn);
        this.lock_layout = (ImageView) view.findViewById(R.id.lock_layout);
        this.arrow_choose_settings = view.findViewById(R.id.arrow_choose_settings);
        view.findViewById(R.id.animation_view).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + HomeNewFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US")));
            }
        });
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        this.navView = (NavigationView) view.findViewById(R.id.nav_view);
        DataAdapter dataAdapter = new DataAdapter(getActivity(), this.recyclerView, this, this, collectedData, this, this.prefs);
        this.dataAdapter = dataAdapter;
        dataAdapter.setSharedText("Imagine a woman handcrafted by the Gods: She emerging from molten lava, levitating above the heart of a volcano. She in a otherworldly dress made by fire and ashes that shapes her body perfectly. Her face resembles of a beautiful woman with some magical elements. Her hair is fiery red and is fueled by the very energy of life itself, a throwback to The Creation at the same time connecting with today nature. Every element of this portrait should come together naturally creating that most beautiful concept art ever created. ");
        this.dataAdapter.setData(getMockData());
        String str = this.inspirationText;
        if (str != null) {
            this.dataAdapter.setSharedText(str);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.2
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return HomeNewFragment.this.dataAdapter.getItemViewType(i) == 7 ? 1 : 2;
            }
        });
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.dataAdapter);
        view.findViewById(R.id.btn_frag_Drawer).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (HomeNewFragment.this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    HomeNewFragment.this.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        view.findViewById(R.id.appCompatButtonPro).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
            }
        });
        this.navView.setLayoutParams(new DrawerLayout.LayoutParams(this.navView.getLayoutParams()) { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.5
            {
                this.gravity = GravityCompat.START;
            }
        });
        this.navView.setItemIconTintList(null);
        this.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.6
            @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.more_apps) {
                    try {
                        HomeNewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + MyApplication.MoreApps)));
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        Toast.makeText(HomeNewFragment.this.getActivity(), "Google Play Store is not available.", 0).show();
                        return true;
                    }
                }
                if (itemId == R.id.share_app) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", "AI Image Generator");
                    intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + HomeNewFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US");
                    intent.addFlags(536870912);
                    HomeNewFragment.this.startActivity(Intent.createChooser(intent, "Share with"));
                    return true;
                }
                if (itemId == R.id.rate_app) {
                    try {
                        HomeNewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + HomeNewFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US")));
                        return true;
                    } catch (ActivityNotFoundException unused2) {
                        Toast.makeText(HomeNewFragment.this.getActivity(), "Google Play Store is not available.", 0).show();
                        return true;
                    }
                }
                if (itemId == R.id.policy) {
                    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(MyApplication.PrivacyPolicy));
                    intent2.setPackage("com.android.chrome");
                    HomeNewFragment.this.startActivity(intent2);
                    return true;
                }
                if (itemId != R.id.termsandconditions) {
                    if (itemId == R.id.BackImageBtnDrawer) {
                        HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    return true;
                }
                Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(MyApplication.TermsandConditions));
                intent3.setPackage("com.android.chrome");
                HomeNewFragment.this.startActivity(intent3);
                return true;
            }
        });
        ((AppCompatButton) view.findViewById(R.id.rate_app)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                try {
                    HomeNewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + HomeNewFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US")));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(HomeNewFragment.this.getActivity(), "Google Play Store is not available.", 0).show();
                }
            }
        });
        ((AppCompatButton) view.findViewById(R.id.share_app)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "AI Anime Generator");
                intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + HomeNewFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US");
                intent.addFlags(536870912);
                HomeNewFragment.this.startActivity(Intent.createChooser(intent, "Share with"));
            }
        });
        ((AppCompatButton) view.findViewById(R.id.more_apps)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                try {
                    HomeNewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + MyApplication.MoreApps)));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(HomeNewFragment.this.getActivity(), "Google Play Store is not available.", 0).show();
                }
            }
        });
        ((AppCompatButton) view.findViewById(R.id.policy)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(MyApplication.PrivacyPolicy));
                intent.setPackage("com.android.chrome");
                HomeNewFragment.this.startActivity(intent);
            }
        });
        ((AppCompatButton) view.findViewById(R.id.termsandconditions)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(MyApplication.TermsandConditions));
                intent.setPackage("com.android.chrome");
                HomeNewFragment.this.startActivity(intent);
            }
        });
        ((AppCompatButton) view.findViewById(R.id.update_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeNewFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                HomeNewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + HomeNewFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US")));
            }
        });
    }

    private void clearBase64ImageInViewModel() {
        ((SharedViewModel) new ViewModelProvider(requireActivity()).get(SharedViewModel.class)).setBase64Image(null);
    }

    private List<DataModel> getMockData() {
        ArrayList arrayList = new ArrayList();
        getResources();
        arrayList.add(new DataModel.AItitle(getString(R.string.enter_prompt)));
        arrayList.add(new DataModel.AIEditText(R.drawable.clear_btn_bg));
        arrayList.add(new DataModel.AItitle(getString(R.string.spinners)));
        arrayList.add(new DataModel.AISamplers());
        arrayList.add(new DataModel.AItitle(getString(R.string.aspect_ratios)));
        arrayList.add(new DataModel.AIRatios());
        arrayList.add(new DataModel.AItitle(getString(R.string.model_and_style)));
        arrayList.add(new DataModel.AIModelAndStyles());
        arrayList.add(new DataModel.AItitle(getString(R.string.advanced_settings)));
        arrayList.add(new DataModel.AIAdvanceSettings());
        arrayList.add(new DataModel.AIGenerate());
        arrayList.add(new DataModel.AItitle(getString(R.string.inspiration)));
        for (int i = 1; i <= 54; i++) {
            arrayList.add(new DataModel.AIImage(getString(getResources().getIdentifier("item_" + i + "_text", TypedValues.Custom.S_STRING, getActivity().getPackageName())), "inspiration__" + i + ".png"));
        }
        return arrayList;
    }

    @Override // com.demo.imageaigenerator24.practicetwo.SpeechToTextListener
    public void requestSpeechToTextPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.RECORD_AUDIO"}, 101);
    }

    @Override // com.demo.imageaigenerator24.practicetwo.SpeechToTextListener
    public void startSpeechToText() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        startActivityForResult(intent, 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr.length > 0 && iArr[0] == 0) {
            return;
        }
        Toast.makeText(getActivity(), "Storage permission is required to save the file", 0).show();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.SpeechToTextListener
    public void onSpeechResult(String str) {
        this.dataAdapter.setSpokenText(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            DataAdapter.speechToTextInterface.onSpeechResult(intent.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
        }
    }

    private void displaySavedData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", 0);
        String string = sharedPreferences.getString("savedText", "DefaultText");
        String string2 = sharedPreferences.getString("savedDateTime", "DefaultDateTime");
        Log.d("SavedData", "Text: " + string);
        Log.d("SavedData", "DateTime: " + string2);
    }

    public void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = requireActivity().getLayoutInflater().inflate(R.layout.custom_inetrnet_dailog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView12);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        if (create.getWindow() != null) {
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeNewFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        create.show();
    }

    public void proceedWithDownload() {
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("model", collectedData.getModelAndStyle());
        bundle.putString("prompt", collectedData.getPrompt());
        bundle.putString("negativePrompt", collectedData.getNegativePrompt());
        bundle.putInt("height", collectedData.getHeight());
        bundle.putInt("width", collectedData.getWidth());
        bundle.putString("scheduler", collectedData.getScheduler());
        bundle.putString("outputFormat", this.outputFormat);
        bundle.putInt("steps", collectedData.getSteps());
        bundle.putInt("seed", collectedData.getSeed());
        bundle.putFloat("guidance", collectedData.getGuidance());
        bundle.putString("negativeStyle", collectedData.getNegativeStyle());
        bundle.putString("sampler", collectedData.getSampler());
        bundle.putString("aspectRatio", collectedData.getAspectRatio());
        downloadFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, downloadFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.DataAdapter.OnGenerateClickListener
    public void onGenerateClick() {
        proceedWithDownload();
    }

    @Override // com.demo.imageaigenerator24.practicetwo.DataAdapter.OnImage2ImageClickListener
    public void onImage2ImageClick() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Image2ImageFragment()).commit();
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ExitFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
