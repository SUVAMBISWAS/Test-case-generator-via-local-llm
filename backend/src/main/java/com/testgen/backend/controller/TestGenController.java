package com.testgen.backend.controller;

import com.testgen.backend.model.TestSuite;
import com.testgen.backend.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow frontend access
public class TestGenController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/generate")
    public TestSuite generateHelper(@RequestBody String requirement) {
        return ollamaService.generateTestCases(requirement);
    }
}
