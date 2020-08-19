package e.Quran.Qaz.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;

import java.util.ArrayList;

import e.Quran.Qaz.R;
import e.Quran.Qaz.model.KuranDetailWord;


public class KuranDetailAdapter extends RecyclerView.Adapter<KuranDetailAdapter.ViewHolder> implements SectionTitleProvider {
    SharedPreferences sharedPreferences1;
    private ArrayList<KuranDetailWord> ayats;

    public KuranDetailAdapter(ArrayList<KuranDetailWord> ayats) {
        this.ayats = ayats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kuran_detail, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        KuranDetailWord ayat = ayats.get(position);

        holder.id.setText(String.valueOf(ayat.id));
        holder.kuranKazakh.setText(ayat.kuranKazakh);
        holder.translit.setText(ayat.translit);
        holder.kuranArabic.setText(ayat.kuranArabic );

        sharedPreferences1 = holder.itemView.getContext().getSharedPreferences("Settings", Activity.MODE_PRIVATE);

        boolean switch1 = sharedPreferences1.getBoolean("switch111", true);
        boolean switch2 = sharedPreferences1.getBoolean("switch222", true);
        boolean switch3 = sharedPreferences1.getBoolean("switch333", true);

        int font1 = sharedPreferences1.getInt("font1", 14);
        int font2 = sharedPreferences1.getInt("font2", 14);
        int font3 = sharedPreferences1.getInt("font3", 14);

        if (!switch1)
            holder.kuranArabic.setVisibility(View.GONE);
        if (!switch2)
            holder.translit.setVisibility(View.GONE);
        if (!switch3)
            holder.kuranKazakh.setVisibility(View.GONE);


        holder.kuranArabic.setTextSize(font1 + 30);
        holder.translit.setTextSize(font2 + 3);
        holder.kuranKazakh.setTextSize(font3 + 3);

    }

    @Override
    public String getSectionTitle(int position) {
        return String.valueOf(++position);
    }

    @Override
    public int getItemCount() {
        return ayats.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        TextView id ;
        TextView translit ;
        TextView kuranKazakh ;
        TextView kuranArabic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            translit = itemView.findViewById(R.id.translit);
            kuranKazakh = itemView.findViewById(R.id.kuranKazakh);
            kuranArabic = itemView.findViewById(R.id.kuranArabic);

        }
    }

}