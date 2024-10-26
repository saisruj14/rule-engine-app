package com.ruleengine.ruleengineast.service;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruleengine.ruleengineast.model.Node;
import com.ruleengine.ruleengineast.repository.RuleRepository;


// The main service class to deploy all the functions related to creation of rules, combining all rules and evaluation JSON input of user conditions. 
@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public Node createRule(String ruleString) {
        return parseRule(ruleString);
    }

    public Node combineRules(List<String> rules) {
        Node combinedNode = null;

        for (String rule : rules) {
            Node newNode = createRule(rule);
            combinedNode = combineNodes(combinedNode, newNode);
        }

        return combinedNode;
    }

    public boolean evaluateRule(Node ast, Map<String, Object> data) {
        if (ast == null) return false;

        switch (ast.getType()) {
            case "operand":
                return evaluateOperand(ast, data);
            case "operator":
                return evaluateOperator(ast, data);
            default:
                throw new IllegalArgumentException("Unknown node type");
        }
    }

    private boolean evaluateOperand(Node node, Map<String, Object> data) {
        String[] parts = node.getValue().toString().split(" ");
        
        if (parts.length != 3) throw new IllegalArgumentException("Invalid operand format");

        String attribute = parts[0];
        String operator = parts[1];
        String comparisonValue = parts[2].replaceAll("'", "");

        Object attributeValue = data.get(attribute);

        if (attributeValue == null) throw new IllegalArgumentException("Attribute not found in data");

        if (attributeValue instanceof String) {
            return evaluateStringComparison((String) attributeValue, operator, comparisonValue);
        } else if (attributeValue instanceof Number) {
            return evaluateNumericComparison(((Number) attributeValue).intValue(), operator, Integer.parseInt(comparisonValue));
        } else {
            throw new IllegalArgumentException("Unsupported attribute type");
        }
    }

    private boolean evaluateStringComparison(String attributeValue, String operator, String comparisonValue) {
        switch (operator) {
            case "=":
                return attributeValue.equals(comparisonValue);
            default:
                throw new IllegalArgumentException("Unknown operator for string comparison");
        }
    }

    private boolean evaluateNumericComparison(int attributeValue, String operator, int comparisonValue) {
        switch (operator) {
            case ">":
                return attributeValue > comparisonValue;
            case "<":
                return attributeValue < comparisonValue;
            case "=":
                return attributeValue == comparisonValue;
            default:
                throw new IllegalArgumentException("Unknown operator for numeric comparison");
        }
    }

    private boolean evaluateOperator(Node node, Map<String, Object> data) {
        boolean leftResult = evaluateRule(node.getLeft(), data);
        boolean rightResult = evaluateRule(node.getRight(), data);

        switch (node.getValue().toString()) {
            case "AND":
                return leftResult && rightResult;
            case "OR":
                return leftResult || rightResult;
            default:
                throw new IllegalArgumentException("Unknown operator: " + node.getValue());
        }
    }

    private Node parseRule(String ruleString) {
        ruleString = ruleString.replace("(", " ( ").replace(")", " ) ")
                               .replace(" AND ", " AND ").replace(" OR ", " OR ");
        
        String[] tokens = ruleString.split("\\s+");
        
        Stack<Node> stack = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    String operator = operators.pop();
                    Node rightNode = stack.pop();
                    Node leftNode = stack.pop();
                    stack.push(new Node("operator", leftNode, rightNode, operator));
                }
                operators.pop(); // Remove the '('
            } else if (token.equals("AND") || token.equals("OR")) {
                operators.push(token);
            } else { 
                stack.push(new Node("operand", null, null, token));
            }
        }

        while (!operators.isEmpty()) {
            String operator = operators.pop();
            Node rightNode = stack.pop();
            Node leftNode = stack.pop();
            stack.push(new Node("operator", leftNode, rightNode, operator));
        }

        return stack.pop(); // The final node is the root of the AST
    }

    private Node combineNodes(Node node1, Node node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;

        // Create a new AND node to combine both trees
        return new Node("operator", node1, node2, "AND");
    }
}