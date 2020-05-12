package e.Quran.Qaz.ui.setting;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import e.Quran.Qaz.R;
import e.Quran.Qaz.model.SharedPref;

import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment {

    public static SharedPreferences sharedPreferences1;
    SharedPref sharedPref;

    private SeekBar seekBar1, seekBar2, seekBar3;
    private Switch switch1, switch2, switch3, switch4;
    private TextView translationValue, quranValue, transcriptionValue;
    int min1 = 10, current1 = 15;
    int min2 = 10, current2 = 15;
    int min3 = 10, current3 = 15;

    boolean switch11, switch22, switch33;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }




    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPref = new SharedPref(requireContext());


        switch4 = view.findViewById(R.id.switch4);
        if (sharedPref.loadNightModeState()) {
            switch4.setChecked(true);
        }
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPref.setSharedPreferences(true);
                    requireActivity().recreate();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingFragment()).commit();
                } else {

                    sharedPref.setSharedPreferences(false);
                    requireActivity().recreate();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingFragment()).commit();
                }
            }
        });


        sharedPreferences1 = getContext().getSharedPreferences("Settings", Activity.MODE_PRIVATE);

        int font1 = sharedPreferences1.getInt("font1", 14);
        int font2 = sharedPreferences1.getInt("font2", 14);
        int font3 = sharedPreferences1.getInt("font3", 14);


        switch11 = sharedPreferences1.getBoolean("switch111", true);
        switch22 = sharedPreferences1.getBoolean("switch222", true);
        switch33 = sharedPreferences1.getBoolean("switch333", true);


        switch1 = view.findViewById(R.id.switch1);
        switch2 = view.findViewById(R.id.switch2);
        switch3 = view.findViewById(R.id.switch3);


        seekBar1 = view.findViewById(R.id.seekBar1);
        seekBar2 = view.findViewById(R.id.seekBar2);
        seekBar3 = view.findViewById(R.id.seekBar3);


        quranValue = view.findViewById(R.id.quranValue);
        transcriptionValue = view.findViewById(R.id.transcriptionValue);
        translationValue = view.findViewById(R.id.translationValue);


        quranValue.setText(font1 + "");
        transcriptionValue.setText(font2 + "");
        translationValue.setText(font3 + "");


        seekBar1.setProgress(font1 - 10);
        quranValue.setText("" + font1);

        seekBar2.setProgress(font2 - 10);
        transcriptionValue.setText("" + font2);

        seekBar3.setProgress(font3 - 10);
        translationValue.setText("" + font3);

        switch1.setChecked(switch11);
        switch2.setChecked(switch22);
        switch3.setChecked(switch33);


        sharedPreferences1 = getContext().getSharedPreferences("test", MODE_PRIVATE);


        SharedPreferences.Editor edit1 = sharedPreferences1.edit();

        edit1.putBoolean("switch111", true);
        edit1.putBoolean("switch222", true);
        edit1.putBoolean("switch333", true);

        edit1.putInt("font1", 14);
        edit1.putInt("font2", 14);
        edit1.putInt("font3", 14);
        edit1.apply();

        //sharedPreferences1 = getContext().getPreferences(MODE_PRIVATE);


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    edit1.putBoolean("switch111", true);
                    edit1.commit();

                } else {
                    sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    edit1.putBoolean("switch111", false);
                    edit1.commit();
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);

                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    edit1.putBoolean("switch222", true);
                    edit1.commit();

                } else {
                    sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    edit1.putBoolean("switch222", false);
                    edit1.commit();
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);

                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    edit1.putBoolean("switch333", true);
                    edit1.commit();

                } else {
                    sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
                    SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                    edit1.putBoolean("switch333", false);
                    edit1.commit();
                }
            }
        });


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current1 = progress + min1;
                sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);

                SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                edit1.putInt("font1", current1);
                edit1.commit();


                quranValue.setText("" + current1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current2 = progress + min2;
                sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);

                SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                edit1.putInt("font2", current2);
                edit1.commit();
                transcriptionValue.setText("" + current2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current3 = progress + min3;

                sharedPreferences1 = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
                SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                edit1.putInt("font3", current3);
                edit1.commit();

                translationValue.setText("" + current3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }


}


