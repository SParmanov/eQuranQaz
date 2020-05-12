package e.Quran.Qaz.ui.kuran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import e.Quran.Qaz.R;

import static android.view.View.GONE;
import static android.view.View.SCROLL_INDICATOR_BOTTOM;

public class SuraDetailActivity extends AppCompatActivity {
    public static final String ARG_ID = "kuranId";
    static String TAG = "SuraDetailActivity";
    private int id;
    private Typeface scheherazade;
    ListView listView;
    final ArrayList<KuranDetailWord> kuranDetailWords = new ArrayList<>();
    SharedPreferences sharedPreferences;
    public static SharedPreferences sharedPreferences1;


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

        //scheherazade = Typeface.createFromAsset(this.getAssets(), "Scheherazade.ttf");


        setContentView(R.layout.activity_kuran_detail);
        id = getIntent().getIntExtra(ARG_ID, 1);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon);

        String[] arabic_names = getResources().getStringArray(R.array.arabic_names);
        String[] kazakh_names = getResources().getStringArray(R.array.kazakh_names);

        int index = id - 1;
        String title = (index + 1) + " " + kazakh_names[index] + " " + arabic_names[index];
        setTitleColor(000);
        getSupportActionBar().setTitle(title);
        scheherazade = Typeface.createFromAsset(this.getAssets(), "Scheherazade.ttf");

        int kazakh = getResources().getIdentifier("kazakh" + "_" + (id), "array", this.getPackageName());
        int trans = getResources().getIdentifier("translit" + "_" + (id), "array", this.getPackageName());
        int arabic = getResources().getIdentifier("arabic" + "_" + (id), "array", this.getPackageName());

        //String[] arabic_names = getResources().getStringArray(R.array.arabic_names);
        //String[] kazakh_names = getResources().getStringArray(R.array.kazakh_names_new);
        String[] kazakh_names_mean = getResources().getStringArray(R.array.kazakh_names_mean);


        String[] translit = getResources().getStringArray(trans);
        String[] kuranKazakh = getResources().getStringArray(kazakh);
        String[] kuranArabic = getResources().getStringArray(arabic);


        if (id == 1) {
            for (int i = 0; i < translit.length; i++) {
                kuranDetailWords.add(new KuranDetailWord((i + 1) + "", translit[i], kuranKazakh[i], kuranArabic[i]));
            }
        } else {
            for (int i = 1; i < translit.length; i++) {

                kuranDetailWords.add(new KuranDetailWord(i + "", translit[i], kuranKazakh[i], kuranArabic[i]));
            }
        }


        listView = findViewById(R.id.kuranDetailList);


        TextView header_text1, header_text2, header_text3;

        View headerView = getLayoutInflater().inflate(R.layout.item_kuran_detail_header, null);

        listView.addHeaderView(headerView);

        header_text1 = findViewById(R.id.header_text1);
        header_text2 = findViewById(R.id.header_text2);
        header_text3 = findViewById(R.id.header_text3);

        if (id == 1)
            header_text3.setVisibility(GONE);
        header_text3.setTypeface(scheherazade);
        header_text1.setText(id + "." + kazakh_names[id - 1]);
        header_text2.setText(kazakh_names_mean[id - 1]);
        header_text3.setText(kuranArabic[0]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.book:
                intent = new Intent(this, KuranBook.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = this.getSharedPreferences("filename", Context.MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("NightMode", false);
        if (state)
            setTheme(R.style.dark);
        else
            setTheme(R.style.AppTheme);


        Log.e(TAG, "onStart()");
        KuranDetailAdapter itemsAdapter = new KuranDetailAdapter(this, kuranDetailWords);
        listView.setAdapter(itemsAdapter);


        sharedPreferences1 = this.getSharedPreferences("Visible", MODE_PRIVATE);
        int lastVisible = sharedPreferences1.getInt(id + "", 0);


        listView.setVerticalScrollbarPosition(SCROLL_INDICATOR_BOTTOM);
        listView.setSelection(lastVisible);


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
        SharedPreferences.Editor edit1 = sharedPreferences1.edit();
        int a = listView.getFirstVisiblePosition();

        edit1.putInt(id + "", a);
        edit1.commit();
//        Log.e(TAG, h "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }


}
