package com.yeobi.compiler.service;

import com.yeobi.compiler.dto.CompileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompileService {
    public CompileDTO.CompileResponse compile(CompileDTO.CompileRequest compileRequest) {

        return null;
    }
}
