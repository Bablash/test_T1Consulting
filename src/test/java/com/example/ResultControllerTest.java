package com.example;


import com.example.controller.ResultController;
import com.example.service.ResultServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ResultControllerTest {
    @Mock
    ResultServiceImpl resultService;

    @InjectMocks
    ResultController resultController;

    @Test
    @DisplayName("GET /result возвращает HTTP-ответ со статусом 200 OK и список символов с количеством их повторений")
    void getResult_ReturnsValidResponseEntity() {
        String input = "aaaaabbbc";
        String result = "{a=5, b=3, c=1}";
        doReturn(result).when(this.resultService).getResult(input);

        var responseEntity = this.resultController.getResult(input);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8), responseEntity.getHeaders().getContentType());
        assertEquals(result, responseEntity.getBody());
    }
}
