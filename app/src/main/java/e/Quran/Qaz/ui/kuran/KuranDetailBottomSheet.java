package e.Quran.Qaz.ui.kuran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import e.Quran.Qaz.R;

public class KuranDetailBottomSheet extends BottomSheetDialogFragment {


    private int sura;
    private int ayat;
    private TextView numberTv;
    private TextView arabicTv;
    private TextView kazakhTv;
    
    private ImageView share;
    private ImageView close;

    private String arabicText;
    private String kazakhText;

    private SharedPreferences sharedPreferences;
    String[] kazakh_names;

    public KuranDetailBottomSheet(int sura, int ayat) {
        this.sura = sura;
        this.ayat = ayat;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_kuran_detail,container,false);
        kazakh_names = getResources().getStringArray(R.array.kazakh_names_new);
        numberTv = view.findViewById(R.id.number);
        arabicTv = view.findViewById(R.id.arabic);
        kazakhTv = view.findViewById(R.id.kazakh);
        close = view.findViewById(R.id.close);
        share = view.findViewById(R.id.share);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getContext().getSharedPreferences("filename", Context.MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("NightMode", false);
        if (state)
            getActivity().setTheme(R.style.dark);
        else
            getActivity().setTheme(R.style.AppTheme);

        String title =  kazakh_names[sura-1]+": "+ ayat;
        numberTv.setText(title);


        int arabic = getResources().getIdentifier("arabic" + "_" + (sura), "array", getActivity().getPackageName());
        int kazakh = getResources().getIdentifier("kazakh" + "_" + (sura), "array", getActivity().getPackageName());

        String[] kuranArabic = getResources().getStringArray(arabic);
        String[] kuranKazakh = getResources().getStringArray(kazakh);

        if(sura==1){
            arabicText = kuranArabic[ayat-1];
            kazakhText = kuranKazakh[ayat-1];
        }
        else {
            arabicText = kuranArabic[ayat];
            kazakhText = kuranKazakh[ayat];
        }

        arabicTv.setText(arabicText);
        kazakhTv.setText(kazakhText);

        arabicTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "scheherazade_font.ttf"));
        
        close.setOnClickListener(i -> dismiss());
        share.setOnClickListener(i ->share());

    }

    private void share() {
        String[] arabic_names = getResources().getStringArray(R.array.arabic_names);

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, arabicText+"\n"+arabic_names[sura-1]+": "+ayat+"\n"+kazakhText +"\n" +kazakh_names[sura-1]+": "+ayat);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Поделиться"));
    }

    @Override
    public int getTheme() {
        return R.style.RoundedBottomSheetDialogTheme;
    }
}
