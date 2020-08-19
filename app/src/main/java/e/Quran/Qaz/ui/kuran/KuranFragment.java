package e.Quran.Qaz.ui.kuran;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.FastScroller;

import java.util.ArrayList;

import e.Quran.Qaz.R;
import e.Quran.Qaz.adapter.SurasAdapter;
import e.Quran.Qaz.model.Sura;

public class KuranFragment extends Fragment {

    RecyclerView suraRV;
    ArrayList<Sura> suras;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kuran, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        suraRV = view.findViewById(R.id.list_sura);

        FastScroller fastScroller =  view.findViewById(R.id.fastscroll);


        String[] kazakh_names = getResources().getStringArray(R.array.kazakh_names_new);
        String[] arabic_names = getResources().getStringArray(R.array.arabic_names);
        String[] kuranAddress = getResources().getStringArray(R.array.suraAddress);
        String[] suraLength = getResources().getStringArray(R.array.suraLength);


        suras = new ArrayList<>();
        for (int i = 0; i < 114; i++) {
            suras.add(new Sura(i + 1, kazakh_names[i], arabic_names[i], suraLength[i] + "", kuranAddress[i]));
        }


        SurasAdapter adapter = new SurasAdapter( suras , this::onClick);
        suraRV.setAdapter(adapter);
        fastScroller.setRecyclerView(suraRV);
        suraRV.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    public void onClick(int pos){
        Intent intent = new Intent(getActivity(), KuranDetailActivity.class);
        intent.putExtra(KuranDetailActivity.ARG_ID, suras.get(pos).id);
        startActivity(intent);

    }

}