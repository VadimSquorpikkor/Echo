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
}
