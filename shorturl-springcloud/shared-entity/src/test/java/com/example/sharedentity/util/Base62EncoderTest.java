package com.example.sharedentity.util;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Base62EncoderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    @DisplayName("shouldSuccessWhenSimpleEncode")
    void encode_simpleNum_encoded() throws Exception {
        Assertions.assertEquals(Base62Encoder.encode(946441234), "123AbC");
        Assertions.assertEquals(Base62Encoder.encode(2147483647), "2LKcb1");
    }

    @Test
    @DisplayName("shouldExceptionWhenNegativeNum")
    void encode_negativeNum_exceptionThrown() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Base62Encoder.encode(-1);
        });
        assertEquals(  Base62Encoder.negative_err_msg, exception.getMessage());
    }

    @Test
    @DisplayName("should0WhenNum0")
    void encode_numIsZero_zeroString() throws Exception {
        String token = Base62Encoder.encode(0);
        Assertions.assertEquals("0", token);
    }

    @Test
    @DisplayName("shouldSuccessWhenSimpleDecode")
    void decode_simpleToken_decoded() throws Exception {
        Assertions.assertEquals(Base62Encoder.decode("123AbC"), 946441234);
        Assertions.assertEquals(Base62Encoder.decode("2LKcb1"), 2147483647);
    }

    @Test
    @DisplayName("shouldExceptionWhenInvalidCharacters")
    void decode_invalidCharacters_exceptionThrown() {
        Throwable exception1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Base62Encoder.decode("123^_^Abc");
        });
        assertEquals(  Base62Encoder.Invalid_token_msg, exception1.getMessage());
        Throwable exception2= Assertions.assertThrows(Exception.class, () -> {
            Base62Encoder.decode("上海交通大学");

        });
        assertEquals(  Base62Encoder.Invalid_token_msg, exception2.getMessage());
    }

    @Test
    @DisplayName("shouldExceptionWhenEmptyToken")
    void decode_emptyToken_exceptionThrown() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Base62Encoder.decode("");
        });
        assertEquals(  Base62Encoder.empty_token_msg, exception.getMessage());
    }

    @Test
    @DisplayName("shouldExceptionWhenPrefixZero")
    void decode_tokenContainsPrefixZero_exceptionThrown() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Base62Encoder.decode("000123AbC");
        });
        assertEquals(  Base62Encoder.prefix_zero_msg, exception.getMessage());
    }
}
