package link.yangxin.my.generate.utils;

import cn.hutool.core.util.CharUtil;

/**
 * @author yangxin
 * @date 2021/12/9
 */
public class StrUtil {

    private static final char UNDERLINE = '_';

    public static String toCamelCase(String str) {
        if (null == str) {
            return null;
        }
        final String name2 = str;
        if (name2.contains(UNDERLINE + "")) {
            final int length = name2.length();
            final StringBuilder sb = new StringBuilder(length);
            boolean upperCase = false;
            for (int i = 0; i < length; i++) {
                char c = name2.charAt(i);

                if (c == UNDERLINE) {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            return sb.toString();
        } else {
            return name2;
        }
    }

}
