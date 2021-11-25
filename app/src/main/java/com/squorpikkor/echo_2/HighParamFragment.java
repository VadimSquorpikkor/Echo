package com.squorpikkor.echo_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HighParamFragment extends Fragment {

    public static HighParamFragment newInstance() {
        return new HighParamFragment();
    }

    MainViewModel mViewModel;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.fragment_high_param, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        view.findViewById(R.id.momcps_button).setOnClickListener(v->mViewModel.getDataRegisters().setH_momcps(parseInt(R.id.momcps_text)));
        view.findViewById(R.id.cps_button).setOnClickListener(v->mViewModel.getDataRegisters().setH_cps(parseInt(R.id.cps_text)));
        view.findViewById(R.id.dr_button).setOnClickListener(v->mViewModel.getDataRegisters().setH_dr(parseFloat(R.id.dr_text)));
        view.findViewById(R.id.dose_button).setOnClickListener(v->mViewModel.getDataRegisters().setH_dose(parseFloat(R.id.dose_text)));
        view.findViewById(R.id.dr_err_button).setOnClickListener(v->mViewModel.getDataRegisters().setH_dr_err(parseFloat(R.id.dr_err_text)));
        view.findViewById(R.id.cps_err_button).setOnClickListener(v->mViewModel.getDataRegisters().setH_cps_err(parseFloat(R.id.cps_err_text)));

        SwitchCompat h_check = view.findViewById(R.id.overload_H_CheckBox);
        h_check.setOnClickListener(v->mViewModel.getDataRegisters().setH_state_prior((byte)(h_check.isChecked()?-1:1)));//todo это не верно, если блок не был подключен, то после отключения перегрузки он "подключится"

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
