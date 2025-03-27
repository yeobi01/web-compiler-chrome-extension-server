package com.yeobi.compiler.dto;

import com.yeobi.compiler.global.enums.LanguageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CompileDTO {

    @Getter
    @AllArgsConstructor
    public static class CompileRequest {

        private LanguageType language;
        private String code;
        private String input;
    }

    @Getter
    @AllArgsConstructor
    public static class CompileResponse {

        private String output;
        private String error;
    }
}
