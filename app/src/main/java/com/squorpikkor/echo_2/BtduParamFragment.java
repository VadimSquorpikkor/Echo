package com.squorpikkor.echo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class BtduParamFragment extends Fragment {

    public static BtduParamFragment newInstance() {
        return new BtduParamFragment();
    }

    MainViewModel mViewModel;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.fragment_btdu_param, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        view.findViewById(R.id.temp_button).setOnClickListener(v->mViewModel.getDataRegisters().setTemperatureHL(parseInt(R.id.temp_text)));
        view.findViewById(R.id.bat_button).setOnClickListener(v->mViewModel.getDataRegisters().setBatCap((short)parseInt(R.id.bat_text)));
//        view.findViewById(R.id.work_time_button).setOnClickListener(v->mViewModel.getDataRegisters().setWorkTime(parseInt(R.id.work_time_text)));
        view.findViewById(R.id.bat_temp_button).setOnClickListener(v->mViewModel.getDataRegisters().setBatmon_temperature((short)parseInt(R.id.bat_temp_text)));
        view.findViewById(R.id.diag_ch1_button).setOnClickListener(v->mViewModel.getDataRegisters().setCh_diag_1(parseInt(R.id.diag_ch1_text)));
        view.findViewById(R.id.diag_ch2_button).setOnClickListener(v->mViewModel.getDataRegisters().setCh_diag_2(parseInt(R.id.diag_ch2_text)));
        view.findViewById(R.id.diag_ch3_button).setOnClickListener(v->mViewModel.getDataRegisters().setCh_diag_3(parseInt(R.id.diag_ch3_text)));
        view.findViewById(R.id.diag_adapter_button).setOnClickListener(v->mViewModel.getDataRegisters().setAdapter_diag(parseInt(R.id.diag_adapter_text)));
        view.findViewById(R.id.g_cps_limit_button).setOnClickListener(v->mViewModel.getDataRegisters().setG_cps_limit(parseFloat(R.id.g_cps_limit_text)));

        ArrayList<String> list = mViewModel.getBtduConnector().getDUNames();

        Spinner ch1 = view.findViewById(R.id.spinner_1);
        Spinner ch2 = view.findViewById(R.id.spinner_2);
        Spinner ch3 = view.findViewById(R.id.spinner_3);
        ArrayAdapter ad = new ArrayAdapter(requireActivity(), R.layout.spinner_item, list);
        ch1.setAdapter(ad);
        ch2.setAdapter(ad);
        ch3.setAdapter(ad);


        ch1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mViewModel.getBtduConnector().setChannel_1(list.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ch2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mViewModel.getBtduConnector().setChannel_2(list.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ch3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mViewModel.getBtduConnector().setChannel_3(list.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private int parseInt(int id) {
        EditText v = view.findViewById(id);
        return Integer.parseInt(v.getText().toString());
    }

    private float parseFloat(int id) {
        EditText v = view.findViewById(id);
        return Float.parseFloat(v.getText().toString());
    }
}
