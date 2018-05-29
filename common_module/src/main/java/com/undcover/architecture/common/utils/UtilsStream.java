
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class UtilsStream {
    private static final String TAG = UtilsStream.class.getSimpleName();

    /**
     * 会关闭流
     */
    public static boolean saveStreamToFile(InputStream is, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos = null;
            return true;
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
        } finally {
            UtilsClose.close(is);
            UtilsClose.close(fos);
        }
        return false;
    }

    /**
     * 会关闭流
     */
    public static byte[] saveStreamToBytes(InputStream is) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            return os.toByteArray();
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
        } finally {
            UtilsClose.close(is);
        }
        return null;
    }

    /**
     * @param is
     * @param encoding
     * @return
     */
    public static String saveStreamToString(InputStream is, String encoding) {
        try {
            byte[] data = saveStreamToBytes(is);
            if (data != null) {
                return new String(data, encoding);
            }
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
        }
        return null;
    }

    /**
     * 会关闭输入输出流
     */
    public static boolean copy(InputStream from, OutputStream to) {
        try {
            byte[] buffer = new byte[4096];
            int n = -1;
            while ((n = from.read(buffer)) != -1) {
                to.write(buffer, 0, n);
            }
            return true;
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        } finally {
            UtilsClose.close(from);
            UtilsClose.close(to);
        }
        return false;
    }

}
