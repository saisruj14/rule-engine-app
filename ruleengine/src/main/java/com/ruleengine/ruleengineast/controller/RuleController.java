package com.ruleengine.ruleengineast.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruleengine.ruleengineast.DTO.EvaluateRequest;
import com.ruleengine.ruleengineast.model.Node;
import com.ruleengine.ruleengineast.service.RuleService;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;
    
    
    // API endpoint to create a new rule
    
    @PostMapping("/create")
    public ResponseEntity<Node> createRule(@RequestBody String ruleString) {
        try {
            Node ast = ruleService.createRule(ruleString);
            return new ResponseEntity<>(ast, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // API endpoint for combining multiple rules
    
    @PostMapping("/combine")
    public ResponseEntity<Node> combineRules(@RequestBody List<String> rules) {
        try {
            Node combinedNode = ruleService.combineRules(rules);
            return new ResponseEntity<>(combinedNode, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // API endpoint to evaluate and validate the input JSON data provided
    @PostMapping("/evaluate")
    public ResponseEntity<Boolean> evaluateRule(@RequestBody EvaluateRequest request) {
        try {
            Node ast = request.getAst();
            Map<String, Object> data = request.getData();

            boolean result = ruleService.evaluateRule(ast, data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (ClassCastException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}