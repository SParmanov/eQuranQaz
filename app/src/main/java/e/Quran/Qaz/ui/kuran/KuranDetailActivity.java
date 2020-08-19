package e.Quran.Qaz.ui.kuran;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.FastScroller;

import java.util.ArrayList;
import java.util.Objects;

import e.Quran.Qaz.R;
import e.Quran.Qaz.adapter.KuranDetailAdapter;
import e.Quran.Qaz.model.KuranDetailWord;

import static android.view.View.GONE;
import static android.view.View.SCROLL_INDICATOR_BOTTOM;



public class KuranDetailActivity extends AppCompatActivity {
    public static final String ARG_ID = "kuranId";
    public static String TAG = "SuraDetailActivity";
    private int id;

    private RecyclerView listRV;
    private final ArrayList<KuranDetailWord> ayats = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private LinearLayoutManager lm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = this.getSharedPreferences("filename", Context.MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("NightMode", false);
        if (state)
            setTheme(R.style.dark);
        else
            setTheme(R.style.AppTheme);


        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");


        setContentView(R.layout.activity_kuran_detail);
        id = getIntent().getIntExtra(ARG_ID, 1);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon);

        String[] arabic_names = getResources().getStringArray(R.array.arabic_names);
        String[] kazakh_names = getResources().getStringArray(R.array.kazakh_names);

        int index = id - 1;
        String title = (index + 1) + " " + kazakh_names[index] + " " + arabic_names[index];
        setTitleColor(0);
        getSupportActionBar().setTitle(title);


        int kazakh = getResources().getIdentifier("kazakh" + "_" + (id), "array", this.getPackageName());
        int trans = getResources().getIdentifier("translit" + "_" + (id), "array", this.getPackageName());
        int arabic = getResources().getIdentifier("arabic" + "_" + (id), "array", this.getPackageName());



        String[] kazakh_names_mean = getResources().getStringArray(R.array.kazakh_names_mean);
        String[] translit = getResources().getStringArray(trans);
        String[] kuranKazakh = getResources().getStringArray(kazakh);
        String[] kuranArabic = getResources().getStringArray(arabic);



        if (id == 1) {
            for (int i = 0; i < translit.length; i++) {
                ayats.add(new KuranDetailWord((i + 1) + "", translit[i], kuranKazakh[i], kuranArabic[i]));
            }
        } else {
            for (int i = 1; i < translit.length; i++) {

                ayats.add(new KuranDetailWord(i + "", translit[i], kuranKazakh[i], kuranArabic[i]));
            }
        }


        listRV = findViewById(R.id.kuranDetailList);


        TextView header_text1, header_text2, header_text3;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = this.getSharedPreferences("filename", Context.MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("NightMode", false);
        if (state)
            setTheme(R.style.dark);
        else
            setTheme(R.style.AppTheme);


        FastScroller fastScroller =  findViewById(R.id.fastscroll);
        Log.e(TAG, "onStart()");
        KuranDetailAdapter itemsAdapter = new KuranDetailAdapter(ayats);
        listRV.setAdapter(itemsAdapter);
        fastScroller.setRecyclerView(listRV);

        sharedPreferences = this.getSharedPreferences("Visible", MODE_PRIVATE);
        int lastVisible = sharedPreferences.getInt(id + "", 0);

        lm = new LinearLayoutManager(this);
        listRV.setLayoutManager(lm);
        listRV.setId(lastVisible);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor edit1 = sharedPreferences.edit();
        int a = lm.findFirstVisibleItemPosition();

        edit1.putInt(id + "", a);
        edit1.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }


}
