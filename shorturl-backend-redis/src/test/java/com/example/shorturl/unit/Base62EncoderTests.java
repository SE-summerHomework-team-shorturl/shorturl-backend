package com.example.shorturl.unit;

import com.example.shorturl.util.Base62Encoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base62EncoderTests {
    Base62Encoder encoder = new Base62Encoder();

    @Test
    void encode_simpleNum_encoded() {
        Assertions.assertEquals(encoder.encode(946441234), "123AbC");
        Assertions.assertEquals(encoder.encode(2147483647), "2LKcb1");
    }

    @Test
    void encode_negativeNum_exceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            encoder.encode(-1);
        });
    }

    @Test
    void encode_numIsZero_zeroString() {
        String token = encoder.encode(0);
        Assertions.assertEquals("0", token);
    }

    @Test
    void decode_simpleToken_decoded() {
        Assertions.assertEquals(encoder.decode("123AbC"), 946441234);
        Assertions.assertEquals(encoder.decode("2LKcb1"), 2147483647);
    }

    @Test
    void decode_invalidCharacters_exceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            encoder.decode("123^_^Abc");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            encoder.decode("上海交通大学");
        });
    }

    @Test
    void decode_emptyToken_exceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            encoder.decode("");
        });
    }

    @Test
    void decode_tokenContainsPrefixZero_exceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            encoder.decode("000123AbC");
        });
    }
}
