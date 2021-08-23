package com.okhttp.demo.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @decription 该类建议只被ReflectionUtility调用, 将ReflectionUtility作为唯一反射入口
 */
final class ReflectionCache {
    private static final String PREFIX = "ReflectionCache";

    private HashMap<String, Class> mClassMap;
    private HashMap<String, Method> mMethodMap;
    private HashMap<String, Field> mFieldMap;

    private static class ReflectionCacheHolder {
        private static ReflectionCache HOLDER = new ReflectionCache();
    }

    private ReflectionCache() {
        mClassMap = new HashMap<>();
        mMethodMap = new HashMap<>();
        mFieldMap = new HashMap<>();
    }

    static ReflectionCache getInstance() {
        return ReflectionCacheHolder.HOLDER;
    }


    Class<?> forName(String className) throws ClassNotFoundException {
        String classKey = PREFIX + "." + className;
        Class<?> objClass = mClassMap.get(classKey);

        if (objClass == null) {
            synchronized (classKey) {
                objClass = mClassMap.get(classKey);
                if (objClass == null) {
                    if (!TextUtils.isEmpty(className)) {
                        objClass = Class.forName(className);
                        mClassMap.put(classKey, objClass);
                    }
                }
            }
        }
        return objClass;
    }


    Method getMethod(String className, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> classObj = forName(className);
        return getMethod(classObj, methodName, parameterTypes);
    }

    Method getMethod(Class<?> objClassObj, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        String methodKey = getKey(objClassObj, methodName, parameterTypes);
        Method method = mMethodMap.get(methodKey);
        if (method == null) {
            synchronized (methodKey) {
                method = mMethodMap.get(methodKey);
                if (method == null) {
                    method = objClassObj.getMethod(methodName, parameterTypes);
                    mMethodMap.put(methodKey, method);
                }
            }
        }
        return method;
    }

    Method getDeclaredMethod(Class<?> objClassObj, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        String methodKey = getKey(objClassObj, methodName, parameterTypes);
        Method method = mMethodMap.get(methodKey);
        if (method == null) {
            synchronized (methodKey) {
                method = mMethodMap.get(methodKey);
                if (method == null) {
                    method = objClassObj.getDeclaredMethod(methodName, parameterTypes);
                    mMethodMap.put(methodKey, method);
                }
            }
        }
        return method;
    }


    Field getField(String className, String fieldName) throws NoSuchFieldException, ClassNotFoundException {
        Class<?> classObj = forName(className);
        return getField(classObj, fieldName);
    }

    Field getField(Class<?> objClass, String fieldName) throws NoSuchFieldException {
        String fieldKey = getKey(objClass, fieldName);
        Field fieldObj = mFieldMap.get(fieldKey);
        if (fieldObj == null) {
            synchronized (fieldKey) {
                fieldObj = mFieldMap.get(fieldKey);
                if (fieldObj == null) {
                    fieldObj = objClass.getField(fieldName);
                    mFieldMap.put(fieldKey, fieldObj);
                }
            }

        }
        return fieldObj;
    }

    Field getDeclaredField(String className, String fieldName) throws NoSuchFieldException, ClassNotFoundException {
        Class<?> classObj = forName(className);
        return getDeclaredField(classObj, className);
    }

    Field getDeclaredField(Class<?> objClass, String fieldName) throws NoSuchFieldException {
        String fieldKey = getKey(objClass, fieldName);
        Field fieldObj = mFieldMap.get(fieldKey);
        if (fieldObj == null) {
            synchronized (fieldKey) {
                fieldObj = mFieldMap.get(fieldKey);
                if (fieldObj == null) {
                    fieldObj = objClass.getDeclaredField(fieldName);
                    mFieldMap.put(fieldKey, fieldObj);
                }
            }
        }
        return fieldObj;
    }

    private String getKey(Class<?> objClass, String name, Class<?>... parameterTypes) {
        StringBuilder builder = new StringBuilder(PREFIX);
        builder.append(".")
                .append(objClass.getName())
                .append(".").append(name);
        if (parameterTypes != null) {
            for (Class<?> parameterClass : parameterTypes) {
                builder.append(".")
                        .append(parameterClass.getName());
            }
        }
        return builder.toString();
    }


    void clearCache() {
        mClassMap.clear();
        mMethodMap.clear();
        mFieldMap.clear();
    }

}
