package com.okhttp.demo.utils;

import android.content.Context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtility {

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param methodName 方法名
     * @return
     * @para
     */
    public static Object reflectMethod(String className, String methodName) {
        return reflectMethod(className, methodName, null, null);
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param className  Java的类名
     * @param methodName 方法名
     * @param params     方法的参数值
     * @return
     */
    public static Object reflectMethod(String className, String methodName, Class[] paramTypes, Object[] params) {
        Object retObject = null;
        try {

            Class cls = ReflectionCache.getInstance().forName(className);
            Method method = ReflectionCache.getInstance().getMethod(cls, methodName, paramTypes);
            Constructor ct = cls.getConstructor();
            Object obj = ct.newInstance();
            method.setAccessible(true);
            retObject = method.invoke(obj, params);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return retObject;
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param obj        对象
     * @param methodName 方法名
     * @return
     */
    public static Object reflectMethod(Object obj, String methodName) {
        return reflectMethod(obj, methodName, null, null);
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param obj        对象
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @param params     方法的参数值
     * @return
     */
    public static Object reflectMethod(Object obj, String methodName, Class[] paramTypes, Object[] params) {

        Object retObject = null;
        try {

            Class cls = obj.getClass();
            Method method = ReflectionCache.getInstance().getMethod(cls, methodName, paramTypes);
            method.setAccessible(true);
            retObject = method.invoke(obj, params);

        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return retObject;
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法，不捕获异常
     *
     * @param obj        对象
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @param params     方法的参数值
     * @return
     */
    public static Object reflectMethodWithoutCatchException(Object obj, String methodName, Class[] paramTypes, Object[] params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class cls = obj.getClass();
        Method method = ReflectionCache.getInstance().getMethod(cls, methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(obj, params);
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param obj        对象
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @param params     方法的参数值
     * @return
     */
    public static Object reflectDeclaredMethod(Object obj, String methodName, Class[] paramTypes, Object[] params) {

        Object retObject = null;
        try {

            Class cls = obj.getClass();
            Method method = ReflectionCache.getInstance().getDeclaredMethod(cls, methodName, paramTypes);
            method.setAccessible(true);
            retObject = method.invoke(obj, params);

        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return retObject;
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param obj        对象
     * @param methodName 方法名
     * @param className  类名
     * @return
     */
    public static Object reflectMethod(Object obj, String className, String methodName) {
        return reflectMethod(obj, className, methodName, null, null);
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param receiverObj 对象
     * @param className   类名
     * @param methodName  方法名
     * @param paramTypes  参数类型
     * @param params      方法的参数值
     * @return
     */
    public static Object reflectMethod(Object receiverObj, String className, String methodName, Class[] paramTypes, Object[] params) {
        Object retObject = null;
        try {

            Class cls = ReflectionCache.getInstance().forName(className);
            Method method = ReflectionCache.getInstance().getMethod(cls, methodName, paramTypes);
            method.setAccessible(true);
            retObject = method.invoke(receiverObj, params);

        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return retObject;
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param className  Java的类名
     * @param methodName 方法名
     * @return
     */
    public static Object reflectStaticMethod(String className, String methodName, Class[] paramTypes, Object[] params) {

        Object retObject = null;
        try {

            Class cls = ReflectionCache.getInstance().forName(className);
            Method method = ReflectionCache.getInstance().getMethod(cls, methodName, paramTypes);
            method.setAccessible(true);
            retObject = method.invoke(null, params);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return retObject;
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param className  Java的类名
     * @param methodName 方法名
     * @return
     */
    public static Object reflectStaticMethod(String className, String methodName) {
        return reflectStaticMethod(className, methodName, null, null);
    }

    public static Object reflectStaticMethod(Class<?> objClass, String methodName) {

        Object retObject = null;
        try {
            Method method = ReflectionCache.getInstance().getMethod(objClass, methodName);
            method.setAccessible(true);
            retObject = method.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return retObject;
    }


    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param className  Java的类名
     * @param methodName 方法名
     * @return
     */
    public static Object reflectMethod(Context context, String className, String methodName) {
        return reflectMethod(context, className, methodName, null, null);
    }

    /**
     * 在运行时加载指定的类，并调用指定的方法
     *
     * @param className  Java的类名
     * @param methodName 方法名
     * @param params     方法的参数值
     * @return
     */
    public static Object reflectMethod(Context context, String className, String methodName, Class[] paramTypes, Object[] params) {

        Object retObject = null;
        try {

            Class cls = ReflectionCache.getInstance().forName(className);
            Constructor ct = cls.getConstructor(Context.class);
            Object obj = ct.newInstance(context);
            Method method = ReflectionCache.getInstance().getMethod(cls, methodName, paramTypes);
            method.setAccessible(true);
            retObject = method.invoke(obj, params);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (null != e.getTargetException()) {
                e.getTargetException().printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return retObject;
    }


    /**
     * 获取某个类的变量的值
     *
     * @param className 类名
     * @param fieldName 变量名
     * @return objectResult 变量的值
     */
    public static Object reflectField(String className, String fieldName) {

        Object objectResult = null;
        try {

            Class cls = ReflectionCache.getInstance().forName(className);
            Field field = ReflectionCache.getInstance().getField(cls, fieldName);
            field.setAccessible(true);
            Object obj = cls.newInstance();
            objectResult = field.get(obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return objectResult;
    }

    /**
     * 获取某个对象的变量的值
     *
     * @param fieldName 变量名
     * @return objectResult 变量的值
     */
    public static Object reflectDeclaredField(Object obj, String fieldName) {

        Object objectResult = null;
        try {

            Class cls = obj.getClass();
            Field field = ReflectionCache.getInstance().getDeclaredField(cls, fieldName);
            field.setAccessible(true);
            objectResult = field.get(obj);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return objectResult;
    }


    /**
     * 获取某个对象的变量的值
     *
     * @param fieldName 变量名
     * @return objectResult 变量的值
     */
    public static Object reflectField(String className, Object obj, String fieldName) {

        Object objectResult = null;
        try {
            Class cls = ReflectionCache.getInstance().forName(className);
            Field field = ReflectionCache.getInstance().getDeclaredField(cls, fieldName);
            field.setAccessible(true);
            objectResult = field.get(obj);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objectResult;
    }


    /**
     * 获取某个类的变量的值
     *
     * @param className 类名
     * @param fieldName 变量名
     * @return objectResult 变量的值
     */
    public static Object reflectStaticField(String className, String fieldName) {

        Object objectResult = null;
        try {

            Class cls = ReflectionCache.getInstance().forName(className);
            Field field = ReflectionCache.getInstance().getDeclaredField(cls, fieldName);
            field.setAccessible(true);
            objectResult = field.get(cls);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return objectResult;
    }


    public static Class<?> forName(String className) throws ClassNotFoundException {
        return ReflectionCache.getInstance().forName(className);
    }


    public static Method getMethod(String className, String methodName, Class... paramTypes) throws NoSuchMethodException, ClassNotFoundException {
        Method methodObj = ReflectionCache.getInstance().getMethod(className, methodName, paramTypes);
        methodObj.setAccessible(true);
        return methodObj;
    }

    public static Method getMethod(Class<?> classObj, String methodName, Class... paramTypes) throws NoSuchMethodException {
        Method methodObj = ReflectionCache.getInstance().getMethod(classObj, methodName, paramTypes);
        methodObj.setAccessible(true);
        return methodObj;
    }

    public static Field getField(String className, String filedName) throws NoSuchFieldException, ClassNotFoundException {
        Field fieldObj = ReflectionCache.getInstance().getField(className, filedName);
        fieldObj.setAccessible(true);
        return fieldObj;
    }

    public static Field getField(Class<?> classObj, String filedName) throws NoSuchFieldException {
        Field fieldObj = ReflectionCache.getInstance().getField(classObj, filedName);
        fieldObj.setAccessible(true);
        return fieldObj;
    }

    public static Field getDeclaredField(String className, String filedName) throws NoSuchFieldException, ClassNotFoundException {
        Field fieldObj = ReflectionCache.getInstance().getDeclaredField(className, filedName);
        fieldObj.setAccessible(true);
        return fieldObj;
    }

    public static Field getDeclaredField(Class<?> classObj, String filedName) throws NoSuchFieldException {
        Field fieldObj = ReflectionCache.getInstance().getDeclaredField(classObj, filedName);
        fieldObj.setAccessible(true);
        return fieldObj;
    }

    public static boolean isInternational() {
        try {

            Method method = ReflectionCache.getInstance().getMethod("android.os.BuildExt", "isProductInternational");
            return (Boolean) method.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCountryBasedFirmware() {
        try {
            Object object = reflectMethod("android.os.SystemProperties", "get", new Class[]{String.class}, new String[]{"ro.meizu.locale.region"});
            return (String) object;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
