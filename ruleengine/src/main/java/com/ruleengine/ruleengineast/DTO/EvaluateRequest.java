package com.ruleengine.ruleengineast.DTO;

import java.util.Map;

import com.ruleengine.ruleengineast.model.Node;

// A class used as DTO or Data Transfer Object for evaluation of the JSON request as given as input by user.
public class EvaluateRequest {
    private Node ast;
    private Map<String, Object> data;

    // Getters and Setters
    public Node getAst() {
        return ast;
    }

    public void setAst(Node ast) {
        this.ast = ast;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
