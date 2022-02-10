package tsi.pdmsf.memorygame.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import tsi.pdmsf.memorygame.ColorSchemePreferencePersistence;
import tsi.pdmsf.memorygame.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            setupThemePreference();
            setupAboutPreference();
            setupWebsitePreference();
            setupRepoPreference();
            setupHighscorePreference();
        }

        private void setupThemePreference() {
            ColorSchemePreferencePersistence colorSchemePersistence = new ColorSchemePreferencePersistence(getContext());
            ListPreference colorSchemePreference = findPreference(getString(R.string.key_change_color_scheme));

            try {
                if (colorSchemePreference != null) {
                    colorSchemePreference.setDefaultValue(colorSchemePersistence.getDefaultColorScheme());

                    colorSchemePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                        String oldValue = colorSchemePersistence.getCurrentColorScheme();

                        if (oldValue != newValue) {
                            colorSchemePersistence.saveColorScheme(newValue.toString());
                        }

                        return oldValue != newValue;
                    });
                }
            } catch (Exception ignored) {
            }
        }

        private void setupAboutPreference() {
            Preference aboutPref = findPreference(getString(R.string.pref_key_about));
            if (aboutPref != null) {
                aboutPref.setOnPreferenceClickListener(preference -> {
                    startActivity(new Intent(getContext(), AboutActivity.class));
                    return false;
                });
            }
        }

        private void setupHighscorePreference() {
            Preference aboutPref = findPreference(getString(R.string.pref_key_score));
            if (aboutPref != null) {
                aboutPref.setOnPreferenceClickListener(preference -> {
                    startActivity(new Intent(getContext(), HighScoreActivity.class));
                    return false;
                });
            }
        }


        private void setupWebsitePreference() {
            Preference websitePreference = findPreference(getString(R.string.pref_key_website));
            if (websitePreference != null) {
                websitePreference.setOnPreferenceClickListener(preference -> {
                    if (getActivity() != null) {
                        WebViewActivity.navigate(getActivity(), getString(R.string.page_url));
                    }
                    return true;
                });
            }
        }

        private void setupRepoPreference() {
            Preference repoPreference = findPreference(getString(R.string.pref_key_repo));
            if (repoPreference != null) {
                repoPreference.setOnPreferenceClickListener(preference -> {
                    if (getActivity() != null) {
                        WebViewActivity.navigate(getActivity(), getString(R.string.repo_url));
                    }
                    return true;
                });
            }
        }
    }
}
