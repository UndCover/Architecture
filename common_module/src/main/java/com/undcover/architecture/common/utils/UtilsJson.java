
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class UtilsJson {
    private static final String TAG = UtilsJson.class.getSimpleName();

    /**
     * b是否为a的子集
     */
    public static boolean isAContainB(String a, String b) {
        if (TextUtils.equals(a, b)) {
            return true;
        }
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        if (TextUtils.isEmpty(b)) {
            return true;
        }

        try {
            JSONObject aj = new JSONObject(a);
            JSONObject bj = new JSONObject(b);
            Iterator<?> keys = bj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (!aj.has(key)) {
                    return false;
                }
                Object avalue = aj.get(key);
                Object bvalue = bj.get(key);
                if (avalue == null) {
                    if (bvalue == null) {
                        continue;
                    } else {
                        return false;
                    }
                } else {
                    if (bvalue == null) {
                        return false;
                    }
                    if (!avalue.equals(bvalue)) {
                        return false;
                    }
                }
            }
        } catch (JSONException e) {
            SmartLog.e(TAG, e.getMessage());
        }
        return true;
    }

    public static boolean putIfnotNull(JSONObject js, String key, Object value) throws JSONException {
        boolean put = false;
        if (null != value) {
            js.put(key, value);
            put = true;
        }
        return put;
    }

    public static String jsonFormat(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
//            levelStr.append("\t\t");
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}
