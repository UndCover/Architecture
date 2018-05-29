
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 字符串构造, 类似StringBuilder <br/>
 * 
 * @important 这个类的目的不是为性能, 而是为易用, 尽管内部使用StringBuffer做代理; 对性能要求高时建议使用StringBuffer
 * @author yangentao
 */
public class UtilsStringBuilder {
    private StringBuffer sb;

    private String nullString = "null";

    public void setNullString(String ns) {
        this.nullString = ns;
    }

    public static String build(Object... args) {
        UtilsStringBuilder builder = new UtilsStringBuilder();
        builder.append(args);
        return builder.toString();
    }

    public UtilsStringBuilder() {
        sb = new StringBuffer(32);
    }

    public UtilsStringBuilder(int capcity) {
        sb = new StringBuffer(capcity);
    }

    public int length() {
        return sb.length();
    }

    public void setLength(int len) {
        sb.setLength(len);
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    public UtilsStringBuilder appendln() {
        return append("\n");
    }

    public UtilsStringBuilder appendln(Object... args) {
        return append(args).appendln();
    }

    public UtilsStringBuilder append(Object... args) {
        if (args.length > 0) {
            sb.ensureCapacity(sb.length() + args.length * 8 + 8);
            for (Object obj : args) {
                sb.append(getString(obj));
            }
        }
        return this;
    }

    private String getString(Object obj) {
        if (obj == null) {
            return nullString == null ? "" : nullString;
        }
        // else
        if (obj instanceof Throwable) {
            Throwable tr = (Throwable) obj;
            StringWriter sw = new StringWriter(128);
            PrintWriter pw = new PrintWriter(sw);
            tr.printStackTrace(pw);
            return sw.toString();
        }
        // else
        if (obj instanceof String) {
            return (String) obj;
        }
        // else
        return obj.toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
