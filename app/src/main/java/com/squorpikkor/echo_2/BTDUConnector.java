package com.squorpikkor.echo_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BTDUConnector {

    private Map<String, Short> idMap;

    private DataRegister dataRegister;

    public BTDUConnector(/*Spinner spinner_ch1, Spinner spinner_ch2, Spinner spinner_ch3, */DataRegister dataRegister) {
        idMap = new HashMap<>();
        idMap.put(" --- ", (short)0);
        idMap.put("БДКГ-04", Constant.ID_BDKG04);
        idMap.put("БДКГ-03", Constant.ID_BDKG03);
        idMap.put("БДКГ-11", Constant.ID_BDKG11);
        idMap.put("БДКГ-05C", Constant.ID_BDKG05C);
        idMap.put("БДКГ-19M", Constant.ID_BDKG19M);
        idMap.put("БДКГ-28", Constant.ID_BDKG28);
        idMap.put("БДКГ-32", Constant.ID_BDKG32);
        idMap.put("БДКГ-34", Constant.ID_BDKG34);
        idMap.put("БДКГ-35", Constant.ID_BDKG35);
        idMap.put("БДКГ-11M", Constant.ID_BDKG11M);
        idMap.put("БДКН-05", Constant.ID_BDKN05);
        this.dataRegister = dataRegister;
        switchOffAllDU();

    }

    private void switchOffAllDU() {
        dataRegister.setG_state_prior((byte)0);
        dataRegister.setG_state_id((byte)0);
        dataRegister.setH_state_prior((byte)0);
        dataRegister.setH_state_id((byte)0);
        dataRegister.setN_state_prior((byte)0);
        dataRegister.setN_state_id((byte)0);
        dataRegister.setCh1_state_id(0);
        dataRegister.setCh2_state_id(0);
        dataRegister.setCh3_state_id(0);
    }

    public Map<String, Short> getIdMap() {
        return idMap;
    }

    public ArrayList<String> getDUNames() {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Short> entry:idMap.entrySet()) {
            list.add(entry.getKey());
        }
        Collections.sort(list);
        return list;
    }

    public void setChannel_1(String key) {
        short id = idMap.get(key);
//        if (id==0) dataRegister.setCh1_state_id(0);
        //TODO в строке 65299 данные о подключенном блоке
        dataRegister.setCh1_state_id(65299);//65299
//        dataRegister.setCh1_state_id(id);//65299
        setPrior(id);
    }

    public void setChannel_2(String key) {
        short id = idMap.get(key);
        dataRegister.setCh2_state_id(id);
        setPrior(id);
    }

    public void setChannel_3(String key) {
        short id = idMap.get(key);
        dataRegister.setCh3_state_id(id);
        setPrior(id);
    }

    private void setPrior(short id) {
        if (id==0)return;
        if (id == Constant.ID_BDKG04) {
            dataRegister.setH_state_prior((byte) 1);
            dataRegister.setH_state_id((byte)id);
            startH();
        } else if (id == Constant.ID_BDKN05) {
            dataRegister.setN_state_prior((byte) 1);
            dataRegister.setN_state_id((byte) id);
            startN();
        } else {
            dataRegister.setG_state_prior((byte) 3);
            dataRegister.setG_state_id((byte) id);
            startG();
        }
    }

    //todo брать значения в viewModel
    private void startG() {
        dataRegister.setG_dr(0.098f);
        dataRegister.setG_cps(345);
        dataRegister.setG_momcps(345);
        dataRegister.setG_dose(100);
    }

    private void startH() {
        dataRegister.setH_dr(0.098f);
        dataRegister.setH_cps(345);
        dataRegister.setH_momcps(345);
        dataRegister.setH_dose(100);
    }

    private void startN() {
        dataRegister.setN_dr(0.098f);
        dataRegister.setN_cps(345);
        dataRegister.setN_momcps(345);
        dataRegister.setN_dose(100);
    }

}
