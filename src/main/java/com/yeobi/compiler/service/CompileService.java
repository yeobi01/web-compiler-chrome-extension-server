package com.yeobi.compiler.service;

import com.yeobi.compiler.dto.CompileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CompileService {

    private static final String COMPILER = "g++";

    public CompileDTO.CompileResponse compile(CompileDTO.CompileRequest compileRequest) throws IOException, InterruptedException {
        Path tempDir = Files.createTempDirectory("code-run");

        String filename = UUID.randomUUID().toString();
        File sourceFile = new File(tempDir.toFile() + filename + ".cpp");
        File binaryFile = new File(tempDir.toFile() + filename);

        Files.write(sourceFile.toPath(), compileRequest.getCode().getBytes());

        Process compileProcess = new ProcessBuilder(COMPILER, sourceFile.getAbsolutePath(), "-o", binaryFile.getAbsolutePath())
                .redirectErrorStream(true)
                .start();

        String compileOutput = readProcessOutput(compileProcess.getInputStream());
        int compileExitCode = compileProcess.waitFor();
        if(compileExitCode != 0){
            cleanup(sourceFile, binaryFile);
            return new CompileDTO.CompileResponse("Compile Error", compileOutput);
        }

        Process runProcess = new ProcessBuilder(binaryFile.getAbsolutePath())
                .redirectErrorStream(true)
                .start();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(runProcess.getOutputStream()))) {
            writer.write(compileRequest.getInput());
            writer.flush();
        }

        String executionOutput = readProcessOutput(runProcess.getInputStream());
        int runExitCode = runProcess.waitFor();

        cleanup(sourceFile, binaryFile);

        if (runExitCode != 0) {
            return new CompileDTO.CompileResponse("실행 중 에러:\n" + executionOutput, null);
        }

        return new CompileDTO.CompileResponse(executionOutput, null);
    }
    private String readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }

    private void cleanup(File... files) {
        for (File file : files) {
            if (file.exists()) file.delete();
        }
    }

}

// g++ Main.cc -o Main -O2 -Wall -lm -static -std=gnu++17 -DONLINE_JUDGE -DBOJ
