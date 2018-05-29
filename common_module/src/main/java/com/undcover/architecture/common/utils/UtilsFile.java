
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.content.Context;
import android.os.Environment;

import com.undcover.architecture.common.SmartSdkManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class UtilsFile {
    private static final String TAG = UtilsFile.class.getSimpleName();
    public static final int KB = 1024;
    public static final int MB = 1024 * KB;
    public static final int GB = 1024 * MB;

    public static int getKB(int size) {
        return size / KB;
    }

    public static int getMB(int size) {
        return size / MB;
    }

    public static int getGB(int size) {
        return size / GB;
    }

    public static String readFileUTF8(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        try {
            int len = fis.available();
            if (len < 0 || len > 1024 * 1024 * 10) {// >10M
                throw new IllegalAccessError("file is too large :" + file.getAbsolutePath());
            }
            byte[] buf = new byte[len < 16 ? 16 : len];
            int readed = fis.read(buf);
            return new String(buf, 0, readed, "UTF-8");
        } finally {
            UtilsClose.close(fis);
        }
    }

    /**
     * The latest src will be written to the end of this file.
     *
     * @param src
     * @param path
     * @param filename
     * @param append
     */
    public static void writeToSDCardAsc(String src, String path, String filename, boolean append) {
        OutputStreamWriter osw = null;
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                File folder = new File(path);
                File file = new File(path + File.separator + filename);

                if (!folder.exists()) {
                    if (false == folder.mkdirs()) {
                        // LOG.E(TAG,
                        // "create file in some second sd card error");
                        return;
                    }
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                osw = new OutputStreamWriter(new FileOutputStream(file, append));
                osw.write(src);
                osw.flush();
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        } finally {
            UtilsClose.closeWriter(osw);
        }
    }

    /**
     * The latest src will be written to the beginning of this file.
     *
     * @param src
     * @param path
     * @param filename
     * @param append
     * @deprecated
     */
    public static void writeToSDCardDsc(String src, String path, String filename, boolean append) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                File folder = new File(path);
                File file = new File(path + File.separator + filename);

                if (!folder.exists()) {
                    folder.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file, append));
                osw.write(src);
                osw.close();
            }
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        }
    }

    public static void cutFile(String srcFileAbsolutePath, String targetDir, String newName) {
        File srcFile = new File(srcFileAbsolutePath);
        File destFile = new File(targetDir + File.separator + newName);
        srcFile.renameTo(destFile);
    }

    public static int copyFile(String srcFileAbsolutePath, String targetDir, String newName,
                               boolean append) throws IOException {
        File folder = new File(targetDir);
        File file = new File(targetDir + File.separator + newName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        FileInputStream input = null;
        FileOutputStream out = null;
        int size = 0;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            input = new FileInputStream(srcFileAbsolutePath);
            out = new FileOutputStream(file, append);
            byte[] buffer = new byte[1024];
            int l;
            while ((l = input.read(buffer)) != -1) {
                out.write(buffer, 0, l);
                size += l;
            }
            out.flush();
        } finally {
            UtilsClose.close(out);
            UtilsClose.close(input);
        }
        return size;
    }

    public static void writeToLoCal(InputStream input, String destDir, String filename,
                                    boolean append) throws IOException {
        File folder = new File(destDir);
        File file = new File(destDir + File.separator + filename);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        FileOutputStream out = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file, append);
            byte[] buffer = new byte[1024];
            int l;
            while ((l = input.read(buffer)) != -1) {
                out.write(buffer, 0, l);
            }
        } finally {
            UtilsClose.close(out);
            UtilsClose.close(input);
        }
    }

    public static boolean checkFileExist(String absolutePath) {
        File file = new File(absolutePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static boolean copy(File from, File to) {
        try {
            if (!to.exists()) {
                to.createNewFile();
            }
            FileInputStream fin = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to);
            return UtilsStream.copy(fin, fos);
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        }
        return false;
    }

    public static String getString(String fileName,String format){
       return getString(SmartSdkManager.getApplication(),fileName,format);
    }

    public static String getString(Context context, String fileName, String format) {
        String Result = "";
        try {
            InputStream stream = null;
            if (fileName.contains("assets/")) {
                stream = context.getAssets().open(fileName.substring(8));
            } else {
                stream = new FileInputStream(fileName);
            }
            InputStreamReader inputReader = new InputStreamReader(stream, format);
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
        }
        return Result;
    }

    public static String getFileName(String uri) {
        if (uri == null) {
            return "";
        }

        String filename = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
        return filename;
    }

    public static boolean isExist(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            SmartLog.e(e.getMessage());
            return false;
        }
        return true;
    }

    public static void delFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

}
