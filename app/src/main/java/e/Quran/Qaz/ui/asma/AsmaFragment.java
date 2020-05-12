package e.Quran.Qaz.ui.asma;

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

import e.Quran.Qaz.R;

import java.util.ArrayList;

import e.Quran.Qaz.model.Asma;

public class AsmaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_asma, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView surasList = view.findViewById(R.id.list_asma);


        String[] namesAllahArab = getResources().getStringArray(R.array.namesAllaArab);
        String[] namesAllahKazakh = getResources().getStringArray(R.array.namesAllaKazakh);

        final ArrayList<Asma> asma = new ArrayList<>();

        for (int i = 0; i < 99; i++) {

            asma.add(new Asma(i + 1, namesAllahKazakh[i], namesAllahArab[i]));
        }

        AsmaAdapter adapter = new AsmaAdapter(getContext(), asma);


        surasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AsmaDetailActivity.class);
                intent.putExtra(AsmaDetailActivity.ARG_ID, asma.get(position).getNumber());
                startActivity(intent);
            }
        });


        surasList.setAdapter(adapter);
    }


}
