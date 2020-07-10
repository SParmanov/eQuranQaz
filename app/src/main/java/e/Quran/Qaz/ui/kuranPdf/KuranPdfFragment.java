package e.Quran.Qaz.ui.kuranPdf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import e.Quran.Qaz.R;

import static android.content.Context.MODE_PRIVATE;


public class KuranPdfFragment extends Fragment {
    public static SharedPreferences sharedPreferences1;

    private static final String BUNDLE_PAGE = "page";

    private PDFView pdfView;
    private int page = 0;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_namaz, container, false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        page = sharedPreferences1.getInt("book1", 0);

        pdfView = view.findViewById(R.id.pdfView);
        pdfView.fromAsset("define_kuran.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)

                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)

                .defaultPage(savedInstanceState == null ? page : savedInstanceState.getInt(BUNDLE_PAGE, page))
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(new DefaultScrollHandle(getContext()))
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
    }

    @Override
    public void onStop() {
        super.onStop();
        page = pdfView.getCurrentPage();

        sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = sharedPreferences1.edit();
        edit1.putInt("book1", page);
        edit1.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_PAGE, pdfView.getCurrentPage());
        super.onSaveInstanceState(outState);
    }
}
