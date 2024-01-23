package com.example;

import com.example.service.ResultServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ResultServiceTest {
    @Autowired
    ResultServiceImpl resultService;

    @ParameterizedTest
    @CsvSource(value = {
            "aaaaabbbc/{a=5, b=3, c=1}",
            "/{}",
            "   /{}",
            "aaaaa  bbb  c /{a=5, b=3, c=1}"
    }, ignoreLeadingAndTrailingWhitespace = false, delimiter = '/')
    void getResult_ValidString_ReturnsValidResult(String input, String expected) {
        String actual = resultService.getResult(input);

        assertEquals(expected, actual);
    }
}
