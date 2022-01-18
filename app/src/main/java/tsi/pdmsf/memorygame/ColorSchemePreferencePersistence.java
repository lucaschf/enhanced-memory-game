package tsi.pdmsf.memorygame;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class ColorSchemePreferencePersistence {

    private final Context context;
    private final SharedPreferences sharedPreferences;

    public ColorSchemePreferencePersistence(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getCurrentColorScheme() {
        return sharedPreferences.getString(
                context.getString(R.string.key_change_color_scheme),
                context.getString(R.string.default_color_scheme_value)
        );
    }

    public void saveColorScheme(String colorScheme) {
        sharedPreferences.edit().
                putString(context.getString(R.string.key_change_color_scheme), colorScheme)
                .apply();
    }

    public String getDefaultColorScheme() {
        return context.getString(R.string.default_color_scheme_entry);
    }

}
