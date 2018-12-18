package com.sxl.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author shuxiaolong
 * @time 2018/12/18 15:53
 * @description TODO com.taimeitech.framework.util
 */
public class CollectionConverter {
    public static <T> List<T> listToLists(List<?> list, Class<T> clazz) {
        if (list == null) {
            return null;
        } else {
            ArrayList result = new ArrayList();

            try {
                Iterator var3 = list.iterator();

                while(var3.hasNext()) {
                    Object source = var3.next();
                    Object target = null;
                    target = clazz.newInstance();
                    BeanUtils.copyProperties(source, target);
                    result.add(target);
                }
            } catch (IllegalAccessException | InstantiationException var6) {
                logger.error(var6.getMessage(), var6);
            }

            return result;
        }
    }

    public static String buildCombineKey(String[] values) {
        StringBuilder combineKey = new StringBuilder();
        String[] var2 = values;
        int var3 = values.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String value = var2[var4];
            combineKey.append(value == null ? "" : value).append("_");
        }

        return combineKey.toString();
    }

    public static <T> String buildCombineKey(T t, String[] keys) {
        StringBuilder combineKey = new StringBuilder();
        String[] var3 = keys;
        int var4 = keys.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String key = var3[var5];
            Object o = null;

            try {
                o = PropertyUtils.getProperty(t, key);
                combineKey.append(o == null ? "" : o.toString()).append("_");
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var9) {
                logger.error(var9.getMessage(), var9);
            }
        }

        return combineKey.toString();
    }

    public static <T> Map<String, T> listToMap(List<T> list, String[] keys) {
        Map<String, T> m = new HashMap();
        if (list != null) {
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                T t = var3.next();
                String combineKey = buildCombineKey(t, keys);
                m.put(combineKey, t);
            }
        }

        return m;
    }

    public static <T> Map<String, T> listToMap(List<T> list, String key) {
        Map<String, T> m = new HashMap();
        Object t;
        Object o;
        if (list != null) {
            for(Iterator var3 = list.iterator(); var3.hasNext(); m.put((String)o, t)) {
                t = var3.next();
                o = null;

                try {
                    o = PropertyUtils.getProperty(t, key);
                } catch (Exception var7) {
                    logger.error(var7.getMessage(), var7);
                }
            }
        }

        return m;
    }

    public static <T> Map<String, List<T>> groupListToMap(List<T> list, String key) {
        Map<String, List<T>> m = new HashMap();
        Object t;
        String o;
        if (list != null) {
            for(Iterator var3 = list.iterator(); var3.hasNext(); ((List)m.computeIfAbsent(o, (k) -> {
                return new ArrayList();
            })).add(t)) {
                t = var3.next();
                o = null;

                try {
                    o = (String)PropertyUtils.getProperty(t, key);
                } catch (Exception var7) {
                    logger.error(var7.getMessage(), var7);
                }
            }
        }

        return m;
    }

    public static <T, K> Map<String, List<K>> groupList2SpecificMap(List<T> list, String key, String valueKey) {
        Map<String, List<K>> m = new HashMap();
        Object o;
        Object value;
        if (list != null) {
            for(Iterator var4 = list.iterator(); var4.hasNext(); ((List)m.get(o)).add(value)) {
                T t = var4.next();
                o = null;
                value = null;

                try {
                    o = PropertyUtils.getProperty(t, key);
                    value = PropertyUtils.getProperty(t, valueKey);
                } catch (Exception var9) {
                    logger.error(var9.getMessage(), var9);
                }

                if (m.get(o) == null) {
                    m.put((String)o, new ArrayList());
                }
            }
        }

        return m;
    }

    public static <T> Set<String> list2Set(List<T> list, String key) {
        Set<String> m = new HashSet();
        if (list != null) {
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                T t = var3.next();
                Object o = null;

                try {
                    o = PropertyUtils.getProperty(t, key);
                } catch (Exception var7) {
                    logger.error(var7.getMessage(), var7);
                }

                if (o != null) {
                    m.add((String)o);
                }
            }
        }

        return m;
    }
}
