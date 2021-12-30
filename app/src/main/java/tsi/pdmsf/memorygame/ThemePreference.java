package tsi.pdmsf.memorygame;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

public class ThemePreference {

    private final Context context;
    private final SharedPreferences sharedPreferences;

    public ThemePreference(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getCurrentTheme() {
        return sharedPreferences.getString(
                context.getString(R.string.key_change_theme),
                context.getString(R.string.default_theme_value)
        );
    }

    public void applyAndSaveTheme(String theme) {

        int mode = AppCompatDelegate.MODE_NIGHT_YES;

        if (theme.equals(context.getString(R.string.default_theme_value))) {
            mode = AppCompatDelegate.MODE_NIGHT_NO;
        }

        saveTheme(theme);
        AppCompatDelegate.setDefaultNightMode(mode);
    }

    private void saveTheme(String theme) {
        sharedPreferences.edit().
                putString(context.getString(R.string.key_change_theme), theme)
                .apply();
    }

    public String getDefaultTheme() {
        return context.getString(R.string.default_theme_entry);
    }

}
