package com.ruleengine.ruleengineast.model;


// A model class declared where all the parameters are instantiated to create a node for the AST (Abstract Syntax Tree) structure.
public class Node {
    private String type; // "operator" or "operand"
    private Node left; // reference to left child node
    private Node right; // reference to right child node (for operators)
    private Object value; // optional value for operand nodes

    public Node(String type, Node left, Node right, Object value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

