package e.Quran.Qaz.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    public static SharedPreferences sharedPreferences;
    public static Boolean aBoolean;

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setSharedPreferences(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightModeState() {
        Boolean state = sharedPreferences.getBoolean("NightMode", false);
        aBoolean = state;
        return state;
    }


}
