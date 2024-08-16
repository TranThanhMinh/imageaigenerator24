package com.demo.imageaigenerator24.history;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HistoryManager {
    private static final String HISTORY_KEY = "history_key";
    private static final String HISTORY_PREFERENCES = "history_prefs";
    private SharedPreferences prefs;

    public HistoryManager(Context context) {
        this.prefs = context.getSharedPreferences(HISTORY_PREFERENCES, 0);
    }

    public void addHistoryItem(History history) {
        Gson gson = new Gson();
        List<History> historyItems = getHistoryItems();
        historyItems.add(history);
        this.prefs.edit().putString(HISTORY_KEY, gson.toJson(historyItems)).apply();
    }

    public List<History> getHistoryItems() {
        List<History> list = (List) new Gson().fromJson(this.prefs.getString(HISTORY_KEY, ""), new TypeToken<ArrayList<History>>() { // from class: com.demo.imageaigenerator24.history.HistoryManager.1
        }.getType());
        return list == null ? new ArrayList() : list;
    }

    public void clearHistory() {
        this.prefs.edit().remove(HISTORY_KEY).apply();
    }

    public void removeHistoryItem(History history) {
        List<History> historyItems = getHistoryItems();
        historyItems.remove(history);
        this.prefs.edit().putString(HISTORY_KEY, new Gson().toJson(historyItems)).apply();
    }
}
