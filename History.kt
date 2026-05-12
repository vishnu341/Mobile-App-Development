import android.content.Context
import android.preference.PreferenceManager
import androidx.core.content.edit

class UserPreferences(context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun isHistoryPageEnabled(): Boolean {
        return preferences.getBoolean("historyPageEnabled", true)
    }

    fun setHistoryPageEnabled(enabled: Boolean) {
        preferences.edit {
            putBoolean("historyPageEnabled", enabled)
        }
    }

    fun isGoalPageEnabled(): Boolean {
        return preferences.getBoolean("goalPageEnabled", true)
    }

    fun setGoalPageEnabled(enabled: Boolean) {
        preferences.edit {
            putBoolean("goalPageEnabled", enabled)
        }
    }
}
