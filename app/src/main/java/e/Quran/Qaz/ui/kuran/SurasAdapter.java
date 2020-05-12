package e.Quran.Qaz.ui.kuran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import e.Quran.Qaz.R;
import e.Quran.Qaz.model.Sura;

public class SurasAdapter extends ArrayAdapter<Sura> {

    private ArrayList<Sura> suras;
    private Typeface scheherazade;

    public SurasAdapter(Context context, ArrayList<Sura> users) {
        super(context, 0, users);
        scheherazade = Typeface.createFromAsset(context.getAssets(), "Scheherazade.ttf");
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sura, parent, false);


        TextView empty = convertView.findViewById(R.id.id);
        TextView kazakh_names = convertView.findViewById(R.id.kazakh_names);
        TextView arabic_names = convertView.findViewById(R.id.arabic_names);
        TextView lengthSura = convertView.findViewById(R.id.lengthSura);
        TextView kuranAddress = convertView.findViewById(R.id.kuranAddress);


        Sura sura = getItem(position);


        empty.setText(String.format("%d%s", sura.getId(), ""));
        kazakh_names.setText(sura.getKazakh_names());
        arabic_names.setText(sura.getArabic_names());
        lengthSura.setText(sura.getLengthSura() + " аят");
        kuranAddress.setText(sura.getKuranAddress());


        arabic_names.setTypeface(scheherazade);


        return convertView;
    }
}