package com.icetea.manager.pagodiario.utils;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static final String URL_PREFIX_HTTP = "http://";

    private static String hostName;

    public static <T> T nv(T value) {
        return value == null ? null : value;
    }

    public static <T> T nv(T value, T nullValue) {
        return value == null ? nullValue : value;
    }

    public static synchronized String getHostName() {
        if (hostName == null) {
            try {
                LOGGER.debug("determining Host Name");
                hostName = InetAddress.getLocalHost().getHostName();
            } catch (final UnknownHostException e) {
                LOGGER.error("determining host failed", e);
                hostName = null;
            }
        }
        return hostName;
    }

    public static String convertToJson(String a) {
        StringBuilder res = new StringBuilder("{\"");

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '=') {
                res = res.append("\"" + ":" + "\"");
            } else if (a.charAt(i) == '&') {
                res = res.append("\"" + StringUtils.COMMA + "\"");
            } else {
                res = res.append(a.charAt(i));
            }
        }
        res.append("\"" + "}");

        return res.toString();
    }

    public static String doUrlClean(String url) {
        String output = url;
        if (StringUtils.startsWith(output, URL_PREFIX_HTTP)) {
            output = StringUtils.replace(output, URL_PREFIX_HTTP, StringUtils.EMPTY);
        }
        return output;
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = Lists.newArrayList();
        if (type == null) {
            return fields;
        }
        return getAllFields(fields, type);
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

}
