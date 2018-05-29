
/*
 * Create by UndCover on 12/4/17 4:02 PM.
 * Copyright (c) 2017.
 */

package com.undcover.architecture.common.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class UtilsObject {
    private static final String TAG = UtilsObject.class.getSimpleName();

    /**
     * 字符串/数字/布尔类型返回true, 其他类型返回false
     *
     * @param cls
     * @return
     */
    public static boolean isBasicType(Class<?> cls) {
        if (cls.isPrimitive()) {
            return true;
        }
        if (cls == String.class) {
            return true;
        }
        if (cls == Boolean.class) {
            return true;
        }
        if (Number.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    /**
     * 深拷贝, 需要被序列化的对象(和内部成员, 递归)实现Serializable接口
     *
     * @param obj
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(128);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(obj);

            ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(is);
            return (T) ois.readObject();
        } catch (Exception e) {
            SmartLog.e(TAG, e.getMessage());
            throw new IllegalArgumentException(
                    "Serialize failed, Do you implements Serializable interface?");
        }
    }

    public static boolean equal(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null) {
            return false;
        }
        return o1.equals(o2);
    }
}
