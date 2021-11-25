package com.squorpikkor.echo_2;

import static com.squorpikkor.echo_2.Constant.DEF_DATA_REGISTERS_2;

import android.util.Log;

import java.util.Arrays;

public class DataRegister {

    private byte[] registerData;
    byte[] out;

    public DataRegister() {
        this.registerData = DEF_DATA_REGISTERS_2;
    }

    /**Берет массив байт БЕЗ CRC, рассчитывает CRC и добавляет полученные байты в конец массива. Полученный массив летит в RSA*/
    public byte[] getRegisterData() {
        out = Arrays.copyOf(registerData, registerData.length+2);
        raskolbasG_momcps();
        raskolbasG_cps();
        raskolbasG_dr();
        raskolbasH_cps();
        raskolbasH_dr();

//        byte[] crc = Converter.integerToByte(Converter.calcCRC(registerData));
        byte[] crc = Converter.integerToByte(Converter.calcCRC_2ignore(out));
        out[out.length-1]=crc[0];
        out[out.length-2]=crc[1];
        return out;
    }

    private int getG_momcps() {
        byte[] tempData = new byte[4];
        tempData[3] = registerData[7];
        tempData[2] = registerData[8];
        tempData[1] = registerData[9];
        tempData[0] = registerData[10];
        return (Converter.toInt32(tempData, 0));
    }

    private float getG_cps() {
        byte[] tempData = new byte[4];
        tempData[3] = registerData[11];
        tempData[2] = registerData[12];
        tempData[1] = registerData[13];
        tempData[0] = registerData[14];
        return (Converter.toFloat(tempData, 0));
    }

    private float getG_dr() {
        byte[] tempData = new byte[4];
        tempData[3] = registerData[19];
        tempData[2] = registerData[20];
        tempData[1] = registerData[21];
        tempData[0] = registerData[22];
        return (Converter.toFloat(tempData, 0));
    }

    private float getH_cps() {
        byte[] tempData = new byte[4];
        tempData[3] = registerData[71];
        tempData[2] = registerData[72];
        tempData[1] = registerData[73];
        tempData[0] = registerData[74];
        return (Converter.toFloat(tempData, 0));
    }

    private float getH_dr() {
        byte[] tempData = new byte[4];
        tempData[3] = registerData[79];
        tempData[2] = registerData[80];
        tempData[1] = registerData[81];
        tempData[0] = registerData[82];
        return (Converter.toFloat(tempData, 0));
    }

    private double raskolbas(float value, int maxPercent) {
        double percent = Math.random()*maxPercent*2-maxPercent;
        double delta = value*(percent/100);
        return value+delta;
    }

    /**Чтобы G_momcps не был ровной линией, расколбашивает значение +- 0-4%*/
    private void raskolbasG_momcps() {
        int cps = getG_momcps();
        byte[] bytes = Converter.integerToByte((int)raskolbas(cps, 4));
        out[7]  = bytes[3];
        out[8]  = bytes[2];
        out[9]  = bytes[1];
        out[10] = bytes[0];
    }

    private void raskolbasG_cps() {
        float cps = getG_cps();
        byte[] bytes = Converter.floatToByte((float)raskolbas(cps, 4));
        out[11] = bytes[3];
        out[12] = bytes[2];
        out[13] = bytes[1];
        out[14] = bytes[0];
    }

    private void raskolbasG_dr() {
        float cps = getG_dr()*1000000;
        byte[] bytes = Converter.floatToByte((float)(raskolbas(cps, 4))/1000000);
        out[19] = bytes[3];
        out[20] = bytes[2];
        out[21] = bytes[1];
        out[22] = bytes[0];
    }

    private void raskolbasH_cps() {
        float cps = getH_cps();
        byte[] bytes = Converter.floatToByte((float)raskolbas(cps, 4));
        out[71] = bytes[3];
        out[72] = bytes[2];
        out[73] = bytes[1];
        out[74] = bytes[0];
    }

    private void raskolbasH_dr() {
        float cps = getH_dr()*1000000;
        byte[] bytes = Converter.floatToByte((float)(raskolbas(cps, 4))/1000000);
        out[79] = bytes[3];
        out[80] = bytes[2];
        out[81] = bytes[1];
        out[82] = bytes[0];
    }

    //Register 0
    public void setAcqTime(short value) {
        byte[] bytes = Converter.shortToByte(value);
        registerData[3] = bytes[1];
        registerData[4] = bytes[0];
    }

    //todo
    //Register 1
    public void setTemperatureHL(int value) {
        registerData[5] = (byte)value;
    }

    //Register 2-3
    public void setG_momcps(int g_momcps) {
        byte[] bytes = Converter.integerToByte(g_momcps);
        registerData[7]  = bytes[3];
        registerData[8]  = bytes[2];
        registerData[9]  = bytes[1];
        registerData[10] = bytes[0];
    }

