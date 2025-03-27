package com.yeobi.compiler.dto;

import com.yeobi.compiler.global.enums.LanguageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CompileDTO {

    @Getter
    @AllArgsConstructor
    public static class Input{
        private String input;
        private String expectedOutput;
    }

    @Getter
    @AllArgsConstructor
    public static class CompileRequest {
        private LanguageType language;
        private String code;
        private Input[] inputs;
    }

    @Getter
    @AllArgsConstructor
    public static class Output{
        private String output;
        private String expectedOutput;
        private String realOutput;
        private Boolean result;
    }

    @Getter
    @AllArgsConstructor
    public static class CompileResponse {
        private Output[] outputs;
        private String compileOutput;
        private Boolean compileError;
    }
}
