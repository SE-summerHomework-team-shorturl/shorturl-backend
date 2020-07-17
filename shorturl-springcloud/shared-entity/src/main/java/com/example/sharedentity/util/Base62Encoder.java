package com.example.sharedentity.util;

public class Base62Encoder {
    static final public String negative_err_msg="num should be non-negative";
    static final public String empty_token_msg="token is empty";
    static final public String prefix_zero_msg="token contains prefix zero";
    static final public String Invalid_token_msg="Invalid token";

    static public String encode(int num) throws Exception {
        if (num < 0)
            throw new IllegalArgumentException( negative_err_msg);
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            long digit = num % 62;
            if (digit < 10)
                builder.append((char) ('0' + digit));
            else if (digit < 36)
                builder.append((char) ('A' + digit - 10));
            else
                builder.append((char) ('a' + digit - 36));
            num /= 62;
        }
        return builder.length() == 0 ? "0" : builder.reverse().toString();
    }

    static public int decode(String token) throws Exception {
        int num = 0;
        int len = token.length();
        if (len == 0)
            throw new IllegalArgumentException(empty_token_msg);
        if (len > 1 && token.charAt(0) == '0')
            throw new IllegalArgumentException(prefix_zero_msg);
        for (int i = 0; i < len; ++i) {
            char c = token.charAt(i);
            num *= 62;
            if (c >= '0' && c <= '9')
                num += c - '0';
            else if (c >= 'A' && c <= 'Z')
                num += (c - 'A') + 10;
            else if (c >= 'a' && c <= 'z')
                num += (c - 'a') + 36;
            else
                throw new IllegalArgumentException(Invalid_token_msg);
        }
        return num;
    }
}