    //Register 4-5
    public void setG_cps(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[11] = bytes[3];
        registerData[12] = bytes[2];
        registerData[13] = bytes[1];
        registerData[14] = bytes[0];
    }

    //Register 6-7
    public void setG_cps_error(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[15] = bytes[3];
        registerData[16] = bytes[2];
        registerData[17] = bytes[1];
        registerData[18] = bytes[0];
    }

    //Register 8-9
    public void setG_dr(float g_dr) {
        byte[] bytes = Converter.floatToByte(g_dr/1000000);
        registerData[19] = bytes[3];
        registerData[20] = bytes[2];
        registerData[21] = bytes[1];
        registerData[22] = bytes[0];
    }

    //Register 10-11
    public void setG_dr_error(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[23] = bytes[3];
        registerData[24] = bytes[2];
        registerData[25] = bytes[1];
        registerData[26] = bytes[0];
    }

    //Register 12-13
    public void setG_dose(float value) {
        byte[] bytes = Converter.floatToByte(value/1000000);
        registerData[27] = bytes[3];
        registerData[28] = bytes[2];
        registerData[29] = bytes[1];
        registerData[30] = bytes[0];
    }

    //Register 14 is not used (registerData[31] and registerData[32])

    //Register 15-16
    public void setLifeTime(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[33] = bytes[3];
        registerData[34] = bytes[2];
        registerData[35] = bytes[1];
        registerData[36] = bytes[0];
    }

    //Register 17
    public void setBatCap(short value) {
        byte[] bytes = Converter.shortToByte(value);
        registerData[37] = bytes[1];
        registerData[38] = bytes[0];
    }

    //Register 18 is not used (registerData[39] and registerData[40])

    //Register 19
    public void setWorkTime(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[39] = bytes[3];
        registerData[40] = bytes[2];
        registerData[41] = bytes[1];
        registerData[42] = bytes[0];
    }

    //Register 20-21
    public void setN_momcps(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[43] = bytes[3];
        registerData[44] = bytes[2];
        registerData[45] = bytes[1];
        registerData[46] = bytes[0];
    }

    //Register 22-23
    public void setN_cps(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[47] = bytes[3];
        registerData[48] = bytes[2];
        registerData[49] = bytes[1];
        registerData[50] = bytes[0];
    }

    //Register 24-25
    public void setN_cps_err(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[51] = bytes[3];
        registerData[52] = bytes[2];
        registerData[53] = bytes[1];
        registerData[54] = bytes[0];
    }

    //Register 26-27
    public void setN_dr(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[55] = bytes[3];
        registerData[56] = bytes[2];
        registerData[57] = bytes[1];
        registerData[58] = bytes[0];
    }

    //Register 28-29
    public void setN_dr_err(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[59] = bytes[3];
        registerData[60] = bytes[2];
        registerData[61] = bytes[1];
        registerData[62] = bytes[0];
    }

    //Register 30-31
    public void setN_dose(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[63] = bytes[3];
        registerData[64] = bytes[2];
        registerData[65] = bytes[1];
        registerData[66] = bytes[0];
    }

    //Register 32-33
    public void setH_momcps(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[67] = bytes[3];
        registerData[68] = bytes[2];
        registerData[69] = bytes[1];
        registerData[70] = bytes[0];
    }

    //Register 34-35
    public void setH_cps(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[71] = bytes[3];
        registerData[72] = bytes[2];
        registerData[73] = bytes[1];
        registerData[74] = bytes[0];
    }

    //Register 36-37
    public void setH_cps_err(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[75] = bytes[3];
        registerData[76] = bytes[2];
        registerData[77] = bytes[1];
        registerData[78] = bytes[0];
    }

    //Register 38-39
    public void setH_dr(float value) {
        byte[] bytes = Converter.floatToByte(value/1000000);
        registerData[79] = bytes[3];
        registerData[80] = bytes[2];
        registerData[81] = bytes[1];
        registerData[82] = bytes[0];
    }

    //Register 40-41
    public void setH_dr_err(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[83] = bytes[3];
        registerData[84] = bytes[2];
        registerData[85] = bytes[1];
        registerData[86] = bytes[0];
    }

    //Register 42-43
    public void setH_dose(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[87] = bytes[3];
        registerData[88] = bytes[2];
        registerData[89] = bytes[1];
        registerData[90] = bytes[0];
    }

    //Register 44
    public void setCh1_state_id(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[91] = bytes[1];
        registerData[92] = bytes[0];
    }

    //Register 45
    public void setCh2_state_id(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[93] = bytes[1];
        registerData[94] = bytes[0];
    }

    //Register 46
    public void setCh3_state_id(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[95] = bytes[1];
        registerData[96] = bytes[0];
    }

