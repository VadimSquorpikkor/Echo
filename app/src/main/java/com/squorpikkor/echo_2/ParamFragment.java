package com.squorpikkor.echo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ParamFragment extends Fragment {

    public static ParamFragment newInstance() {
        return new ParamFragment();
    }

    MainViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_param, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        EditText momcpsText = view.findViewById(R.id.momcps_text);
        view.findViewById(R.id.momcps_button).setOnClickListener(v->mViewModel.getDataRegisters().setG_momcps(parseInt(momcpsText)));

        EditText cpsText = view.findViewById(R.id.cps_text);
        view.findViewById(R.id.cps_button).setOnClickListener(v->mViewModel.getDataRegisters().setG_cps(parseInt(cpsText)));

        EditText drText = view.findViewById(R.id.dr_text);
        view.findViewById(R.id.dr_button).setOnClickListener(v->mViewModel.getDataRegisters().setG_dr(parseFloat(drText)));

        EditText batText = view.findViewById(R.id.bat_text);
        view.findViewById(R.id.bat_button).setOnClickListener(v->mViewModel.getDataRegisters().setBatCap((short)parseInt(batText)));

        // todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится". Сделать: если -1, то temp = getG_state_prior, set -1; иначе set temp
        CheckBox g_check = view.findViewById(R.id.overload_G_CheckBox);
        g_check.setOnClickListener(v->mViewModel.getDataRegisters().setG_state_prior((byte)(g_check.isChecked()?-1:1)));

        CheckBox h_check = view.findViewById(R.id.overload_H_CheckBox);
        h_check.setOnClickListener(v->mViewModel.getDataRegisters().setH_state_prior((byte)(h_check.isChecked()?-1:1)));//todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится"

        CheckBox n_check = view.findViewById(R.id.overload_N_CheckBox);
        n_check.setOnClickListener(v->mViewModel.getDataRegisters().setN_state_prior((byte)(n_check.isChecked()?-1:1)));//todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится"

        return view;
    }

    private void getAndSendInt(EditText edit, View button) {
        button.setOnClickListener(v->mViewModel.getDataRegisters().setG_momcps(parseInt(edit)));
    }

    private int parseInt(EditText v) {
        return Integer.parseInt(v.getText().toString());
    }

    private float parseFloat(EditText v) {
        return Float.parseFloat(v.getText().toString());
    }
}
