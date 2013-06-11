/*
 * WordUtils.java
 * created in 2013/07/13
 *
 * (C) Copyright 2003-2013 GreenDay Project. All rights reserved.
 */
package proj.green.javafx.modules.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * 文字処理を行うクラス。
 * 
 * @version 01-00
 * @since 01-00
 * @author T.TSUCHIE
 *
 */
public class WordUtils {
    
    /**
     * 全角文字
     */
    public static final char[] CHAR_FULL = new char[] {
        'Ａ', 'Ｂ', 'Ｃ', 'Ｄ', 'Ｅ', 'Ｆ', 'Ｇ', 'Ｈ', 'Ｉ', 'Ｊ', 'Ｋ', 'Ｌ', 'Ｍ', 'Ｎ', 'Ｏ', 'Ｐ', 'Ｑ', 'Ｒ', 'Ｓ', 'Ｔ', 'Ｕ', 'Ｖ', 'Ｗ', 'Ｘ', 'Ｙ', 'Ｚ',
        'ａ', 'ｂ', 'ｃ', 'ｄ', 'ｅ', 'ｆ', 'ｇ', 'ｈ', 'ｉ', 'ｊ', 'ｋ', 'ｌ', 'ｍ', 'ｎ', 'ｏ', 'ｐ', 'ｑ', 'ｒ', 'ｓ', 'ｔ', 'ｕ', 'ｖ', 'ｗ', 'ｘ', 'ｙ', 'ｚ', 
        '０', '１', '２', '３', '４', '５', '６', '７', '８', '９',
        '！', '”', '＃', '＄', '％', '＆', '’', '（', '）', '＝', '－', '＋', '～', '＾', '￥', '＠', '‘', '［', '］', '｛', '｝', '；', '：', '，', '＊', '＜', '＞', '．', '？', '＿', 
        '　', 
    };
    
    /**
     * 半角文字
     */
    public static final char[] CHAR_HALF = new char[] {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '!', '\'', '#', '$', '%', '&', '\'', '(', ')', '=', '-', '+', '~', '^', '\\', '@', '`', '[', ']', '{', '}', ';', ':', ',', '*', '<', '>', '.', '?', '_', 
        ' ',
    };
    
    final static Map<Character, Character> full2HalfMap;
    static {
        assert CHAR_FULL.length == CHAR_HALF.length;
        
        Map<Character, Character> map = new HashMap<>();
        for(int i=0; i < CHAR_FULL.length; i++) {
            map.put(CHAR_FULL[i], CHAR_HALF[i]);
        }
        
        full2HalfMap = Collections.unmodifiableMap(map);
    }
    
    final static Map<Character, Character> half2FullMap;
    static {
        assert CHAR_FULL.length == CHAR_HALF.length;
        
        Map<Character, Character> map = new HashMap<>();
        for(int i=0; i < CHAR_HALF.length; i++) {
            map.put(CHAR_HALF[i], CHAR_FULL[i]);
        }
        
        half2FullMap = Collections.unmodifiableMap(map);
    }
    
    /**
     * 全角文字を半角に変換する。
     * <p>引数がnullの場合は、空文字を返す。
     * 
     * @param str
     * @return
     */
    public static final String convertFull2half(final String str) {
        
        if(str == null || str.isEmpty()) {
            return "";
        }
        
        final int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        
        for(int i=0; i < length; i++) {
            final char c = str.charAt(i);
            if(full2HalfMap.containsKey(c)) {
                sb.append(full2HalfMap.get(c));
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
        
    }
    
    /**
     * 半角を全角に変換する。
     * @param str
     * @return
     */
    public static final String convertHalf2Full(final String str) {
        
        if(str == null || str.isEmpty()) {
            return "";
        }
        
        final int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        
        for(int i=0; i < length; i++) {
            final char c = str.charAt(i);
            if(half2FullMap.containsKey(c)) {
                sb.append(half2FullMap.get(c));
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
        
    }
    
    /**
     * 指定した文字数で折り返しをする。
     * <p>引数がnullの場合は、空文字を返す。
     * @param text
     * @param wrapLength 折り返しする文字数。 1以上を設定する。
     * @return
     */
    public static String wrapText(final String text, final int wrapLength) {
        
        if(text == null || text.isEmpty()) {
            return "";
        }
        
        if(wrapLength <= 0) {
            throw new IllegalArgumentException("wrapLength should be not less than 1(>= 1)");
        }
        
        final int messageLength = text.length();
        final StringBuilder str = new StringBuilder(messageLength + 32);
        if(wrapLength <= 0) {
            return text;
        } else if(messageLength <= wrapLength) {
            return text;
            
        } else {
            
            int offset = 0;
            while((messageLength - offset) > wrapLength) {
                int endIndex = offset + wrapLength;
                str.append(text.substring(offset, endIndex));
                
                if(offset == 0 || endIndex != wrapLength) {
                    str.append(System.lineSeparator());
                }
                
                offset = endIndex;
                
            }
            
            str.append(text.substring(offset));
            
        }
        
        return str.toString();
    }
    
}
