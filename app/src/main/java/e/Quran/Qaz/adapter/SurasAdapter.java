package e.Quran.Qaz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;

import java.util.ArrayList;

import e.Quran.Qaz.R;
import e.Quran.Qaz.listener.SuraClickLintener;
import e.Quran.Qaz.model.Sura;

public class SurasAdapter extends RecyclerView.Adapter<SurasAdapter.ViewHolder>   implements SectionTitleProvider {

    SuraClickLintener listener;
    private ArrayList<Sura> suras;


    public SurasAdapter( ArrayList<Sura> suras , SuraClickLintener lis) {
        this.suras = suras;
        this.listener = lis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sura, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Sura sura = suras.get(position);

        holder.empty.setText(String.valueOf(sura.getId()));
        holder.kazakh_names.setText(sura.getKazakh_names());
        holder.arabic_names.setText(sura.getArabic_names());
        holder.lengthSura.setText(sura.getLengthSura() + " аят");
        holder.kuranAddress.setText(sura.getKuranAddress());

        holder.itemView.setOnClickListener(v -> listener.onClick(position));

    }

    @Override
    public String getSectionTitle(int position) {
        return String.valueOf(++position);
    }

    @Override
    public int getItemCount() {
        return suras.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView empty;
        TextView kazakh_names;
        TextView arabic_names;
        TextView lengthSura;
        TextView kuranAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            empty = itemView.findViewById(R.id.id);
            kazakh_names = itemView.findViewById(R.id.kazakh_names);
            arabic_names = itemView.findViewById(R.id.arabic_names);
            lengthSura = itemView.findViewById(R.id.lengthSura);
            kuranAddress = itemView.findViewById(R.id.kuranAddress);


        }
    }
}