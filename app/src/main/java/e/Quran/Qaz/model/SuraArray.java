package e.Quran.Qaz.model;

import androidx.appcompat.app.AppCompatActivity;

import e.Quran.Qaz.R;

public class SuraArray extends AppCompatActivity {
    public String[] getKazakh_names() {
        return kazakh_names;
    }

    public String[] getArabic_names() {
        return arabic_names;
    }

    public String[] getKuranAddress() {
        return kuranAddress;
    }

    public String[] getSuraLength() {
        return suraLength;
    }

    String[] kazakh_names = getResources().getStringArray(R.array.kazakh_names_new);
    String[] arabic_names = getResources().getStringArray(R.array.arabic_names);
    String[] kuranAddress = getResources().getStringArray(R.array.suraAddress);
    String[] suraLength = getResources().getStringArray(R.array.suraLength);


}
