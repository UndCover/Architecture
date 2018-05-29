/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.undcover.architecture.common.SmartSdkManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Provides utility methods for communicating with the server.
 */
public class UtilsNetwork {
    private static final String TAG = UtilsNetwork.class.getSimpleName();

    public static boolean isNetworkConnected() {
        return isNetworkConnected(SmartSdkManager.getApplication());
    }

    /**
     * Check all the network
     *
     * @param context
     * @return
     */

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network != null) {
            return network.isAvailable();
        }
        return false;
    }

    /**
     * check wifi network
     *
     * @param context
     * @return
     */
    public static boolean isWIFIConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (network != null) {
            return network.isAvailable();
        }
        return false;
    }

    /**
     * check mobile network
     *
     * @param context
     * @return
     */
    public static boolean isMOBILEConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (network != null) {
            return network.isAvailable();
        }
        return false;
    }

    /**
     * 用来获取手机拨号上网（包括CTWAP和CTNET）时由PDSN分配给手机终端的源IP地址。
     *
     * @return
     * @author SHANHY
     */
    public static String getPsdnIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en
                    .hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
                        .hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        }
        return "";
    }

    public static boolean is3GNetwork(int type) {
        boolean is3G = false;
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                is3G = true;
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                is3G = true;
                break;
        }
        return is3G;
    }

    public static final boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null == info) {
            return false;
        }
        int type = info.getType();
        if (ConnectivityManager.TYPE_WIFI == type) {
            return true;
        }
        return false;
    }

    public static final boolean isMobileConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null == info) {
            return false;
        }
        int type = info.getType();
        if (ConnectivityManager.TYPE_MOBILE == type) {
            return true;
        }
        return false;
    }

    public static final boolean is3GConnected(Context context) {
        if (isMobileConnected(context)) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            int nt = tm.getNetworkType();
            if (is3GNetwork(nt)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean is2GConnected(Context context) {
        if (isMobileConnected(context)) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            int nt = tm.getNetworkType();
            if (false == is3GNetwork(nt)) {
                return true;
            }
        }
        return false;
    }
}
