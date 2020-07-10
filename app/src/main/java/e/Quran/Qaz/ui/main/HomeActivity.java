package e.Quran.Qaz.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import e.Quran.Qaz.R;
import e.Quran.Qaz.ui.asma.AsmaFragment;
import e.Quran.Qaz.ui.kuran.KuranFragment;
import e.Quran.Qaz.ui.kuranPdf.KuranPdfFragment;
import e.Quran.Qaz.ui.setting.SettingFragment;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    KuranPdfFragment kuranPdfFragment;
    Fragment kuranFragment;
    AsmaFragment asmaFragment;
    SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = this.getSharedPreferences("filename", Context.MODE_PRIVATE);
        boolean isDark = sharedPreferences.getBoolean("NightMode", false);
        if (isDark) {
            setTheme(R.style.dark);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.kuran);

        }

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment;
            switch (menuItem.getItemId()) {
                case R.id.namaz:
                    if (kuranPdfFragment == null)
                        kuranPdfFragment = new KuranPdfFragment();
                    selectedFragment = kuranPdfFragment;
                    break;
                case R.id.kuran:
                    if (kuranFragment == null)
                        kuranFragment = new KuranFragment();
                    selectedFragment = kuranFragment;
                    break;
                case R.id.asma:
                    if (asmaFragment == null)
                        asmaFragment = new AsmaFragment();
                    selectedFragment = asmaFragment;
                    break;
                case R.id.setting:
                    if (settingFragment == null)
                        settingFragment = new SettingFragment();
                    selectedFragment = settingFragment;
                    break;
                default:
                    selectedFragment = new Fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
