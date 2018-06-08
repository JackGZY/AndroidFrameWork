package com.jack.framework.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;


import com.jack.framework.FrameWorkApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Utils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-7-22
 */
public class StringUtils {

    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * is null or its length is 0 or it is made by space
     * <p>
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return true, else return false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * is null or its length is 0
     * <p>
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * get length of CharSequence
     * <p>
     * <pre>
     * length(null) = 0;
     * length(\"\") = 0;
     * length(\"abc\") = 3;
     * </pre>
     *
     * @param str
     * @return if str is null or empty, return 0, else return {@link CharSequence#length()}.
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * null Object to empty string
     * <p>
     * <pre>
     * nullStrToEmpty(null) = &quot;&quot;;
     * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
     * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
     * </pre>
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * capitalize first letter
     * <p>
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : new StringBuilder(str.length())
                .append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }

    /**
     * encoded in utf-8
     * <p>
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException if an error occurs
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * get innerHtml from href
     * <p>
     * <pre>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
     * </pre>
     *
     * @param href
     * @return <ul>
     * <li>if href is null, return ""</li>
     * <li>if not match regx, return source</li>
     * <li>return the last string that match regx</li>
     * </ul>
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * process special char in html
     * <p>
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * transform half width char to full width char
     * <p>
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * transform full width char to half width char
     * <p>
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 格式化数据为小数点后几位输出
     *
     * @param str 可以转换成数字的str，如果不行，返回原来的值
     * @param n   小数点后多少位
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static String NumberToStringPoint(String str, int n) {
        String result = str;
        double num = 0;
        try {
            num = Double.parseDouble(str);

            String format = "\"%." + n + "f\"";
            result = String.format(format, num);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 设置text的字体颜色不同
     *
     * @param str   需要的文字
     * @param start 开始的位置，0开始，包括
     * @param end   结束的位置，不包括end
     * @param color 颜色，需要是Color.parseColor
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static SpannableString textColorMore(String str, int start, int end, int color) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spannable
                .SPAN_INCLUSIVE_INCLUSIVE);

        return spannableString;
    }


    /**
     * 设置text的字体颜色不同
     *
     * @param spannableString 需要的文字的spannableString
     * @param start           开始的位置，0开始，包括
     * @param end             结束的位置，不包括end
     * @param color           颜色，需要是Color.parseColor
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static SpannableString textColorMore(SpannableString spannableString, int start, int
            end, int color) {
        spannableString.setSpan(new ForegroundColorSpan(color), start, end, Spannable
                .SPAN_INCLUSIVE_INCLUSIVE);

        return spannableString;
    }


    /**
     * 设置text的字体大小不同
     *
     * @param spannableString 需要的文字的spannableString
     * @param start           开始的位置，0开始，包括
     * @param end             结束的位置，不包括end
     * @param size            字体大小，单位是sp
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static SpannableString textSizeMore(SpannableString spannableString, int start, int
            end, int size) {
        spannableString.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(FrameWorkApplication
                        .getApplication(), size)), start, end,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


    /**
     * 设置text的字体大小不同
     *
     * @param str   需要的文字的
     * @param start 开始的位置，0开始，包括
     * @param end   结束的位置，不包括end
     * @param size  字体大小，单位是sp
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static SpannableString textSizeMore(String str, int start, int end, int size) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(FrameWorkApplication.getApplication
                        (), size)), start, end,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    /**
     * 设置text的字体下划线
     *
     * @param str   需要的文字的
     * @param start 开始的位置，0开始，包括
     * @param end   结束的位置，不包括end
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static SpannableString textUnderLine(String str, int start, int end) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new UnderlineSpan(), start, end,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


    /**
     * 设置text的字体下划线
     *
     * @param spannableString 需要的文字的spannableString
     * @param start           开始的位置，0开始，包括
     * @param end             结束的位置，不包括end
     * @Author: JACK-GU
     * @E-Mail: 528489389@qq.com
     */
    public static SpannableString textUnderLine(SpannableString spannableString, int start, int
            end) {
        spannableString.setSpan(new UnderlineSpan(), start, end,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    /**
     * 判断是不是合法数字, 小数点后两位数字
     *
     * @param str 数字
     * @return true 或 false
     */
    public static boolean isNumber(String str) {
        if (str == null || str.length() == 0)
            return true;
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isPlate(String str) {
        if (str == null || str.length() == 0)
            return true;
        // 普通民用车
        Pattern pattern1 = Pattern.compile("^[京,津,渝,沪,冀,晋,辽,吉,黑,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,琼,川,贵,云,陕,秦,甘,陇,青,台,内蒙古,桂,宁,新,藏][A-Z][0-9,A-Z]{5}$");
        Matcher match1 = pattern1.matcher(str);
        // 匹配特种车牌(挂,警,学,领,港,澳)
        Pattern pattern2 = Pattern.compile("^[京,津,渝,沪,冀,晋,辽,吉,黑,苏,浙,皖,闽,赣,鲁,豫,鄂,湘,粤,琼,川,贵,云,陕,秦,甘,陇,青,台,内蒙古,桂,宁,新,藏][A-Z][0-9,A-Z]{4}[挂,警,学,领,港,澳]{1}$");
        Matcher match2 = pattern2.matcher(str);
        return match1.matches() || match2.matches();
    }

}
