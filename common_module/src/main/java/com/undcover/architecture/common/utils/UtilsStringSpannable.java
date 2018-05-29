
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;

public class UtilsStringSpannable {

    /**
     * change font color of string
     *
     * @param str
     * @param color
     * @param start
     * @param end
     * @return
     */
    public static SpannableStringBuilder setStrCorlor(String str, int color, int start, int end) {
        if (!TextUtils.isEmpty(str)) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new ForegroundColorSpan(color), start, end,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        }
        return null;
    }

    /**
     * change background of string
     *
     * @param str
     * @return
     */
    public static SpannableStringBuilder setStringBg(String str, int color) {
        if (!TextUtils.isEmpty(str)) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            int end = str.length();
            style.setSpan(new BackgroundColorSpan(color), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return style;
        }
        return null;
    }

    /**
     * change font size of string
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static SpannableStringBuilder setStrSize(String str, int size, int start, int end) {
        if (!TextUtils.isEmpty(str)) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new AbsoluteSizeSpan(size, true), start, end,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            return style;
        }
        return null;
    }

    /**
     * add under line for string
     *
     * @param str
     * @return
     */
    public static SpannableString addUnderLine(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new UnderlineSpan(), 0, str.length(), 0);
        return spannableString;
    }

    /**
     * set font color and bg color
     * @param str
     * @param bgColor
     * @param fontColor
     * @param start
     * @param end
     * @return
     */
    public static SpannableStringBuilder setFontAndBgColor(String str, int bgColor, int fontColor, int start, int end) {
        if (!TextUtils.isEmpty(str)) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new BackgroundColorSpan(bgColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(fontColor), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        }
        return null;
    }
}
