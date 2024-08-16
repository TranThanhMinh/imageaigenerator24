package com.demo.imageaigenerator24.practicetwo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.demo.imageaigenerator24.R;
import com.demo.imageaigenerator24.adapter.AspectRatioAdapter;
import com.demo.imageaigenerator24.ads.AdsCommon;
import com.demo.imageaigenerator24.model.AspectRatioModel;
import com.demo.imageaigenerator24.practicetwo.AIArtBottomSheetFragment;
import com.demo.imageaigenerator24.practicetwo.AIPhotorealismBottomSheetFragment;
import com.demo.imageaigenerator24.practicetwo.AIStyleBottomSheetFragment;
import com.demo.imageaigenerator24.practicetwo.DataModel;
import com.demo.imageaigenerator24.remoteconfig.SharedPrefsHelper;
import com.demo.imageaigenerator24.utils.PromptGenerator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder> {
    public static final int TYPE_AI_ADVANCE_SETTINGS = 5;
    public static final int TYPE_AI_ASPECT = 3;
    public static final int TYPE_AI_EDIT_LAYOUT = 1;
    public static final int TYPE_AI_GENERATE = 6;
    public static final int TYPE_AI_IMAGE = 7;
    public static final int TYPE_AI_IMAGE_2_IMAGE = 8;
    public static final int TYPE_AI_MODEL_AND_STYLE = 4;
    public static final int TYPE_AI_SAMPLERS = 2;
    public static final int TYPE_AI_TITLE = 0;
    public static CollectedData collectedData = null;
    static Context context = null;
    public static EditText currentEditText = null;
    public static int currentPosition = -1;
    static EditText editText = null;
    public static OnGenerateClickListener generateClickListener = null;
    public static OnImage2ImageClickListener image2ImageClickListener = null;
    public static ImageUploadListener imageUploadListener = null;
    public static SharedPrefsHelper prefs = null;
    public static PromptGenerator promptGenerator = null;
    public static RecyclerView recyclerView = null;
    public static AppCompatButton selectedButton = null;
    public static String sharedText = "";
    public static SpeechToTextListener speechToTextInterface;
    private static OnTextTransferListener textTransferListener;
    public AdapterCallback callback;
    private List<DataModel.AIImage2Imgae> items;
    private List<DataModel> adapterData = new ArrayList();
    private int selectedHeight = 512;
    private int selectedWidth = 512;
    private String spokenText = "";

    /* loaded from: classes.dex */
    public interface OnGenerateClickListener {
        void onGenerateClick();
    }

    /* loaded from: classes.dex */
    public interface OnImage2ImageClickListener {
        void onImage2ImageClick();
    }

    public DataModel.AIImage2Imgae getItem(int i) {
        return this.items.get(i);
    }

    public DataAdapter(Context context2, RecyclerView recyclerView2, SpeechToTextListener speechToTextListener, OnGenerateClickListener onGenerateClickListener, CollectedData collectedData2, ImageUploadListener imageUploadListener2, OnImage2ImageClickListener onImage2ImageClickListener, SharedPrefsHelper sharedPrefsHelper) {
        context = context2;
        recyclerView = recyclerView2;
        speechToTextInterface = speechToTextListener;
        generateClickListener = onGenerateClickListener;
        collectedData = collectedData2;
        imageUploadListener = imageUploadListener2;
        image2ImageClickListener = onImage2ImageClickListener;
        prefs = sharedPrefsHelper;
    }

    public DataAdapter(Context context2, RecyclerView recyclerView2, SpeechToTextListener speechToTextListener, OnGenerateClickListener onGenerateClickListener, CollectedData collectedData2, OnImage2ImageClickListener onImage2ImageClickListener, SharedPrefsHelper sharedPrefsHelper) {
        context = context2;
        recyclerView = recyclerView2;
        speechToTextInterface = speechToTextListener;
        generateClickListener = onGenerateClickListener;
        collectedData = collectedData2;
        image2ImageClickListener = onImage2ImageClickListener;
        prefs = sharedPrefsHelper;
    }

    public void setSharedText(String str) {
        sharedText = str;
        notifyDataSetChanged();
    }

    public void setSpokenText(String str) {
        EditText editText2 = currentEditText;
        if (editText2 != null) {
            editText2.setText(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DataAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int i2;
        switch (i) {
            case 0:
                i2 = R.layout.item_image_ai_title;
                break;
            case 1:
                i2 = R.layout.item_image_ai_edittext;
                break;
            case 2:
                i2 = R.layout.item_image_ai_samplers;
                break;
            case 3:
                i2 = R.layout.item_image_ai_aspect_ratios;
                break;
            case 4:
                i2 = R.layout.item_image_ai_model_and_style;
                break;
            case 5:
                i2 = R.layout.item_image_ai_advance_settings;
                break;
            case 6:
                i2 = R.layout.item_image_ai_generate;
                break;
            case 7:
                i2 = R.layout.item_image_ai_image;
                break;
            case 8:
                i2 = R.layout.item_image_ai_imagetoimage;
                break;
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
        return new DataAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i2, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DataAdapterViewHolder dataAdapterViewHolder, int i) {
        dataAdapterViewHolder.bind(this.adapterData.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.adapterData.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.adapterData.get(i) instanceof DataModel.AItitle) {
            return 0;
        }
        if (this.adapterData.get(i) instanceof DataModel.AIEditText) {
            return 1;
        }
        if (this.adapterData.get(i) instanceof DataModel.AISamplers) {
            return 2;
        }
        if (this.adapterData.get(i) instanceof DataModel.AIRatios) {
            return 3;
        }
        if (this.adapterData.get(i) instanceof DataModel.AIModelAndStyles) {
            return 4;
        }
        if (this.adapterData.get(i) instanceof DataModel.AIAdvanceSettings) {
            return 5;
        }
        if (this.adapterData.get(i) instanceof DataModel.AIGenerate) {
            return 6;
        }
        if (this.adapterData.get(i) instanceof DataModel.AIImage) {
            return 7;
        }
        return this.adapterData.get(i) instanceof DataModel.AIImage2Imgae ? 8 : 0;
    }

    public void setData(List<DataModel> list) {
        this.adapterData.clear();
        this.adapterData.addAll(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class DataAdapterViewHolder extends RecyclerView.ViewHolder {
        public DataAdapterViewHolder(View view) {
            super(view);
        }

        public void bind(DataModel dataModel) {
            if (dataModel instanceof DataModel.AItitle) {
                bindAITitle((DataModel.AItitle) dataModel);
                return;
            }
            if (dataModel instanceof DataModel.AIEditText) {
                bindAIEditLayout((DataModel.AIEditText) dataModel);
                return;
            }
            if (dataModel instanceof DataModel.AISamplers) {
                bindAISamplers((DataModel.AISamplers) dataModel);
                return;
            }
            if (dataModel instanceof DataModel.AIRatios) {
                bindAIAspectRatios((DataModel.AIRatios) dataModel);
                return;
            }
            if (dataModel instanceof DataModel.AIModelAndStyles) {
                bindAIAiModelAndStyle((DataModel.AIModelAndStyles) dataModel);
                return;
            }
            if (dataModel instanceof DataModel.AIAdvanceSettings) {
                bindAIAdvanceSettings((DataModel.AIAdvanceSettings) dataModel);
                return;
            }
            if (dataModel instanceof DataModel.AIGenerate) {
                bindAIGenerate((DataModel.AIGenerate) dataModel);
            } else if (dataModel instanceof DataModel.AIImage) {
                bindAIImage((DataModel.AIImage) dataModel);
            } else if (dataModel instanceof DataModel.AIImage2Imgae) {
                bindAIImage2Image((DataModel.AIImage2Imgae) dataModel);
            }
        }

        private void bindAITitle(DataModel.AItitle aItitle) {
            ((TextView) this.itemView.findViewById(R.id.textViewimageaititle)).setText(aItitle.getTextView());
        }

        private void bindAIEditLayout(DataModel.AIEditText aIEditText) {
            DataAdapter.promptGenerator = new PromptGenerator();
            ImageView imageView = (ImageView) this.itemView.findViewById(R.id.cleanr_btn);
            ConstraintLayout constraintLayout = (ConstraintLayout) this.itemView.findViewById(R.id.constraintLayout);
            ImageView imageView2 = (ImageView) this.itemView.findViewById(R.id.imageView2);
            ImageView imageView3 = (ImageView) this.itemView.findViewById(R.id.history_btn);
            ImageView imageView4 = (ImageView) this.itemView.findViewById(R.id.image2image_btn);
            ImageView imageView5 = (ImageView) this.itemView.findViewById(R.id.speechtotext_btn);
            DataAdapter.editText = (EditText) this.itemView.findViewById(R.id.editText);
            if (DataAdapter.sharedText == null || DataAdapter.sharedText.isEmpty()) {
                DataAdapter.editText.setText(DataAdapter.collectedData.getPrompt());
            } else {
                DataAdapter.editText.setText(DataAdapter.sharedText);
                DataAdapter.collectedData.setPrompt(DataAdapter.sharedText);
            }
            imageView.setImageResource(aIEditText.getDrawableResourceId());
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DataAdapter.context instanceof FragmentActivity) {
                        HistoryBottomSheetFragment historyBottomSheetFragment = new HistoryBottomSheetFragment();
                        historyBottomSheetFragment.show(((FragmentActivity) DataAdapter.context).getSupportFragmentManager(), historyBottomSheetFragment.getTag());
                    }
                }
            });
            DataAdapter.editText.addTextChangedListener(new TextWatcher() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.2
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    if (editable.toString().isEmpty()) {
                        return;
                    }
                    DataAdapter.collectedData.setPrompt(editable.toString());
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapter.editText.setText(DataAdapter.promptGenerator.generateRandomPrompt());
                }
            });
            imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DataAdapter.image2ImageClickListener != null) {
                        DataAdapter.image2ImageClickListener.onImage2ImageClick();
                    }
                }
            });
            imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapter.currentEditText = DataAdapter.editText;
                    if (ContextCompat.checkSelfPermission(DataAdapter.context, "android.permission.RECORD_AUDIO") != 0) {
                        DataAdapter.speechToTextInterface.requestSpeechToTextPermission();
                    } else {
                        DataAdapter.speechToTextInterface.startSpeechToText();
                    }
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapter.editText.setText("");
                    DataAdapter.collectedData.setPrompt("");
                }
            });
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapter.editText.setText("");
                    DataAdapter.collectedData.setPrompt("");
                }
            });
        }

        private void bindAISamplers(DataModel.AISamplers aISamplers) {
            final AppCompatButton appCompatButton = (AppCompatButton) this.itemView.findViewById(R.id.ddim_btn);
            final AppCompatButton appCompatButton2 = (AppCompatButton) this.itemView.findViewById(R.id.lms_btn);
            final AppCompatButton appCompatButton3 = (AppCompatButton) this.itemView.findViewById(R.id.eular_btn);
            final AppCompatButton appCompatButton4 = (AppCompatButton) this.itemView.findViewById(R.id.eular_a_btn);
            final AppCompatButton appCompatButton5 = (AppCompatButton) this.itemView.findViewById(R.id.dpmsolver_btn);
            final AppCompatButton appCompatButton6 = (AppCompatButton) this.itemView.findViewById(R.id.pndm_btn);
            appCompatButton.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showAISamplersDailog(appCompatButton, "ddim", R.drawable.sampler_1, "Use the joint power of your imagination and generative AI to easily  make your own unique images.");
                }
            });
            appCompatButton2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showAISamplersDailog(appCompatButton2, "euler_a", R.drawable.sampler_2, "Use our powerful AI model to create exceptional art with your imagination.");
                }
            });
            appCompatButton3.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showAISamplersDailog(appCompatButton3, "euler", R.drawable.sampler_3, "Generate original concept art and inspirational assets to reduce time to market your products and also offer a personalized experience.");
                }
            });
            appCompatButton4.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showAISamplersDailog(appCompatButton4, "euler_a", R.drawable.sampler_4, "Use our AI advance model to create multiple images, matching the style of your requirements with the help of generative AI.  ");
                }
            });
            appCompatButton5.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showAISamplersDailog(appCompatButton5, "dpmsolver++", R.drawable.sampler_5, "Change the look of your generative art faster 10x to 50x using the high quality models and styles. ");
                }
            });
            appCompatButton6.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showAISamplersDailog(appCompatButton6, "ddim", R.drawable.sampler_6, "Creative fictional character or  trending images for your games, books, apps and generate inspiring ideas.");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showAISamplersDailog(final AppCompatButton appCompatButton, final String str, int i, String str2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataAdapter.context);
            View inflate = LayoutInflater.from(DataAdapter.context).inflate(R.layout.dialog_custom_sampler, (ViewGroup) null);
            builder.setView(inflate);
            AppCompatButton appCompatButton2 = (AppCompatButton) inflate.findViewById(R.id.dialog_try_now_button);
            appCompatButton2.setText("OK\nNote: generated results based on your inputs.");
            appCompatButton2.setTextSize(10.0f);
            ((TextView) inflate.findViewById(R.id.dialog_text_view)).setText(str2);
            ((ImageView) inflate.findViewById(R.id.dialog_image_view)).setImageResource(i);
            final AlertDialog create = builder.create();
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            ((ImageButton) inflate.findViewById(R.id.dialog_close_button)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DataAdapter.selectedButton != null) {
                        DataAdapter.selectedButton.setBackgroundResource(R.drawable.gradient_drawable);
                        DataAdapter.selectedButton.setTextColor(ContextCompat.getColor(DataAdapter.context, R.color.black));
                    }
                    create.dismiss();
                }
            });
            appCompatButton2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.15
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AdsCommon.InterstitialAdsOnly((Activity) DataAdapter.context);
                    DataAdapter.collectedData.setSampler(str);
                    if (DataAdapter.selectedButton != null) {
                        DataAdapter.selectedButton.setBackgroundResource(R.drawable.gradient_drawable);
                        DataAdapter.selectedButton.setTextColor(ContextCompat.getColor(DataAdapter.context, R.color.black));
                    }
                    appCompatButton.setBackgroundResource(R.drawable.selected_bg_samplers);
                    appCompatButton.setTextColor(ContextCompat.getColor(DataAdapter.context, android.R.color.white));
                    DataAdapter.selectedButton = appCompatButton;
                    create.dismiss();
                }
            });
            create.show();
        }

        private void bindAIAspectRatios(DataModel.AIRatios aIRatios) {
            List asList = Arrays.asList(new AspectRatioModel("1:1"), new AspectRatioModel("4:3"), new AspectRatioModel("3:2"), new AspectRatioModel("2:3"), new AspectRatioModel("16:9"));
            RecyclerView recyclerView = (RecyclerView) this.itemView.findViewById(R.id.recyclerView_aspcetratio);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.itemView.getContext(), RecyclerView.HORIZONTAL, false));
            recyclerView.setAdapter(new AspectRatioAdapter(asList, this.itemView.getContext(), new AspectRatioAdapter.AspectRatioClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.16
                @Override // com.demo.imageaigenerator24.adapter.AspectRatioAdapter.AspectRatioClickListener
                public void onRatioSelected(String str) {
                    DataAdapterViewHolder.this.onRatioSelectedCall(str, 512, 512);
                }
            }));
        }

        public void onRatioSelectedCall(String str, int i, int i2) {
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
                case 49899:
                    if (str.equals("2:3")) {
                        c = 1;
                        break;
                    }
                    break;
                case 50859:
                    if (str.equals("3:2")) {
                        c = 2;
                        break;
                    }
                    break;
                case 51821:
                    if (str.equals("4:3")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1513508:
                    if (str.equals("16:9")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    DataAdapter.collectedData.setWidth(512);
                    DataAdapter.collectedData.setHeight(512);
                    return;
                case 1:
                    DataAdapter.collectedData.setWidth(768);
                    DataAdapter.collectedData.setHeight(512);
                    return;
                case 2:
                    DataAdapter.collectedData.setWidth(384);
                    DataAdapter.collectedData.setHeight(512);
                    return;
                case 3:
                    DataAdapter.collectedData.setWidth(512);
                    DataAdapter.collectedData.setHeight(768);
                    return;
                case 4:
                    DataAdapter.collectedData.setWidth(1024);
                    DataAdapter.collectedData.setHeight(576);
                    return;
                default:
                    return;
            }
        }

        private void bindAIAiModelAndStyle(DataModel.AIModelAndStyles aIModelAndStyles) {
            ((AppCompatButton) this.itemView.findViewById(R.id.ai_photoreaslism)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AIPhotorealismBottomSheetFragment aIPhotorealismBottomSheetFragment = new AIPhotorealismBottomSheetFragment();
                    aIPhotorealismBottomSheetFragment.setBottomSheetListener(new AIPhotorealismBottomSheetFragment.BottomSheetListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.17.1
                        @Override // com.demo.imageaigenerator24.practicetwo.AIPhotorealismBottomSheetFragment.BottomSheetListener
                        public void onModelSelected(String str) {
                            DataAdapter.collectedData.setModelAndStyle(str);
                        }

                        @Override // com.demo.imageaigenerator24.practicetwo.AIPhotorealismBottomSheetFragment.BottomSheetListener
                        public void onLockedModelSelected(String str) {
                            DataAdapterViewHolder.this.showBillingDialog();
                        }
                    });
                    aIPhotorealismBottomSheetFragment.show(((FragmentActivity) DataAdapter.context).getSupportFragmentManager(), "AIPhotorealismBottomSheet");
                }
            });
            ((AppCompatButton) this.itemView.findViewById(R.id.ai_art)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.18
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AIArtBottomSheetFragment aIArtBottomSheetFragment = new AIArtBottomSheetFragment();
                    aIArtBottomSheetFragment.setBottomSheetListener(new AIArtBottomSheetFragment.BottomSheetListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.18.1
                        @Override // com.demo.imageaigenerator24.practicetwo.AIArtBottomSheetFragment.BottomSheetListener
                        public void onModelSelected(String str) {
                            DataAdapter.collectedData.setModelAndStyle(str);
                        }

                        @Override // com.demo.imageaigenerator24.practicetwo.AIArtBottomSheetFragment.BottomSheetListener
                        public void onLockedModelSelected(String str) {
                            DataAdapterViewHolder.this.showBillingDialog();
                        }
                    });
                    aIArtBottomSheetFragment.show(((FragmentActivity) DataAdapter.context).getSupportFragmentManager(), "AIArtBottomSheetFragment");
                }
            });
            ((AppCompatButton) this.itemView.findViewById(R.id.ai_style)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.19
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AIStyleBottomSheetFragment aIStyleBottomSheetFragment = new AIStyleBottomSheetFragment();
                    aIStyleBottomSheetFragment.setBottomSheetListener(new AIStyleBottomSheetFragment.BottomSheetListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.19.1
                        @Override // com.demo.imageaigenerator24.practicetwo.AIStyleBottomSheetFragment.BottomSheetListener
                        public void onModelSelected(String str) {
                            DataAdapter.collectedData.setModelAndStyle(str);
                        }

                        @Override // com.demo.imageaigenerator24.practicetwo.AIStyleBottomSheetFragment.BottomSheetListener
                        public void onLockedModelSelected(String str) {
                            DataAdapterViewHolder.this.showBillingDialog();
                        }
                    });
                    aIStyleBottomSheetFragment.show(((FragmentActivity) DataAdapter.context).getSupportFragmentManager(), "AIStyleBottomSheetFragment");
                }
            });
        }

        public void showBillingDialog() {
            Toast.makeText(DataAdapter.context, "Please purchase Pro", Toast.LENGTH_SHORT).show();
        }

        private void bindAIAdvanceSettings(DataModel.AIAdvanceSettings aIAdvanceSettings) {
            this.itemView.findViewById(R.id.arrow_choose_settings);
            ((ConstraintLayout) this.itemView.findViewById(R.id.advancce_Setting_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.20
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new AdvancedSettingsBottomSheetFragment(DataAdapter.collectedData).show(((FragmentActivity) DataAdapter.context).getSupportFragmentManager(), "AdvancedSettingsBottomSheetFragment");
                }
            });
        }

        private void bindAIGenerate(DataModel.AIGenerate aIGenerate) {
            ((ConstraintLayout) this.itemView.findViewById(R.id.btnGeneratenew)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.21
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DataAdapter.generateClickListener != null) {
                        DataAdapter.generateClickListener.onGenerateClick();
                    }
                    if (DataAdapter.editText != null) {
                        DataAdapterViewHolder.this.saveTextToHistory(DataAdapter.editText.getText().toString());
                        DataAdapterViewHolder.this.updateRecyclerViewWithHistory();
                    }
                }
            });
        }

        public void saveTextToHistory(String str) {
            SharedPreferences sharedPreferences = DataAdapter.context.getSharedPreferences("MyPrefs", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            try {
                JSONArray jSONArray = new JSONArray(sharedPreferences.getString("history", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI));
                jSONArray.put(str);
                edit.putString("history", jSONArray.toString());
                edit.apply();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void updateRecyclerViewWithHistory() {
            String string = DataAdapter.context.getSharedPreferences("MyPrefs", 0).getString("history", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(string);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RecyclerView.Adapter adapter = DataAdapter.recyclerView.getAdapter();
            if (adapter instanceof MyRecyclerViewAdapter) {
                MyRecyclerViewAdapter myRecyclerViewAdapter = (MyRecyclerViewAdapter) adapter;
                myRecyclerViewAdapter.setData(arrayList);
                myRecyclerViewAdapter.notifyDataSetChanged();
                return;
            }
            Log.e("DataAdapter", "RecyclerView does not have MyRecyclerViewAdapter");
        }

        private void bindAIImage(final DataModel.AIImage aIImage) {
            ImageView imageView = (ImageView) this.itemView.findViewById(R.id.view_inspire_frag_item);
            ((TextView) this.itemView.findViewById(R.id.textView_inspire_frag_item)).setText(aIImage.getText());
            ((ConstraintLayout) this.itemView.findViewById(R.id.item_image_parent)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.22
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapterViewHolder.this.showCustomDialog(aIImage);
                }
            });
            try {
                imageView.setImageDrawable(Drawable.createFromStream(this.itemView.getContext().getAssets().open(aIImage.getAssetFileName()), null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void bindAIImage2Image(DataModel.AIImage2Imgae aIImage2Imgae) {
            final ImageView imageView = (ImageView) this.itemView.findViewById(R.id.getBackImage);
            final CardView cardView = (CardView) this.itemView.findViewById(R.id.getBackImageCard);
            CardView cardView2 = (CardView) this.itemView.findViewById(R.id.uploadImage_btn);
            final TextView textView = (TextView) this.itemView.findViewById(R.id.textView10);
            final TextView textView2 = (TextView) this.itemView.findViewById(R.id.textView101);
            ImageView imageView2 = (ImageView) this.itemView.findViewById(R.id.upload_Cross_btn);
            if (aIImage2Imgae.getImageUri() != null) {
                cardView.setVisibility(0);
                textView.setVisibility(8);
                textView2.setVisibility(0);
                imageView.setImageURI(aIImage2Imgae.getImageUri());
            }
            cardView2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.23
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DataAdapter.imageUploadListener != null) {
                        DataAdapter.imageUploadListener.onImageUploadClick();
                        DataAdapter.currentPosition = DataAdapterViewHolder.this.getAdapterPosition();
                    }
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.24
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    imageView.setImageResource(0);
                    cardView.setVisibility(8);
                    textView.setVisibility(0);
                    textView2.setVisibility(8);
                }
            });
        }

        public int getCurrentPosition() {
            return DataAdapter.currentPosition;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showCustomDialog(final DataModel.AIImage aIImage) {
            AlertDialog.Builder builder = new AlertDialog.Builder(DataAdapter.context);
            View inflate = LayoutInflater.from(DataAdapter.context).inflate(R.layout.dialog_custom, (ViewGroup) null);
            builder.setView(inflate);
            setImageToView(aIImage.getAssetFileName(), (ImageView) inflate.findViewById(R.id.dialog_image_view));
            ((TextView) inflate.findViewById(R.id.dialog_text_view)).setText(aIImage.getText());
            final AlertDialog create = builder.create();
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            ((ImageButton) inflate.findViewById(R.id.dialog_close_button)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.25
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    create.dismiss();
                }
            });
            ((AppCompatButton) inflate.findViewById(R.id.dialog_try_now_button)).setOnClickListener(new View.OnClickListener() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.26
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    DataAdapter.sharedText = aIImage.getText();
                    create.dismiss();
                    if (DataAdapter.recyclerView != null) {
                        DataAdapter.recyclerView.scrollToPosition(0);
                        DataAdapter.recyclerView.postDelayed(new Runnable() { // from class: com.demo.imageaigenerator24.practicetwo.DataAdapter.DataAdapterViewHolder.26.1
                            @Override // java.lang.Runnable
                            public void run() {
                                EditText editText;
                                RecyclerView.ViewHolder findViewHolderForAdapterPosition = DataAdapter.recyclerView.findViewHolderForAdapterPosition(0);
                                if (findViewHolderForAdapterPosition == null || (editText = (EditText) findViewHolderForAdapterPosition.itemView.findViewById(R.id.editText)) == null) {
                                    return;
                                }
                                editText.requestFocus();
                            }
                        }, 200L);
                    }
                }
            });
            create.show();
        }

        private void setImageToView(String str, ImageView imageView) {
            try {
                imageView.setImageDrawable(Drawable.createFromStream(DataAdapter.context.getAssets().open(str), null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
