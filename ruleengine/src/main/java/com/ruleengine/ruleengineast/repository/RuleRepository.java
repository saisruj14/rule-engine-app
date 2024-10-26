package com.ruleengine.ruleengineast.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruleengine.ruleengineast.model.Rule;

// A repository interface to store the rules
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
