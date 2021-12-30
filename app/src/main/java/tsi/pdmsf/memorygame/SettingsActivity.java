package tsi.pdmsf.memorygame;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

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
        }

        private void setupThemePreference() {
            ThemePreference themePersistence = new ThemePreference(getContext());
            ListPreference themePreference = findPreference(getString(R.string.key_change_theme));

            try {
                themePreference.setDefaultValue(themePersistence.getDefaultTheme());
            } catch (Exception ignored) {
            }

            themePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                String oldValue = themePersistence.getCurrentTheme();

                if (oldValue != newValue) {
                    themePersistence.applyAndSaveTheme(newValue.toString());
                }

                return oldValue != newValue;
            });
        }

        private void setupAboutPreference() {
            Preference aboutPref = findPreference(getString(R.string.pref_key_about));
            aboutPref.setOnPreferenceClickListener(preference -> {
                startActivity(new Intent(getContext(), AboutActivity.class));
                return false;
            });
        }

        private void setupWebsitePreference() {
            Preference websitePreference = findPreference(getString(R.string.pref_key_website));
            websitePreference.setOnPreferenceClickListener(preference -> {
                WebViewActivity.navigate(getActivity(), getString(R.string.page_url));
                return true;
            });
        }

        private void setupRepoPreference() {
            Preference repoPreference = findPreference(getString(R.string.pref_key_repo));
            repoPreference.setOnPreferenceClickListener(preference -> {
                WebViewActivity.navigate(getActivity(), getString(R.string.repo_url));
                return true;
            });
        }
    }
}
