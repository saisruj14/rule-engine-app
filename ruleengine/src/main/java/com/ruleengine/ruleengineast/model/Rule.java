package com.ruleengine.ruleengineast.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// A entity class that is declared to initiate rule creation and modification
@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleString;

    public Rule() {}

    public Rule(String ruleString) {
        this.ruleString = ruleString;
    }
    
    // Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuleString() {
		return ruleString;
	}

	public void setRuleString(String ruleString) {
		this.ruleString = ruleString;
	}

    
    
    
}