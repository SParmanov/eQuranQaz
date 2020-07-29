package e.Quran.Qaz.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import e.Quran.Qaz.R;
import e.Quran.Qaz.model.KuranDetailWord;


public class KuranDetailAdapter extends ArrayAdapter<KuranDetailWord> {
    private Typeface scheherazade;
    SharedPreferences sharedPreferences1;


    public KuranDetailAdapter(Context context, ArrayList<KuranDetailWord> users) {
        super(context, 0, users);
        scheherazade = Typeface.createFromAsset(context.getAssets(), "Scheherazade.ttf");
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        KuranDetailWord kuranDetailWord = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_kuran_detail, parent, false);


        }

        TextView id = convertView.findViewById(R.id.id);
        TextView translit = convertView.findViewById(R.id.translit);
        TextView kuranKazakh = convertView.findViewById(R.id.kuranKazakh);
        TextView kuranArabic = convertView.findViewById(R.id.kuranArabic);


        id.setText(kuranDetailWord.id + "");

        kuranKazakh.setText(kuranDetailWord.kuranKazakh);
        translit.setText(kuranDetailWord.translit);
        kuranArabic.setText(kuranDetailWord.kuranArabic);

        sharedPreferences1 = this.getContext().getSharedPreferences("Settings", Activity.MODE_PRIVATE);

        boolean switch1 = sharedPreferences1.getBoolean("switch111", true);
        boolean switch2 = sharedPreferences1.getBoolean("switch222", true);
        boolean switch3 = sharedPreferences1.getBoolean("switch333", true);

        int font1 = sharedPreferences1.getInt("font1", 14);
        int font2 = sharedPreferences1.getInt("font2", 14);
        int font3 = sharedPreferences1.getInt("font3", 14);

        if (!switch1)
            kuranArabic.setVisibility(convertView.GONE);
        if (!switch2)
            translit.setVisibility(convertView.GONE);
        if (!switch3)
            kuranKazakh.setVisibility(convertView.GONE);


        kuranArabic.setTypeface(scheherazade);


        kuranArabic.setTextSize(font1 + 30);
        translit.setTextSize(font2 + 3);
        kuranKazakh.setTextSize(font3 + 3);


        return convertView;
    }

}