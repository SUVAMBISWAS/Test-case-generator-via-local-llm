package com.testgen.backend.model;

import java.util.List;

public class TestSuite {
    private String test_suite_name;
    private List<TestCase> cases;

    // Getters and Setters
    public String getTest_suite_name() {
        return test_suite_name;
    }

    public void setTest_suite_name(String test_suite_name) {
        this.test_suite_name = test_suite_name;
    }

    public List<TestCase> getCases() {
        return cases;
    }

    public void setCases(List<TestCase> cases) {
        this.cases = cases;
    }
}
