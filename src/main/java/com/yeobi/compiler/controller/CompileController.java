package com.yeobi.compiler.controller;

import com.yeobi.compiler.dto.CompileDTO;
import com.yeobi.compiler.service.CompileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compile")
public class CompileController {

    private final CompileService compileService;

    @PostMapping
    public ResponseEntity<CompileDTO.CompileResponse> compile(
            @RequestBody CompileDTO.CompileRequest compileRequest
    ) throws IOException, InterruptedException {
        CompileDTO.CompileResponse compileResponse = compileService.compile(compileRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(compileResponse);
    }
}
