package com.demo.imageaigenerator24.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.AspectRatioAdapter;
import com.demo.imageaigenerator24.adapter.ItemAdapter;
import com.demo.imageaigenerator24.adapter.ItemModelAdapter;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.ads.MyApplication;
import com.demo.imageaigenerator24.history.HistoryBottomSheetFragment;
import com.demo.imageaigenerator24.model.AspectRatioModel;
import com.demo.imageaigenerator24.model.HistoryItem;
import com.demo.imageaigenerator24.model.ItemModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.demo.imageaigenerator24.utils.ConnectivityChecker;
import com.demo.imageaigenerator24.utils.PromptGenerator;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class HomeFragment extends Fragment {
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 101;
    private static final int SPEECH_REQUEST_CODE = 0;
    public static int increment;
    View arrow_choose_settings;
    ConstraintLayout btnGeneratenew;
    public DrawerLayout drawerLayout;
    public EditText editTextPrompt;
    private ImageView imageView;
    ImageView imageView2ImageBtn;
    private ImageView imageViewButton;
    ImageView lock_layout;
    private NavigationView navView;
    SharedPrefsHelper prefs;
    private PromptGenerator promptGenerator;
    RecyclerView recyclerView_model;
    private TextView responseTextView;
    String rewardedID;
    private ImageView speechtotext_btn;
    private TextView styles_tv;
    TextView textView4;
    TextView textView5;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public String model = "absolute-reality-v1-8-1";
    public String outputFormat = "png";
    public String prompt = "Superman standing with super girl in galaxy and with cyborg girl";
    private int selectedHeight = 512;
    String selectedModel = "absolute-reality-v1-8-1";
    private int selectedWidth = 512;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("checkiyty", "onCreateView: HomeFRagment ");
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        this.prefs = SharedPrefsHelper.getInstance(getActivity());
        this.imageView2ImageBtn = (ImageView) inflate.findViewById(R.id.image2image_btn);
        this.lock_layout = (ImageView) inflate.findViewById(R.id.lock_layout);
        this.arrow_choose_settings = inflate.findViewById(R.id.arrow_choose_settings);
        this.textView4 = (TextView) inflate.findViewById(R.id.textView4);
        this.textView5 = (TextView) inflate.findViewById(R.id.textView5);
        this.editTextPrompt = (EditText) inflate.findViewById(R.id.editTextPrompt);
        this.btnGeneratenew = (ConstraintLayout) inflate.findViewById(R.id.btnGeneratenew);
        this.drawerLayout = (DrawerLayout) inflate.findViewById(R.id.drawer_layout);
        this.navView = (NavigationView) inflate.findViewById(R.id.nav_view);
        this.imageViewButton = (ImageView) inflate.findViewById(R.id.imageView2);
        this.speechtotext_btn = (ImageView) inflate.findViewById(R.id.speechtotext_btn);
        this.promptGenerator = new PromptGenerator();
        this.recyclerView_model = (RecyclerView) inflate.findViewById(R.id.recyclerView_model);
        this.styles_tv = (TextView) inflate.findViewById(R.id.styles_tv);
        if (getArguments() != null && getArguments().containsKey("textKey")) {
            this.editTextPrompt.setText(getArguments().getString("textKey"));
        }
        if (getArguments() != null && getArguments().containsKey("textKeyInspiration")) {
            this.editTextPrompt.setText(getArguments().getString("textKeyInspiration"));
        }
        if (getArguments() != null && getArguments().containsKey("textKey_styles")) {
            this.editTextPrompt.setText(getArguments().getString("textKey_styles") + " " + this.editTextPrompt.getText().toString());
        }
        setupRecyclerViewStyles(inflate);
        setupRecyclerViewModel();
        setupAspectRatioRV(inflate);
        inflate.findViewById(R.id.appCompatButtonPro).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        inflate.findViewById(R.id.inspire_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InspirationFragment()).commit();
            }
        });
        inflate.findViewById(R.id.btn_frag_Drawer).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HomeFragment.this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    HomeFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    HomeFragment.this.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        this.imageView2ImageBtn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                HomeFragment.this.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new GeneralCategoryImage2ImageFragment()).commit();
            }
        });
        this.imageViewButton.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.editTextPrompt.setText(HomeFragment.this.promptGenerator.generateRandomPrompt());
            }
        });
        this.speechtotext_btn.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(HomeFragment.this.getContext(), "android.permission.RECORD_AUDIO") != 0) {
                    HomeFragment.this.requestPermission();
                } else {
                    HomeFragment.this.displaySpeechRecognizer();
                }
            }
        });
        this.btnGeneratenew.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                if (ConnectivityChecker.isNetworkAvailable(HomeFragment.this.getActivity())) {
                    HomeFragment homeFragment = HomeFragment.this;
                    homeFragment.prompt = homeFragment.editTextPrompt.getText().toString();
                    if (HomeFragment.this.prompt.trim().isEmpty()) {
                        Toast.makeText(HomeFragment.this.getActivity(), "Please input Something!", 0).show();
                        return;
                    }
                    String obj = HomeFragment.this.editTextPrompt.getText().toString();
                    String format = DateFormat.getDateTimeInstance().format(new Date());
                    SharedPreferences.Editor edit = HomeFragment.this.getActivity().getSharedPreferences("MyPrefs", 0).edit();
                    edit.putString("savedHistory", new Gson().toJson(new HistoryItem(obj, format)));
                    edit.apply();
                    HomeFragment.increment++;
                    HomeFragment.this.proceedWithDownload();
                    Log.d("taggy", "Output Format: " + HomeFragment.this.outputFormat + ", Model: " + HomeFragment.this.model);
                    return;
                }
                HomeFragment.this.showNoInternetDialog();
            }
        });
        inflate.findViewById(R.id.history_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.8
            private long lastClickTime = 0;

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                if (SystemClock.elapsedRealtime() - this.lastClickTime >= 1000) {
                    this.lastClickTime = SystemClock.elapsedRealtime();
                    Log.d("MainActivityyyy", "History button clicked");
                    HistoryBottomSheetFragment.newInstance().show(HomeFragment.this.getActivity().getSupportFragmentManager(), "historyFragment");
                }
            }
        });
        inflate.findViewById(R.id.constraintLayout4).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                HomeFragment.this.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StylesFragment()).addToBackStack(null).commit();
            }
        });
        inflate.findViewById(R.id.advance_settings_btn).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                AdvanceSettingsFragment.newInstance().show(HomeFragment.this.getActivity().getSupportFragmentManager(), "advanceSettingsFragment");
            }
        });
        inflate.findViewById(R.id.constraintLayout5).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdsCommon.InterstitialAdsOnly(HomeFragment.this.getActivity());
                Log.d("MainActivityyyy", "History button clicked");
                HomeFragment.this.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ModelFragment()).addToBackStack(null).commit();
            }
        });
        this.navView.setLayoutParams(new DrawerLayout.LayoutParams(this.navView.getLayoutParams()) { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.12
            {
                this.gravity = GravityCompat.START;
            }
        });
        this.navView.setItemIconTintList(null);
        this.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.13
            @Override // com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.more_apps) {
                    try {
                        HomeFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + MyApplication.MoreApps)));
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        Toast.makeText(HomeFragment.this.getActivity(), "Google Play Store is not available.", 0).show();
                        return true;
                    }
                }
                if (itemId == R.id.share_app) {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", "AI Image Generator");
                    intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + HomeFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US");
                    intent.addFlags(536870912);
                    HomeFragment.this.startActivity(Intent.createChooser(intent, "Share with"));
                    return true;
                }
                if (itemId == R.id.rate_app) {
                    try {
                        HomeFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + HomeFragment.this.getActivity().getPackageName() + "&hl=en_US&gl=US")));
                        return true;
                    } catch (ActivityNotFoundException unused2) {
                        Toast.makeText(HomeFragment.this.getActivity(), "Google Play Store is not available.", 0).show();
                        return true;
                    }
                }
                if (itemId != R.id.policy) {
                    if (itemId == R.id.BackImageBtnDrawer) {
                        HomeFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    return true;
                }
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(MyApplication.PrivacyPolicy));
                intent2.setPackage("com.android.chrome");
                HomeFragment.this.startActivity(intent2);
                return true;
            }
        });
        this.navView.getHeaderView(0).findViewById(R.id.BackImageBtnDrawer).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeFragment.this.drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        return inflate;
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
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        create.show();
    }

    private void displaySavedData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", 0);
        String string = sharedPreferences.getString("savedText", "DefaultText");
        String string2 = sharedPreferences.getString("savedDateTime", "DefaultDateTime");
        Log.d("SavedData", "Text: " + string);
        Log.d("SavedData", "DateTime: " + string2);
    }

    public void proceedWithDownload() {
        if (this.selectedHeight <= 0 || this.selectedWidth <= 0) {
            Toast.makeText(getActivity().getApplicationContext(), "Invalid dimensions!", 0).show();
            return;
        }
        this.model = this.selectedModel;
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("model", this.model);
        bundle.putString("prompt", this.prompt);
        bundle.putInt("height", this.selectedHeight);
        bundle.putInt("width", this.selectedWidth);
        bundle.putString("outputFormat", this.outputFormat);
        downloadFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, downloadFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
        Log.d("taggy", "Output Format: " + this.outputFormat + ", Model: " + this.model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.RECORD_AUDIO"}, 101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displaySpeechRecognizer() {
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            this.editTextPrompt.setText(intent.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
        }
    }

    private void setupRecyclerViewStyles(View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ItemModel("style_image_1.png", getString(R.string.fantasy), "FANTASY"));
        arrayList.add(new ItemModel("style_image_2.png", getString(R.string.vibrant), "VIBRANT"));
        arrayList.add(new ItemModel("style_image_3.png", getString(R.string.euphoric), "EUPHORIC"));
        arrayList.add(new ItemModel("style_image_4.png", getString(R.string.gta), "GTA"));
        arrayList.add(new ItemModel("style_image_5.png", getString(R.string.anime_v2), "ANIME V2"));
        arrayList.add(new ItemModel("style_image_6.png", getString(R.string.cosmic), "COSMIC"));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        ItemAdapter itemAdapter = new ItemAdapter(arrayList);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setOnStyleClickListener(new ItemAdapter.OnStyleClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.16
            @Override // com.demo.imageaigenerator24.adapter.ItemAdapter.OnStyleClickListener
            public void onStyleSelected(String str) {
                String str2 = str + " " + HomeFragment.this.editTextPrompt.getText().toString();
                HomeFragment.this.editTextPrompt.setText(str2);
                HomeFragment.this.editTextPrompt.setSelection(str2.length());
            }
        });
    }

    private void setupRecyclerViewModel() {
        ItemModelAdapter itemModelAdapter = new ItemModelAdapter(Arrays.asList(new ItemModel("model_1.png", getString(R.string.absolute_reality), "absolute-reality-v1-8-1"), new ItemModel("model_2.png", getString(R.string.dream_shaper), "absolute-reality-v1-8-1"), new ItemModel("model_3.png", getString(R.string.realistic_vision), "absolute-reality-v1-8-1"), new ItemModel("model_4.png", getString(R.string.stable_diffusion), "absolute-reality-v1-8-1"), new ItemModel("model_5.png", getString(R.string.absolute_reality), "absolute-reality-v1-8-1"), new ItemModel("model_6.png", getString(R.string.dark_sushi), "absolute-reality-v1-8-1")));
        this.recyclerView_model.setAdapter(itemModelAdapter);
        this.recyclerView_model.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        itemModelAdapter.setOnItemClickListener(new ItemModelAdapter.OnItemClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.17
            @Override // com.demo.imageaigenerator24.adapter.ItemModelAdapter.OnItemClickListener
            public void onItemClick(ItemModel itemModel) {
                HomeFragment.this.selectedModel = itemModel.getModel();
            }
        });
    }

    private void setupAspectRatioRV(View view) {
        List asList = Arrays.asList(new AspectRatioModel("1:1"), new AspectRatioModel("4:3"), new AspectRatioModel("3:2"), new AspectRatioModel("16:9"), new AspectRatioModel("5:4"), new AspectRatioModel("7:5"), new AspectRatioModel("21:9"), new AspectRatioModel("4:5"));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_aspcetratio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        recyclerView.setAdapter(new AspectRatioAdapter(asList, getActivity(), new AspectRatioAdapter.AspectRatioClickListener() { // from class: com.demo.imageaigenerator24.fragments.HomeFragment.18
            @Override // com.demo.imageaigenerator24.adapter.AspectRatioAdapter.AspectRatioClickListener
            public void onRatioSelected(String str) {
                HomeFragment.this.onRatioSelectedCall(str);
            }
        }));
    }

    public void onRatioSelectedCall(String str) {
        str.hashCode();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 48936:
                if (str.equals("1:1")) {
                    c = 0;
                    break;
                }
                break;
            case 50859:
                if (str.equals("3:2")) {
                    c = 1;
                    break;
                }
                break;
            case 51821:
                if (str.equals("4:3")) {
                    c = 2;
                    break;
                }
                break;
            case 1513508:
                if (str.equals("16:9")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.selectedHeight = 512;
                this.selectedWidth = 512;
                return;
            case 1:
                this.selectedHeight = 384;
                this.selectedWidth = 512;
                return;
            case 2:
                this.selectedHeight = 512;
                this.selectedWidth = 768;
                return;
            case 3:
                this.selectedHeight = 576;
                this.selectedWidth = 1024;
                return;
            default:
                return;
        }
    }

    public void handleBackPress() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ExitFragment());
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
