package com.yurentsy.watchingyou.ui.fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yurentsy.watchingyou.App;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.ui.common.BackButtonListener;

import java.util.Locale;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class SettingFragment extends PreferenceFragmentCompat implements BackButtonListener, PreferenceFragmentCompat.OnPreferenceStartScreenCallback, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    Router router;

    public static final String LANGUAGE_SETTING = "lang_choice";

    public static SettingFragment getNewInstance() {
        SettingFragment fragment = new SettingFragment();
        //если все же что-то добавил то fragment.setArguments(bundle)
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().getComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Toolbar toolbar = null;
        if (view != null) {
            toolbar = view.findViewById(R.id.toolbar);
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(v1 -> onBackPressed());
        }

        return view;
    }

    @Override
    public boolean onBackPressed() {
        router.exit();
        return true;
    }

    @Override
    public Fragment getCallbackFragment() {
        return this;
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
        preferenceFragmentCompat.setPreferenceScreen(preferenceScreen);
        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference preference = findPreference(s);
        Configuration configuration = getResources().getConfiguration();

        if (s.equals("lang_choice")) {
            preference.setSummary(((ListPreference) preference).getEntry());

            if (((ListPreference) preference).getValue().equals("en")) {
                configuration.locale = new Locale("en");
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
            }

            if (((ListPreference) preference).getValue().equals("ru")) {
                configuration.locale = new Locale("ru");
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
            }
        }

        preference = findPreference("notifications");
        preference.setOnPreferenceClickListener(p1 -> {
            Toast.makeText(getContext(), "notifications", Toast.LENGTH_SHORT).show();
            return true;
        });

        // Возврат к фрагменту
        getCallbackFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}