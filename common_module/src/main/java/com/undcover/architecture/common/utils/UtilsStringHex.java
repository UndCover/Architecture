
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

public class UtilsStringHex {

    private static final char[] hexArray = {
            48, 49, 50, 51, 52, 53, 54, 55,
            56, 57, 97, 98, 99, 100, 101, 102
    };

    public static String bytesToHex(byte[] bytesArray) {
        char[] ret = new char[bytesArray.length * 2];
        int retIndex;
        for (int i = 0; i < bytesArray.length; i++) {
            retIndex = i * 2;
            ret[retIndex] = hexArray[(bytesArray[i] & 0xFF) >>> 4];
            ret[retIndex + 1] = hexArray[bytesArray[i] & 0x0F];
        }
        return new String(ret);
    }

    private static String bytesToHex(byte[] bytesArray, int offset, int length) {
        char[] ret = new char[length * 2];
        int retIndex;
        for (int i = 0; i < length; i++) {
            retIndex = i * 2;
            ret[retIndex] = hexArray[(bytesArray[offset + i] & 0xFF) >>> 4];
            ret[retIndex + 1] = hexArray[bytesArray[offset + i] & 0x0F];
        }
        return new String(ret);
    }

    public static String getUUIDByBytes(byte[] bytesUUID) {
        String szProfileUUID = bytesToHex(bytesUUID);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(szProfileUUID.substring(0, 8));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(8, 12));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(12, 16));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(16, 20));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(20, 32));
        return stringBuilder.toString();
    }

    public static String getUUIDByBytes(byte[] bytesUUID, int offset, int length) {
        String szProfileUUID = bytesToHex(bytesUUID, offset, length);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(szProfileUUID.substring(0, 8));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(8, 12));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(12, 16));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(16, 20));
        stringBuilder.append("-");
        stringBuilder.append(szProfileUUID.substring(20, 32));
        return stringBuilder.toString();
    }
}
