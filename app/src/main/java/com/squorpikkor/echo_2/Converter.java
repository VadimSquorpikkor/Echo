package com.squorpikkor.echo_2;

import java.nio.ByteBuffer;

class Converter {

//--------------------------------------------------------------------------------------------------

    public static byte[] integerToByte(int a) {
        byte[] ret = new byte[4];
        ret[0] = (byte) (a & 0xFF);
        ret[1] = (byte) ((a >> 8) & 0xFF);
        ret[2] = (byte) ((a >> 16) & 0xFF);
        ret[3] = (byte) ((a >> 24) & 0xFF);
        return ret;
    }

    public static byte[] shortToByte(short v) {
        byte[] writeBuffer = new byte[2];
        writeBuffer[0] = (byte) ((v) & 0xFF);
        writeBuffer[1] = (byte) ((v >>> 8) & 0xFF);
        return writeBuffer;
    }

    public static byte[] floatToByte(float v) {
        return integerToByte(Float.floatToRawIntBits(v));
    }

    public static byte[] longToByte(long v) {
        byte[] writeBuffer = new byte[8];
        writeBuffer[7] = (byte) ((v >>> 56) & 0xFF);
        writeBuffer[6] = (byte) ((v >>> 48) & 0xFF);
        writeBuffer[5] = (byte) ((v >>> 40) & 0xFF);
        writeBuffer[4] = (byte) ((v >>> 32) & 0xFF);
        writeBuffer[3] = (byte) ((v >>> 24) & 0xFF);
        writeBuffer[2] = (byte) ((v >>> 16) & 0xFF);
        writeBuffer[1] = (byte) ((v >>> 8) & 0xFF);
        writeBuffer[0] = (byte) ((v) & 0xFF);
        return writeBuffer;
    }

//--------------------------------------------------------------------------------------------------

    public static float toFloat(byte[] data, int offset) {
        return Float.intBitsToFloat(toInt32(data, offset));
    }

    public static short toInt16(byte[] data, int offset) {
        return (short) (data[offset] & 0xFF | (data[offset + 1] & 0xFF) << 8);
    }

    public static int toInt32(byte[] data, int offset) {
        return (data[offset] & 0xFF) | ((data[offset + 1] & 0xFF) << 8)
                | ((data[offset + 2] & 0xFF) << 16)
                | ((data[offset + 3] & 0xFF) << 24);
    }

    public static long toInt64(byte[] data, int offset) {
        return (((long) (data[offset + 7] & 0xff) << 56)
                | ((long) (data[offset + 6] & 0xff) << 48)
                | ((long) (data[offset + 5] & 0xff) << 40)
                | ((long) (data[offset + 4] & 0xff) << 32)
                | ((long) (data[offset + 3] & 0xff) << 24)
                | ((long) (data[offset + 2] & 0xff) << 16)
                | ((long) (data[offset + 1] & 0xff) << 8) | (data[offset] & 0xff));
    }

//--------------------------------------------------------------------------------------------------

    public static int calcCRC(byte[] dataBuffer) {
        int sum = 0xffff;
        for (byte aDataBuffer : dataBuffer) {
            sum = (sum ^ (aDataBuffer & 255));
            for (int j = 0; j < 8; j++) {
                if ((sum & 0x1) == 1) {
                    sum >>>= 1;
                    sum = (sum ^ 0xA001);
                } else {
                    sum >>>= 1;
                }
            }
        }
        return sum;
    }


}
