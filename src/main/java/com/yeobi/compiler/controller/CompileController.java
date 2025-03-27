package com.yeobi.compiler.controller;

import com.yeobi.compiler.dto.CompileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compile")
public class CompileController {

    @PostMapping
    public ResponseEntity<CompileDTO.CompileResponse> compile(
            @RequestBody CompileDTO.CompileRequest compileRequest
    ) {



        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
