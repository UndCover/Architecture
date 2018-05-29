/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import java.io.File;

public class UtilsDir {

    public static void deleteDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        File[] flist = dir.listFiles();
        if (flist != null) {
            for (File file : flist) {
                if (file.isFile())
                    file.delete(); // 删除所有文件
                else if (file.isDirectory())
                    deleteDir(file.getAbsolutePath()); // 递规的方式删除文件夹
            }
        }
    }

    public static void deleteDir(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            File[]  flist = directory.listFiles();
            if(flist != null)
            for (File item : flist) {
                if (item.isDirectory()) {
                    deleteDir(item);
                } else if (item.isFile()) {
                    item.delete();
                }
            }
        }
    }
}
