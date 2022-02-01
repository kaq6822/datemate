package com.datemate.common.util;

//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열을 처리하는 유틸 클래스
 * <p>
 * <b>NOTE:</b> Apache Commons regexp 오픈소스를 활용하여 String 관련 기능을 제공하는 유틸이다.
 *
 * 전저정부 프레임워크 EgovStringUtil에서 가져옴
 *
 */
public abstract class StringUtils {

    private static final Log log = LogFactory.getLog(StringUtils.class);

    private final static char WHITE_SPACE = ' ';

    public static boolean isNull(String str) {

        if (str != null) {
            str = str.trim();
        }

        return (str == null || "".equals(str));
    }

    public static boolean isAlpha(String str) {

        if (str == null) {
            return false;
        }

        int size = str.length();

        if (size == 0) return false;

        for (int i = 0; i < size; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAlphaNumeric(String str) {

        if (str == null) {
            return false;
        }

        int size = str.length();

        if (size == 0) return false;

        for (int i = 0; i < size; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String integer2string(int integer) {
        return ("" + integer);
    }

    public static String long2string(long longdata) {
        return String.valueOf(longdata);
    }

    public static String float2string(float floatdata) {
        return String.valueOf(floatdata);
    }

    public static String double2string(double doubledata) {
        return String.valueOf(doubledata);
    }

    public static String null2void(String str) {

        if (isNull(str)) {
            str = "";
        }

        return str;
    }

    public static int string2integer(String str) {

        if (isNull(str)) {
            return 0;
        }

        return Integer.parseInt(str);
    }

    public static float string2float(String str) {

        if (isNull(str)) {
            return 0.0F;
        }

        return Float.parseFloat(str);
    }

    public static double string2double(String str) {

        if (isNull(str)) {
            return 0.0D;
        }

        return Double.parseDouble(str);
    }

    public static long string2long(String str) {

        if (isNull(str)) {
            return 0L;
        }

        return Long.parseLong(str);
    }

    public static String null2string(String str, String defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return str;
    }

    public static int string2integer(String str, int defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Integer.parseInt(str);
    }

    public static float string2float(String str, float defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Float.parseFloat(str);
    }

    public static double string2double(String str, double defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Double.parseDouble(str);
    }

    public static long string2long(String str, long defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        return Long.parseLong(str);
    }

    public static boolean equals(String source, String target) {

        return null2void(source).equals(null2void(target));

    }

    public static String toSubString(String str, int beginIndex, int endIndex) {

        if (equals(str, "")) {
            return str;
        } else if (str.length() < beginIndex) {
            return "";
        } else if (str.length() < endIndex) {
            return str.substring(beginIndex);
        } else {
            return str.substring(beginIndex, endIndex);
        }

    }

    public static String toSubString(String source, int beginIndex) {

        if (equals(source, "")) {
            return source;
        } else if (source.length() < beginIndex) {
            return "";
        } else {
            return source.substring(beginIndex);
        }

    }

    public static String trim(String str) {
        return str.trim();
    }

    public static String ltrim(String str) {

        int index = 0;

        while (' ' == str.charAt(index++))
            ;

        if (index > 0) str = str.substring(index - 1);

        return str;
    }

    public static String rtrim(String str) {

        int index = str.length();

        while (' ' == str.charAt(--index))
            ;

        if (index < str.length()) str = str.substring(0, index + 1);

        return str;
    }

    public static String concat(String str1, String str2) {

        StringBuffer sb = new StringBuffer(str1);
        sb.append(str2);

        return sb.toString();
    }

    public static String lPad(String str, int len, char pad) {
        return lPad(str, len, pad, false);
    }

    public static String lPad(String str, int len, char pad, boolean isTrim) {

        if (isNull(str)) {
            return null;
        }

        if (isTrim) {
            str = str.trim();
        }

        for (int i = str.length(); i < len; i++) {
            str = pad + str;
        }

        return str;
    }

    public static String rPad(String str, int len, char pad) {
        return rPad(str, len, pad, false);
    }

    public static String rPad(String str, int len, char pad, boolean isTrim) {

        if (isNull(str)) {
            return null;
        }

        if (isTrim) {
            str = str.trim();
        }

        StringBuilder sb = new StringBuilder(str);
        for (int i = str.length(); i < len; i++) {
            sb.append(pad);
        }

        return sb.toString();
    }

    public static String alignLeft(String str, int length) {
        return alignLeft(str, length, false);
    }

    /**
     * <p>
     * 문자열의 뒷쪽에 지정한 길이만큼 공백으로 채움
     * </p>
     *
     * @param str
     *            문자열
     * @param length
     *            전체 길이
     * @param isEllipsis
     *            생략 여부
     * @return (String)
     */
    public static String alignLeft(String str, int length, boolean isEllipsis) {

        if (str.length() <= length) {
            StringBuilder temp = new StringBuilder(str);
            for (int i = 0; i < (length - str.length()); i++) {
                temp.append(WHITE_SPACE);
            }
            return temp.toString();
        } else {
            if (isEllipsis) {
                StringBuilder temp = new StringBuilder(str.substring(0, length - 3));
                temp.append("...");
                return temp.toString();
            } else {
                return str.substring(0, length);
            }
        }
    }

    public static String alignRight(String str, int length) {

        return alignRight(str, length, false);
    }

    public static String alignRight(String str, int length, boolean isEllipsis) {

        if (str.length() <= length) {

            StringBuffer temp = new StringBuffer(length);
            for (int i = 0; i < (length - str.length()); i++) {
                temp.append(WHITE_SPACE);
            }
            temp.append(str);
            return temp.toString();
        } else {
            if (isEllipsis) {

                StringBuffer temp = new StringBuffer(length);
                temp.append(str, 0, length - 3);
                temp.append("...");
                return temp.toString();
            } else {
                return str.substring(0, length);
            }
        }
    }

    public static String alignCenter(String str, int length) {
        return alignCenter(str, length, false);
    }

    public static String alignCenter(String str, int length, boolean isEllipsis) {
        if (str.length() <= length) {

            StringBuffer temp = new StringBuffer(length);
            int leftMargin = (length - str.length()) / 2;

            int rightMargin;
            if ((leftMargin * 2) == (length - str.length())) {
                rightMargin = leftMargin;
            } else {
                rightMargin = leftMargin + 1;
            }

            for (int i = 0; i < leftMargin; i++) {
                temp.append(WHITE_SPACE);
            }

            temp.append(str);

            for (int i = 0; i < rightMargin; i++) {
                temp.append(WHITE_SPACE);
            }

            return temp.toString();
        } else {
            if (isEllipsis) {

                StringBuffer temp = new StringBuffer(length);
                temp.append(str, 0, length - 3);
                temp.append("...");
                return temp.toString();
            } else {
                return str.substring(0, length);
            }
        }

    }

    public static String capitalize(String str) {
        return !isNull(str) ? str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase() : str;
    }

    public static boolean isPatternMatch(String str, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(str);
        log.debug("" + matcher);

        return matcher.matches();
    }

    public static String toEng(String kor) throws UnsupportedEncodingException {

        if (isNull(kor)) {
            return null;
        }

        return new String(kor.getBytes("KSC5601"), "8859_1");

    }

    public static String toKor(String en) throws UnsupportedEncodingException {

        if (isNull(en)) {
            return null;
        }

        return new String(en.getBytes("8859_1"), "euc-kr");
    }

    public static int countOf(String str, String charToFind) {
        int findLength = charToFind.length();
        int count = 0;

        for (int idx = str.indexOf(charToFind); idx >= 0; idx = str.indexOf(charToFind, idx + findLength)) {
            count++;
        }

        return count;
    }

    /*
     * StringUtil in Anyframe
     */

    /**
     * Encode a string using algorithm specified in web.xml and return the resulting encrypted password. If exception, the plain credentials string is returned
     *
     * @param password
     *            Password or other credentials to use in authenticating this username
     * @param algorithm
     *            Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();

        MessageDigest md = null;

        try {
            // first create an instance, given the
            // provider
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            log.error("Exception: " + e);

            return password;
        }

        md.reset();

        // call the update method one or more times
        // (useful when you don't know the size of your
        // data, eg. stream)
        md.update(unencodedPassword);

        // now calculate the hash
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    /**
     * Encode a string using Base64 encoding. Used when storing passwords as cookies. This is weak encoding in that anyone can use the decodeString routine to reverse the encoding.
     *
     * @param str
     *            String to be encoded
     * @return String encoding result
     */
    public static String encodeString(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Decode a string using Base64 encoding.
     *
     * @param str
     *            String to be decoded
     * @return String decoding String
     */
    public static String decodeString(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * convert first letter to a big letter or a small letter.<br>
     *
     * <pre>
     * StringUtil.trim('Password') = 'password'
     * StringUtil.trim('password') = 'Password'
     * </pre>
     *
     * @param str
     *            String to be swapped
     * @return String converting result
     */
    public static String swapFirstLetterCase(String str) {
        StringBuilder sbuf = new StringBuilder(str);
        sbuf.deleteCharAt(0);
        if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0])) {
            sbuf.insert(0, str.substring(0, 1).toUpperCase());
        } else {
            sbuf.insert(0, str.substring(0, 1).toLowerCase());
        }
        return sbuf.toString();
    }

    /**
     * If original String has a specific String, remove specific Strings from original String.
     *
     * <pre>
     * StringUtil.trim('pass*word', '*') = 'password'
     * </pre>
     *
     * @param origString
     *            original String
     * @param trimString
     *            String to be trimmed
     * @return converting result
     */
    public static String trim(String origString, String trimString) {
        int startPosit = origString.indexOf(trimString);
        if (startPosit != -1) {
            int endPosit = trimString.length() + startPosit;
            return origString.substring(0, startPosit) + origString.substring(endPosit);
        }
        return origString;
    }

    /**
     * Break a string into specific tokens and return a String of last location.
     *
     * <pre>
     * StringUtil.getLastString('password*password*a*b*c', '*') = 'c'
     * </pre>
     *
     * @param origStr
     *            original String
     * @param strToken
     *            specific tokens
     * @return String of last location
     */
    public static String getLastString(String origStr, String strToken) {
        StringTokenizer str = new StringTokenizer(origStr, strToken);
        String lastStr = "";
        while (str.hasMoreTokens()) {
            lastStr = str.nextToken();
        }
        return lastStr;
    }

    /**
     * If original String has token, Break a string into specific tokens and change String Array. If not, return a String Array which has original String as it is.
     *
     * <pre>
     * StringUtil.getStringArray('passwordabcpassword', 'abc') 		= String[]{'password','password'}
     * StringUtil.getStringArray('pasword*password', 'abc') 		= String[]{'pasword*password'}
     * </pre>
     *
     * @param str
     *            original String
     * @param strToken
     *            specific String token
     * @return String[]
     */
    public static String[] getStringArray(String str, String strToken) {
        if (str.indexOf(strToken) != -1) {
            StringTokenizer st = new StringTokenizer(str, strToken);
            String[] stringArray = new String[st.countTokens()];
            for (int i = 0; st.hasMoreTokens(); i++) {
                stringArray[i] = st.nextToken();
            }
            return stringArray;
        }
        return new String[] { str };
    }

    /**
     * If string is null or empty string, return false. <br>
     * If not, return true.
     *
     * <pre>
     * StringUtil.isNotEmpty('') 		= false
     * StringUtil.isNotEmpty(null) 		= false
     * StringUtil.isNotEmpty('abc') 	= true
     * </pre>
     *
     * @param str
     *            original String
     * @return which empty string or not.
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * If Integer is -1 or smaller, return false. <br>
     * If not, return true.
     *
     * <pre>
     * StringUtils.isNotEmpty(1)                        = true
     * StringUtils.isNotEmpty(0)                        = true
     * StringUtils.isNotEmpty(Integer.parseInt(null))   = NumberFormatException
     * StringUtils.isNotEmpty(-1)                       = false
     * </pre>
     *
     * @param param
     *            (int)
     * @return which empty Integer or not.
     * @since 2015-04-01
     */
    public static boolean isNotEmpty(int param) {
        return -1 < param;
    }

    /**
     * If List is null or no item, return false. <br>
     * If not, return true.
     *
     * <pre>
     * List&lt;String&gt; param;
     * StringUtils.isNotEmpty(param)                    = false
     * param = new ArrayList&lt;String&gt;();
     * StringUtils.isNotEmpty(Integer.parseInt(null))   = false
     * param.add("");
     * StringUtils.isNotEmpty(param)                    = true
     * </pre>
     *
     * @param param
     *            (int)
     * @return which empty Integer or not.
     * @since 2015-04-01
     */
    public static boolean isNotEmpty(List<? extends Object> param) {
        return null != param && 0 < param.size();
    }

    /**
     * If string is null or empty string, return true. <br>
     * If not, return false.
     *
     * <pre>
     * StringUtil.isEmpty('') 		= true
     * StringUtil.isEmpty(null) 	= true
     * StringUtil.isEmpty('abc') 	= false
     * </pre>
     *
     * @param str
     *            original String
     * @return which empty string or not.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * replace replaced string to specific string from original string. <br>
     *
     * <pre>
     * StringUtil.replace('work$id', '$', '.') 	= 'work.id'
     * </pre>
     *
     * @param str
     *            original String
     * @param replacedStr
     *            to be replaced String
     * @param replaceStr
     *            replace String
     * @return converting result
     */
    public static String replace(String str, String replacedStr, String replaceStr) {
        String newStr = "";
        if (str.indexOf(replacedStr) != -1) {
            String s1 = str.substring(0, str.indexOf(replacedStr));
            String s2 = str.substring(str.indexOf(replacedStr) + 1);
            newStr = s1 + replaceStr + s2;
        }
        return newStr;
    }

    /**
     * <pre>
     * StringUtil.isPatternMatching('abc-def', '*-*') 	= true
     * StringUtil.isPatternMatching('abc', '*-*') 	= false
     * </pre>
     *
     * @param str
     *            original String
     * @param pattern
     *            pattern String
     * @return boolean which matches the pattern string or not.
     * @throws Exception
     *             fail to check pattern matched
     */
    public static boolean isPatternMatching(String str, String pattern) throws Exception {
        // if url has wild key, i.e. "*", convert it to
        // ".*" so that we can
        // perform regex matching
        if (pattern.indexOf('*') >= 0) {
            pattern = pattern.replaceAll("\\*", ".*");
        }

        pattern = "^" + pattern + "$";

        return Pattern.matches(pattern, str);
    }

    /**
     * It returns true if string contains a sequence of the same character.
     *
     * <pre>
     * StringUtil.containsMaxSequence('password', '2') 	= true
     * StringUtil.containsMaxSequence('my000', '3') 	= true
     * StringUtil.containsMaxSequence('abbbbc', '5')	= false
     * </pre>
     *
     * @param str
     *            original String
     * @param maxSeqNumber
     *            a sequence of the same character
     * @return which contains a sequence of the same character
     */
    public static boolean containsMaxSequence(String str, String maxSeqNumber) {
        int occurence = 1;
        int max = string2integer(maxSeqNumber);
        if (str == null) {
            return false;
        }

        int sz = str.length();
        for (int i = 0; i < (sz - 1); i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                occurence++;

                if (occurence == max) return true;
            } else {
                occurence = 1;
            }
        }
        return false;
    }

    /**
     * <p>
     * Checks that the String contains certain characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>false</code>. A <code>null</code> invalid character array will return <code>false</code>. An empty String ("") always returns false.
     * </p>
     *
     * <pre>
     * StringUtil.containsInvalidChars(null, *)       			= false
     * StringUtil.containsInvalidChars(*, null)      			= false
     * StringUtil.containsInvalidChars(&quot;&quot;, *)         = false
     * StringUtil.containsInvalidChars(&quot;ab&quot;, '')      = false
     * StringUtil.containsInvalidChars(&quot;abab&quot;, 'xyz') = false
     * StringUtil.containsInvalidChars(&quot;ab1&quot;, 'xyz')  = false
     * StringUtil.containsInvalidChars(&quot;xbz&quot;, 'xyz')  = true
     * </pre>
     *
     * @param str
     *            the String to check, may be null
     * @param invalidChars
     *            an array of invalid chars, may be null
     * @return false if it contains none of the invalid chars, or is null
     */

    public static boolean containsInvalidChars(String str, char[] invalidChars) {
        if (str == null || invalidChars == null) {
            return false;
        }
        int strSize = str.length();
        int validSize = invalidChars.length;
        for (int i = 0; i < strSize; i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < validSize; j++) {
                if (invalidChars[j] == ch) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <p>
     * Checks that the String contains certain characters.
     * </p>
     * <p>
     * A <code>null</code> String will return <code>false</code>. A <code>null</code> invalid character array will return <code>false</code>. An empty String ("") always returns false.
     * </p>
     *
     * <pre>
     * StringUtil.containsInvalidChars(null, *)       			= false
     * StringUtil.containsInvalidChars(*, null)      			= false
     * StringUtil.containsInvalidChars(&quot;&quot;, *)         = false
     * StringUtil.containsInvalidChars(&quot;ab&quot;, '')      = false
     * StringUtil.containsInvalidChars(&quot;abab&quot;, 'xyz') = false
     * StringUtil.containsInvalidChars(&quot;ab1&quot;, 'xyz')  = false
     * StringUtil.containsInvalidChars(&quot;xbz&quot;, 'xyz')  = true
     * </pre>
     *
     * @param str
     *            the String to check, may be null
     * @param invalidChars
     *            a String of invalid chars, may be null
     * @return false if it contains none of the invalid chars, or is null
     */
    public static boolean containsInvalidChars(String str, String invalidChars) {
        if (str == null || invalidChars == null) {
            return true;
        }
        return containsInvalidChars(str, invalidChars.toCharArray());
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        if (sz == 0) return false;
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Reverses a String as per {@link StringBuffer#reverse()}.
     * </p>
     * <p>
     * <code>null</code> String returns <code>null</code>.
     * </p>
     *
     * <pre>
     * StringUtil.reverse(null)  		   = null
     * StringUtil.reverse(&quot;&quot;)    = &quot;&quot;
     * StringUtil.reverse(&quot;bat&quot;) = &quot;tab&quot;
     * </pre>
     *
     * @param str
     *            the String to reverse, may be null
     * @return the reversed String, <code>null</code> if null String input
     */

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuffer(str).reverse().toString();
    }

    /**
     * Make a new String that filled original to a special char as cipers
     *
     * @param originalStr
     *            original String
     * @param ch
     *            a special char
     * @param cipers
     *            cipers
     * @return filled String
     */
    public static String fillString(String originalStr, char ch, int cipers) {
        int originalStrLength = originalStr.length();

        if (cipers < originalStrLength) return null;

        int difference = cipers - originalStrLength;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < difference; i++)
            strBuf.append(ch);

        strBuf.append(originalStr);
        return strBuf.toString();
    }

    /**
     * Determine whether a (trimmed) string is empty
     *
     * @param foo
     *            The text to check.
     * @return Whether empty.
     */
    public static boolean isEmptyTrimmed(String foo) {
        return (foo == null || foo.trim().length() == 0);
    }

    public static List<String> getTokens(String lst, String separator) {
        List<String> tokens = new ArrayList<>();

        if (lst != null) {
            StringTokenizer st = new StringTokenizer(lst, separator);
            while (st.hasMoreTokens()) {
                try {
                    String en = st.nextToken().trim();
                    tokens.add(en);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return tokens;
    }

    /**
     * Return token list which is separated by ","
     *
     * @param lst
     *            string include ','
     * @return List
     */
    public static List<String> getTokens(String lst) {
        return getTokens(lst, ",");
    }

    /**
     * This method convert "string_util" to "stringUtil"
     *
     * @param targetString
     *            targetString
     * @param posChar
     *            posChar
     * @return String result
     */
    public static String convertToCamelCase(String targetString, char posChar) {
        StringBuffer result = new StringBuffer();
        boolean nextUpper = false;
        String allLower = targetString.toLowerCase();

        for (int i = 0; i < allLower.length(); i++) {
            char currentChar = allLower.charAt(i);
            if (currentChar == posChar) {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    currentChar = Character.toUpperCase(currentChar);
                    nextUpper = false;
                }
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    /**
     * Convert a string that may contain underscores to camel case.
     *
     * @param underScore
     *            Underscore name.
     * @return Camel case representation of the underscore string.
     */
    public static String convertToCamelCase(String underScore) {
        return convertToCamelCase(underScore, '_');
    }

    /**
     * Convert a camel case string to underscore representation.
     *
     * @param camelCase
     *            Camel case name.
     * @return Underscore representation of the camel case string.
     */
    public static String convertToUnderScore(String camelCase) {
        String result = "";
        for (int i = 0; i < camelCase.length(); i++) {
            char currentChar = camelCase.charAt(i);
            // This is starting at 1 so the result does
            // not end up with an
            // underscore at the begin of the value
            if (i > 0 && Character.isUpperCase(currentChar)) {
                result = result.concat("_");
            }
            result = result.concat(Character.toString(currentChar).toLowerCase());
        }
        return result;
    }

    public static String toStr(Object arg, String toCharEncoding) {
        return toStr(arg, null, toCharEncoding);
    }

    public static String toStr(Object arg, String fromCharEncoding, String toCharEncoding) {
        if (arg == null) return "";
        else {
            if (arg instanceof String) {
                String str = arg.toString();
                if (null == toCharEncoding) {
                    return str;
                } else {
                    try {
                        if (null == fromCharEncoding) {
                            return new String(str.getBytes(), toCharEncoding);
                        } else {
                            return new String(str.getBytes(fromCharEncoding), toCharEncoding);
                        }
                    } catch (UnsupportedEncodingException e) {
                        return str;
                    }
                }
            } else {
                return arg.toString();
            }
        }
    }

    public static String toStrWithQuoted(Object obj) {
        return "\"".concat(toStr(obj, null, null)).concat("\"");
    }

    public static String toStr(Object obj) {
        return toStr(obj, null, null);
    }

    public static String defaultString(Object obj) {
        return defaultString(obj, "");
    }

    public static String defaultString(Object obj, String defaultString) {
        return obj == null ? defaultString : obj.toString();
    }

    public static boolean hasText(String str) {
        return org.springframework.util.StringUtils.hasText(str);
    }

    public static boolean hasText(CharSequence charSequence) {
        return org.springframework.util.StringUtils.hasText(charSequence);
    }

    /**
     *
     *
     *
     *
     */

    /**
     * 문자열이 null 인경우 flase 리턴
     *
     * 문자열이 Y, y, TRUE, true 인 겨우 true 리턴
     *
     * @param str
     *            string value
     * @return boolean true or false
     */
    public static boolean string2Boolean(String str) {
        return string2Boolean(str, false);
    }

    /**
     * 문자열이 null 인경우 defaultValue return
     *
     * 문자열이 Y, y, TRUE, true 인 겨우 true return
     *
     * @param str
     *            string value
     * @param defaultValue
     *            default value
     * @return boolean true or false
     */
    public static boolean string2Boolean(String str, boolean defaultValue) {

        if (isNull(str)) {
            return defaultValue;
        }

        boolean result = false;
        if (str.equalsIgnoreCase("Y") || str.equalsIgnoreCase("TRUE")) result = true;

        return result;
    }

    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("'", "''");
    }

    public static String escapeSql(String str, int length) {
        if (str == null) {
            return null;
        }
        return cutInStringByBytes(str.replaceAll("'", "''"), length);
    }

    public static String removeSingleQuote(String str, int length) {
        if (str == null) {
            return null;
        }
        return cutInStringByBytes(str.replaceAll("'", ""), length);
    }

    public static String cutInStringByBytes(String str, int length) {
        byte[] bytes = str.getBytes();
        int len = bytes.length;
        int counter = 0;
        if (length >= len) {
            return str;
        }
        for (int i = length - 1; i >= 0; i--) {
            if ((bytes[i] & 0x80) != 0) counter++;
        }
        return new String(bytes, 0, length - (counter % 2));
    }

    /*public static String formatDuration(long durationMillis, String format) {
        return org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(durationMillis, format);
    }*/

    public static String messageFormat(String messageKey, Object[] params) {
        String msg = null;
        if (!isNull(messageKey)) {
            MessageFormat form = new MessageFormat(messageKey);
            msg = form.format(params);
        } else {
            msg = messageKey;
        }

        return msg;
    }

    public static String quoteCommand(String cmd) {
        StringBuffer b = new StringBuffer();

        StringTokenizer st = new StringTokenizer(cmd, "'", true);
        while (st.hasMoreTokens()) {
            String t = st.nextToken();

            if ("'".equals(t)) {
                b.append("\\'");
            } else {
                b.append("'");
                b.append(t);
                b.append("'");
            }
        }

        return b.toString();
    }

    public static boolean isTrue(@Nullable String s) {
        return (null != s) && ((Boolean.parseBoolean(s)) || ("yes".equalsIgnoreCase(s)) || ("1".equals(s)) || ("y".equalsIgnoreCase(s)));
    }
}
