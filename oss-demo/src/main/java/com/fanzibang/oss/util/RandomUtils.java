package com.fanzibang.oss.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机字符串生成工具类
 * 支持生成指定长度的随机字符串，可自定义字符集
 */
public class RandomUtils {
    
    // 默认字符集：大小写字母和数字
    private static final String DEFAULT_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    // 数字字符集
    private static final String NUMBER_CHARSET = "0123456789";
    
    // 字母字符集（大小写）
    private static final String ALPHA_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    // 字母字符集（大写）
    private static final String UPPER_ALPHA_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // 字母字符集（小写）
    private static final String LOWER_ALPHA_CHARSET = "abcdefghijklmnopqrstuvwxyz";
    
    // 特殊字符集
    private static final String SPECIAL_CHARSET = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    
    // 安全随机数生成器
    private static final SecureRandom secureRandom = new SecureRandom();
    
    // 普通随机数生成器（性能更高）
    private static final Random random = new Random();
    
    /**
     * 生成指定长度的随机字符串（使用默认字符集）
     * @param length 字符串长度
     * @return 随机字符串
     */
    public static String generate(int length) {
        return generate(length, DEFAULT_CHARSET);
    }
    
    /**
     * 生成指定长度的随机数字字符串
     * @param length 字符串长度
     * @return 随机数字字符串
     */
    public static String generateNumbers(int length) {
        return generate(length, NUMBER_CHARSET);
    }
    
    /**
     * 生成指定长度的随机字母字符串（大小写混合）
     * @param length 字符串长度
     * @return 随机字母字符串
     */
    public static String generateAlpha(int length) {
        return generate(length, ALPHA_CHARSET);
    }
    
    /**
     * 生成指定长度的随机大写字母字符串
     * @param length 字符串长度
     * @return 随机大写字母字符串
     */
    public static String generateUpperAlpha(int length) {
        return generate(length, UPPER_ALPHA_CHARSET);
    }
    
    /**
     * 生成指定长度的随机小写字母字符串
     * @param length 字符串长度
     * @return 随机小写字母字符串
     */
    public static String generateLowerAlpha(int length) {
        return generate(length, LOWER_ALPHA_CHARSET);
    }
    
    /**
     * 生成指定长度的随机字母数字字符串（包含特殊字符）
     * @param length 字符串长度
     * @return 随机字母数字字符串（包含特殊字符）
     */
    public static String generateWithSpecialChars(int length) {
        return generate(length, DEFAULT_CHARSET + SPECIAL_CHARSET);
    }
    
    /**
     * 生成指定长度的随机字符串，使用自定义字符集
     * @param length 字符串长度
     * @param charset 自定义字符集
     * @return 随机字符串
     */
    public static String generate(int length, String charset) {
        if (length <= 0) {
            throw new IllegalArgumentException("字符串长度必须大于0");
        }
        
        if (charset == null || charset.isEmpty()) {
            throw new IllegalArgumentException("字符集不能为空");
        }
        
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            // 使用SecureRandom生成更安全的随机数（适合密码等安全场景）
            // 如果对性能要求更高，可以替换为 random.nextInt(charset.length())
            int randomIndex = secureRandom.nextInt(charset.length());
            result[i] = charset.charAt(randomIndex);
        }
        
        return new String(result);
    }
    
    /**
     * 生成指定长度的随机字符串，保证至少包含一个大写字母、一个小写字母、一个数字和一个特殊字符
     * 适合生成强密码
     * @param length 字符串长度（至少为4）
     * @return 符合要求的随机字符串
     */
    public static String generateStrongPassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("强密码长度必须至少为4");
        }
        
        // 确保至少包含一个大写字母、一个小写字母、一个数字和一个特殊字符
        char[] result = new char[length];
        
        // 随机位置放置一个大写字母
        int upperPos = secureRandom.nextInt(length);
        result[upperPos] = UPPER_ALPHA_CHARSET.charAt(secureRandom.nextInt(UPPER_ALPHA_CHARSET.length()));
        
        // 随机位置放置一个小写字母
        int lowerPos;
        do {
            lowerPos = secureRandom.nextInt(length);
        } while (lowerPos == upperPos);
        result[lowerPos] = LOWER_ALPHA_CHARSET.charAt(secureRandom.nextInt(LOWER_ALPHA_CHARSET.length()));
        
        // 随机位置放置一个数字
        int numberPos;
        do {
            numberPos = secureRandom.nextInt(length);
        } while (numberPos == upperPos || numberPos == lowerPos);
        result[numberPos] = NUMBER_CHARSET.charAt(secureRandom.nextInt(NUMBER_CHARSET.length()));
        
        // 随机位置放置一个特殊字符
        int specialPos;
        do {
            specialPos = secureRandom.nextInt(length);
        } while (specialPos == upperPos || specialPos == lowerPos || specialPos == numberPos);
        result[specialPos] = SPECIAL_CHARSET.charAt(secureRandom.nextInt(SPECIAL_CHARSET.length()));
        
        // 填充剩余位置
        String allChars = DEFAULT_CHARSET + SPECIAL_CHARSET;
        for (int i = 0; i < length; i++) {
            if (i != upperPos && i != lowerPos && i != numberPos && i != specialPos) {
                result[i] = allChars.charAt(secureRandom.nextInt(allChars.length()));
            }
        }
        
        // 随机打乱顺序
        shuffleArray(result);
        
        return new String(result);
    }
    
    // Fisher-Yates洗牌算法
    private static void shuffleArray(char[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = secureRandom.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
    // 示例用法
    public static void main(String[] args) {
        System.out.println("默认随机字符串(10位): " + generate(10));
        System.out.println("纯数字随机字符串(6位): " + generateNumbers(6));
        System.out.println("纯字母随机字符串(8位): " + generateAlpha(8));
        System.out.println("自定义字符集(只包含ABC): " + generate(5, "ABC"));
        System.out.println("强密码(12位): " + generateStrongPassword(12));
    }
}