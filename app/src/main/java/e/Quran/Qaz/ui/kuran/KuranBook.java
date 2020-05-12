package e.Quran.Qaz.ui.kuran;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import e.Quran.Qaz.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class KuranBook extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static SharedPreferences sharedPreferences1;

    private static final String BUNDLE_PAGE = "page";

    private PDFView pdfView;
    private int page = 0;


    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = this.getSharedPreferences("filename", MODE_PRIVATE);
        boolean isDark = sharedPreferences.getBoolean("NightMode", false);
        if (isDark) {
            setTheme(R.style.dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuran_book);


        sharedPreferences1 = this.getSharedPreferences("Settings", MODE_PRIVATE);
        page = sharedPreferences1.getInt("book1", 0);



        pdfView = findViewById(R.id.pdfView1);
        pdfView.fromAsset("quran_okyp.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                //.defaultPage(savedInstanceState == null ? page : savedInstanceState.getInt(BUNDLE_PAGE, page))
                .defaultPage(page)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(new DefaultScrollHandle(this))
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BUNDLE_PAGE, pdfView.getCurrentPage());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        page = pdfView.getCurrentPage();

        sharedPreferences1 = this.getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = sharedPreferences1.edit();
        edit1.putInt("book1", page);
        edit1.commit();
    }
}
