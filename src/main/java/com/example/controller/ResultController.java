package com.example.controller;

import com.example.service.ResultServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Tag(name = "Контроллер для получения результата")
@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultServiceImpl resultService;

    @Autowired
    public ResultController(ResultServiceImpl resultService) {
        this.resultService = resultService;
    }

    @Operation(
            summary = "Получить количество вхождений символов",
            description = "Результатом работы является отсортированный в убывающем порядке список символов " +
                    "с количеством их повторений в строке"
    )
    @GetMapping
    public ResponseEntity<String> getResult(@RequestParam(defaultValue = "") @Parameter(description = "Любая строка, " +
            "количество пробелов не считается") String input) {
        return ResponseEntity.ok()
                .contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8))
                .body(resultService.getResult(input));
    }
}