    //Register 47
    public void setG_state_prior(byte value) {
        registerData[97] = value;
    }

    //Register 47 тоже
    public void setG_state_id(byte value) {
        registerData[98] = value;
    }

    //Register 48
    public void setN_state_prior(byte value) {
        registerData[99] = value;
    }

    //Register 48 тоже
    public void setN_state_id(byte value) {
        registerData[100] = value;
    }

    //Register 49
    public void setH_state_prior(byte value) {
        registerData[101] = value;
    }

    //Register 49 тоже
    public void setH_state_id(byte value) {
        registerData[102] = value;
    }

    //Register 50 НЕ ИСПОЛЬЗУЕТСЯ, БЫЛ ДЛЯ ДИАГНОСТИКИ

    //Register 51
    public void setBatmon_temperature(short value) {
        byte[] bytes = Converter.shortToByte(value);
        registerData[105] = bytes[1];
        registerData[106] = bytes[0];
    }

    //Register 52
    public void setBatmon_v(short value) {
        byte[] bytes = Converter.shortToByte(value);
        registerData[107] = bytes[1];
        registerData[108] = bytes[0];
    }

    //Register 53
    public void setBatmon_cur(short value) {
        byte[] bytes = Converter.shortToByte(value);
        registerData[109] = bytes[1];
        registerData[110] = bytes[0];
    }

    //Register 54
    public void setBatmon_ica(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[111] = bytes[1];
        registerData[112] = bytes[0];
    }

    //Register 55
    public void setBatmon_icacur(short value) {
        byte[] bytes = Converter.shortToByte(value);
        registerData[113] = bytes[1];
        registerData[114] = bytes[0];
    }

    //Register 56
    public void setBatmon_cca(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[115] = bytes[1];
        registerData[116] = bytes[0];
    }

    //Register 57
    public void setBatmon_dca(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[117] = bytes[1];
        registerData[118] = bytes[0];
    }

    //Register 58
    public void setBatmon_noc(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[119] = bytes[1];
        registerData[120] = bytes[0];
    }

    //Register 59
    public void setBatmon_noccur(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[121] = bytes[1];
        registerData[122] = bytes[0];
    }

    //Register 60-61
    public void setBatmon_etm(long value) {
        byte[] bytes = Converter.longToByte(value);
        registerData[123] = bytes[3];
        registerData[124] = bytes[2];
        registerData[125] = bytes[1];
        registerData[126] = bytes[0];
    }

    //Register 62-63
    public void setBatmon_dis(long value) {
        byte[] bytes = Converter.longToByte(value);
        registerData[127] = bytes[3];
        registerData[128] = bytes[2];
        registerData[129] = bytes[1];
        registerData[130] = bytes[0];
    }

    //Register 64-65
    public void setBatmon_eoc(long value) {
        byte[] bytes = Converter.longToByte(value);
        registerData[131] = bytes[3];
        registerData[132] = bytes[2];
        registerData[133] = bytes[1];
        registerData[134] = bytes[0];
    }

    //Register 66
    public void setCh_diag_1(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[135] = bytes[1];
        registerData[136] = bytes[0];
    }

    //Register 67
    public void setCh_diag_2(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[137] = bytes[1];
        registerData[138] = bytes[0];
    }

    //Register 68
    public void setCh_diag_3(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[139] = bytes[1];
        registerData[140] = bytes[0];
    }

    //Register 69
    //0-Заряд батарей в норме; 1-Батареи разряжены
    public void setBatmon_state(byte value) {
        registerData[142] = value;
    }

    //todo
    //Register 69 тоже
    public void setAdapter_state(byte value) {
        registerData[142] = value;
    }

    //Register 70
    public void setAdapter_diag(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[143] = bytes[1];
        registerData[144] = bytes[0];
    }

    //Register 71-72
    public void setG_cps_limit(float value) {
        byte[] bytes = Converter.floatToByte(value);
        registerData[145] = bytes[3];
        registerData[146] = bytes[2];
        registerData[147] = bytes[1];
        registerData[148] = bytes[0];
    }

    //Register 73-74
    public void setCh1_momcps(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[149] = bytes[3];
        registerData[150] = bytes[2];
        registerData[151] = bytes[1];
        registerData[152] = bytes[0];
    }

    //Register 75-76
    public void setCh2_momcps(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[153] = bytes[3];
        registerData[154] = bytes[2];
        registerData[155] = bytes[1];
        registerData[156] = bytes[0];
    }

    //Register 77-78
    public void setCh3_momcps(int value) {
        byte[] bytes = Converter.integerToByte(value);
        registerData[157] = bytes[3];
        registerData[158] = bytes[2];
        registerData[159] = bytes[1];
        registerData[160] = bytes[0];
    }

}
