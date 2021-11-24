package com.squorpikkor.echo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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

        // todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится". Сделать: если -1, то temp = getG_state_prior, set -1; иначе set temp
        SwitchCompat g_check = view.findViewById(R.id.overload_G_CheckBox);
        g_check.setOnClickListener(v->mViewModel.getDataRegisters().setG_state_prior((byte)(g_check.isChecked()?-1:1)));

        SwitchCompat h_check = view.findViewById(R.id.overload_H_CheckBox);
        h_check.setOnClickListener(v->mViewModel.getDataRegisters().setH_state_prior((byte)(h_check.isChecked()?-1:1)));//todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится"

        SwitchCompat n_check = view.findViewById(R.id.overload_N_CheckBox);
        n_check.setOnClickListener(v->mViewModel.getDataRegisters().setN_state_prior((byte)(n_check.isChecked()?-1:1)));//todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится"


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
