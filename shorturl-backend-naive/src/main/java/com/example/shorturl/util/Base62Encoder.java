package com.example.shorturl.util;

public class Base62Encoder {
    static public String encode(int num) throws Exception {
        if (num < 0)
            throw new Exception("Input should be non-negative");
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            int digit = num % 62;
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
                throw new Exception("Invalid token");
        }
        return num;
    }
}
