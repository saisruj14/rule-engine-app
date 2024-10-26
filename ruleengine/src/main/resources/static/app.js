// JavaScript functionality for interacting with the Rule Engine API

// Function to create a rule
function createRule() {
    const ruleInput = document.getElementById('createRuleInput').value;
    
    fetch('http://localhost:8000/api/rules/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(ruleInput),
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('createRuleResult').innerText = JSON.stringify(data, null, 2);
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('createRuleResult').innerText = 'Error creating rule.';
    });
}

// Function to combine rules
function combineRules() {
    const rulesInput = document.getElementById('combineRulesInput').value.split('\n');
    
    fetch('http://localhost:8000/api/rules/combine', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(rulesInput),
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('combineRulesResult').innerText = JSON.stringify(data, null, 2);
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('combineRulesResult').innerText = 'Error combining rules.';
    });
}

// Function to evaluate a rule
function evaluateRule() {
    const evaluateInput = document.getElementById('evaluateRuleInput').value;

    fetch('http://localhost:8000/api/rules/evaluate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: evaluateInput,
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('evaluateRuleResult').innerText = JSON.stringify(data, null, 2);
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('evaluateRuleResult').innerText = 'Error evaluating rule.';
    });
}