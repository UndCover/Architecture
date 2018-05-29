
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.database.Cursor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class UtilsClose {
    private static final String TAG = "UtilsClose";

    public static void close(Cursor c) {
        if (c != null) {
            c.close();
        }
    }

    public static void close(InputStream is) {
        try {
            if (is != null) {
                is.close();
                is = null;
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.toString());
        }
    }

    public static void close(OutputStream os) {
        try {
            if (os != null) {
                os.close();
                os = null;
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.toString());
        }
    }

    public static void close(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.toString());
        }
    }

    public static void closeWriter(OutputStreamWriter writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        }
    }

    public static void close(BufferedWriter writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.toString());
        }
    }

    public static void close(FileWriter fWriter) {
        try {
            if (fWriter != null) {
                fWriter.close();
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.toString());
        }
    }
}
