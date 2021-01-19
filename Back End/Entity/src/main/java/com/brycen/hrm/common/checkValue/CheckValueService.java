package com.brycen.hrm.common.checkValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [Description]:Check value common <br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class CheckValueService {

    /**
     * Half-width alphanumeric characters
     */
    private static final String regAlphaNume = "[a-zA-Z0-9]+";

    /**
     * Half-width lowercase letters
     */
    private static final String regLowerAlphaChk = "[a-z]+";

    /**
     * Half-width English capital letters
     */
    private static final String regUpperAlphaChk = "[A-Z]+";

    /**
     * Half-width numbers
     */
    public static final String regNumChk = "[0-9]+";

    /**
     * Half-width symbol' is excluded
     */
    private static final String regSymbolChk = "[ -&(-/:-@\\[-\\`\\{-\\~]+";

    /**
     * Single-byte alphanumeric characters and symbols to check for passwords, excluding single-byte spaces and'
     */
    private static final String regPwdChk = "[a-zA-Z0-9!-&(-/:-@\\[-\\`\\{-\\~]+";

    /**
     * Constructor
     */
    private CheckValueService() {
    }

    /**
     * CheckNull
     *
     * @param str
     * @return
     */
    synchronized public static Boolean checkNull(String str) {
        return ("".equals(str)) || (str == null);
    }

    /**
     * Check half-width numbers or half-width alphabets
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean isAlphaNumber(String str) {
        return !str.matches(regAlphaNume);
    }

    /**
     * Check whether half-width lowercase letters are included
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean partialMatchLowerAlpha(String str) {
        return !str.matches(".*" + regLowerAlphaChk + ".*");
    }

    /**
     * Check whether half-width uppercase letters are included
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean partialMatchUpperAlpha(String str) {
        return !str.matches(".*" + regUpperAlphaChk + ".*");
    }

    /**
     * Check whether half-width numbers are included
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean partialMatchNum(String str) {
        return !str.matches(".*" + regNumChk + ".*");
    }

    /**
     * Check if single-byte symbols are included
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean partialMatchSymbol(String str) {
        return !str.matches(".*" + regSymbolChk + ".*");
    }

    /**
     * Check for single-byte numbers or single-byte alphabets or single-byte symbols for passwords. Return true if single-byte spaces and' are included.
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean isPwdChk(String str) {
        return !str.matches(regPwdChk);
    }

    /**
     * Check if it is less than a certain number of characters
     * 
     * @param str
     * @param min
     * @return
     */
    synchronized public static Boolean isMinLength(String str, int min) {
        if (str.length() < min) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if it is larger than a certain number of characters
     * 
     * @param str
     * @param max
     * @return
     */
    synchronized public static Boolean checkMaxLength(String str, int max) {
        if (str != null && str.length() > max) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if it is smaller than a certain number of characters
     * [ Remarks ]:<br/>
     *
     * @param str
     * @param min
     * @return
     */
    synchronized public static Boolean checkMinLength(String str, int min) {
        if (str != null && str.length() < min) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Half-width katakana check
     * 
     * @param str
     * @return
     */
    synchronized public static Boolean checkHalfKata(String str) {

        for (char c : str.toCharArray()) {
            if (String.valueOf(c).matches("[\uff65-\uff9f]"))
                return true;
        }
        return false;
    }

    /**
     * Check number type (integer/decimal/minus)
     * 
     * @param num
     * @return
     */
    synchronized public static Boolean isFloat(String num) {
        String regex = "^\\-?[0-9]*\\.?[0-9]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(num);
        return m.find();
    }

    /**
     * Check if it is a negative value
     * 
     * @param num
     * @return
     */
    synchronized public static Boolean isMinux(String num) {
        String firstChar = num.substring(0, 1);
        if (firstChar.equals("-")) {
            return true;
        }
        return false;
    }

    /**
     * Check if it is a decimal value
     * 
     * @param num
     * @return
     */
    synchronized public static Boolean isDecimals(String num) {
        Integer pos = num.indexOf(".");
        if (pos == -1) {
            return false;
        }
        return true;
    }

    /**
     * Get the number of digits in the integer part
     * 
     * @param num
     * @return
     */
    synchronized public static Integer getIntPartDigit(String num) {
        String numList[] = num.split("\\.", 0);

        if (numList.length == 0) {
            // Integer
            return num.length();
        } else {
            return numList[0].length();
        }
    }

    /**
     * Get the number of decimal places
     * 
     * @param num
     * @return
     */
    synchronized public static Integer getDecimalPartDigit(String num) {
        String numList[] = num.split("\\.");

        if (numList.length == 1) {
            // Integer only
            return 0;
        } else {
            return numList[1].length();
        }
    }

    /**
     * Get the number of digits in the integer part
     * 
     * @param num
     * @return
     */
    synchronized public static Integer getIntPartDigit(BigDecimal num) {
        return num.precision();
    }

    /**
     * Get the number of decimal places
     * 
     * @param num
     * @return
     */
    synchronized public static Integer getDecimalPartDigit(BigDecimal num) {
        return num.scale();
    }

    /**
     * Check if the date is correct
     * 
     * @param num
     * @return
     */
    synchronized public static Boolean isDate(String date) {

        if (date == null || "".equals(date)) {
            return false;
        }

        // Check with the delimiter "/"
        Boolean retSlash = isDateInputDelimiter(date, "/");
        if (retSlash == true) {
            return true;
        }

        // Check with the delimiter "-"
        Boolean retHaifun = isDateInputDelimiter(date, "-");
        if (retHaifun == true) {
            return true;
        }

        return false;

    }

    /**
     * Type check by specifying delimiter
     * 
     * @param date
     * @param delimiter
     * @return
     */
    synchronized private static Boolean isDateInputDelimiter(String date, String delimiter) {

        // Insert a slash for 8 digits
        if (date.length() == 8 && date.replace(delimiter, "").length() == 8) {
            date = date.substring(0, 4) + delimiter + date.substring(4, 6) + delimiter + date.substring(6, 8);
        }

        DateFormat format = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");

        try {
            // Not only the type but also the invalid date (2015/05/32 etc.) as an error
            format.setLenient(false);
            format.parse(date);

            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * [Description]:Check number<br/>
     * [ Remarks ]:<br/>
     *
     * @param str
     * @return
     */
    synchronized public static Boolean checkNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            char result = str.charAt(i);
            String s = Character.toString(result);
            Boolean test = !s.matches(".*" + regNumChk + ".*");
            if (test == true) {
                return test;
            }
        }
        return false;
    }

    /**
     * [Description]:checkTrim for string<br/>
     * [ Remarks ]:<br/>
     *
     * @param str
     * @return
     */
    synchronized public static String checkTrim(String str) {
        if ("".equals(str) || str == null) {
            return str;
        }
        return str.trim();
    }

}