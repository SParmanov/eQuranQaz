package e.Quran.Qaz.ui.asma;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import e.Quran.Qaz.R;

public class AsmaDetailActivity extends AppCompatActivity {

    public static final String ARG_ID = "asmaId";
    private int id;
    SharedPreferences sharedPreferences;

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
        setContentView(R.layout.activity_asma_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = getIntent().getIntExtra(ARG_ID, 1);

        String[] names = getResources().getStringArray(R.array.namesAlla);
        String[] namesDesc = getResources().getStringArray(R.array.names_descs);
        String[] namesDesc1 = getResources().getStringArray(R.array.namesAllaKazakh1);

        ((TextView) findViewById(R.id.asmaDetail)).setText(names[id - 1] + "\n" + namesDesc1[id - 1] + "\n \n" + namesDesc[id - 1]);

        Log.d("myLogs", namesDesc[id - 1]);
        int index = id - 1;
        String title = names[index];
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


