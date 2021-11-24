package com.squorpikkor.echo_2;

class Constant {

    /**Массив байт данных с CRC. Не используется*/
    public static final byte[] DEF_DATA_REGISTERS = {1, 4, -98, 0, 5, 25, -48, 0, 0, 1, 101, 67, -94, 48, -39, 64, -107, 92, -5, 51, -57, 39, -44, 65, 24, -80, 72, 47, -96, -88, -118, 0, 0, 0, 0, 0, 92, 0, 93, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 19, 3, 19, 0, 0, 0, 0, 15, 15, 0, 24, 2, -105, -1, -9, 0, -87, 0, -87, 0, 12, 0, 8, 0, -73, 0, -73, 0, 48, 42, -51, 0, 48, 12, -26, 0, 48, 42, -61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 71, -22, 96, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 101, -78, -72};
    /**Массив байт данных без CRC*/
    public static final byte[] DEF_DATA_REGISTERS_2 = {1, 4, -98, 0, 5, 25, -48, 0, 0, 1, 101, 67, -94, 48, -39, 64, -107, 92, -5, 51, -57, 39, -44, 65, 24, -80, 72, 47, -96, -88, -118, 0, 0, 0, 0, 0, 92, 0, 93, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 19, 3, 19, 0, 0, 0, 0, 15, 15, 0, 24, 2, -105, -1, -9, 0, -87, 0, -87, 0, 12, 0, 8, 0, -73, 0, -73, 0, 48, 42, -51, 0, 48, 12, -26, 0, 48, 42, -61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 71, -22, 96, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 101};

    /**
     * To consider the status of the binary signals
     */
    public static final byte READ_STATUS_BINARY_SIGNAL = 0x02;

    /**
     * Consider the state of the control registers
     */
    public static final byte READ_STATE_CONTROL_REGISTERS = 0x03;

    /**
     * Read state of data registers
     */
    public static final byte READ_STATE_DATA_REGISTERS = 0x04;

    /**
     * Send control signal
     */
    public static final byte SEND_CONTROL_SIGNAL = 0x05;

    /**
     * Change the state of the control register
     */
    public static final byte CHANGE_STATE_CONTROL_REGISTER = 0x06;

    /**
     * Read status word
     */
    public static final byte READ_STATUS_WORD = 0x07;

    /**
     * Diagnostics
     */
    public static final byte DIAGNOSTICS = 0x08;

    /**
     * Consider a sample spectrum from the non-volatile memory
     */
    public static final byte READ_SPECTRUM_NON_VOLATILE_MEMORY = 0x09;

    /**
     * To consider the accumulated sample spectrum
     */
    public static final byte READ_SPECTRUM_ACCUMULATED_SAMPLE = 0x0B;

    /**
     * Change the state of control registers
     */
    public static final byte CHANGE_STATE_CONTROL_REGISTERS = 0x10;

    /**
     * Read device identification code
     */
    public static final byte READ_DEVICE_ID = 0x11;

    /**
     * Read calibration data sample
     */
    public static final byte READ_CALIBRATION_DATA_SAMPLE = 0x12;

    /**
     * Record calibration data sample
     */
    public static final byte WRITE_CALIBRATION_DATA_SAMPLE = 0x13;

    /**
     * Read the accumulated spectrum
     */
    public static final byte READ_ACCUMULATED_SPECTRUM = 0x40;

    /**
     * Read the accumulated spectrum in compressed form with the restart of set
     */
    public static final byte READ_ACCUMULATED_SPECTRUM_COMPRESSED_REBOOT = 0x41;

    /**
     * Read the accumulated spectrum in compressed form
     */
    public static final byte READ_ACCUMULATED_SPECTRUM_COMPRESSED = 0x42;





    //Device ID

    /**
     * ID_BTDU3 device ID
     */
    public static final short ID_BTDU3 = 0x30;

    //Detection unit ID

    /**
     * BDKG04 device ID
     */
    public static final short ID_BDKG04 = 0x25;

    /**
     * ID_BDKN05 device ID
     */
    public static final short ID_BDKN05 = 0x2a;

    /**
     * ID_BDKG11M device ID
     */
    public static final short ID_BDKG11M = 0x13;

    /**
     * ID_BDKG11 device ID
     */
    public static final short ID_BDKG11 = 0x0b;

    /**
     * ID_BDKG19M device ID
     */
    public static final short ID_BDKG19M = 0x18;

    /**
     * ID_BDKG28 device ID
     */
    public static final short ID_BDKG28 = 0x31;

    /**
     * ID_BDKG34 device ID
     */
    public static final short ID_BDKG34 = 0x4e;

    /**
     * ID_BDKG35 device ID
     */
    public static final short ID_BDKG35 = 0x1f;

    /**
     * ID_BDKG03 device ID
     */
    public static final short ID_BDKG03 = 0x07;

    /**
     * ID_STR_I device ID (SrI2 38x38)
     */
    public static final short ID_BDKG05C = 0x6a;


    public static final short ID_BDKG32 = 0x48;//было неправильно 0x30
}
