package com.testgen.backend.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;

public class TestCase {
    private String id;
    private String description;
    private List<String> steps;
    private String expected;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getExpected() {
        return expected;
    }

    @JsonProperty("expected")
    @JsonAlias({ "expected_result", "expectedResult", "result", "Expected Result", "Expected", "expected_output",
            "Expected Output" })
    public void setExpected(String expected) {
        this.expected = expected;
        // Fallback or logging could go here if needed
    }
}
