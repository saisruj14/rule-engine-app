# Rule Engine Application

## Description

This application is a simple rule engine that allows users to create, combine, and evaluate rules based on various attributes such as age, department, salary, and experience.

## Features

- Create rules using a simple syntax. (Example input is given below)
- ```Example Rule to create -
  "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)"
  ```
- Combine multiple rules into one. (Example input is given below)
- ```Example of rule to combine with above -
  [
    "((age > 30 AND department = 'Sales'))",
    "((age < 25 AND department = 'Marketing'))"
  ]
  ```
- Evaluate rules against user-defined data. (Example input is given below)
- ```Example of the JSON input data to validate the rules created
  {
    "ast": {
        "type": "operator",
        "left": {
            "type": "operator",
            "left": {
                "type": "operator",
                "left": {
                    "type": "operand",
                    "value": "age > 30"
                },
                "right": {
                    "type": "operand",
                    "value": "department = 'Sales'"
                },
                "value": "AND"
            },
            "right": {
                "type": "operator",
                "left": {
                    "type": "operand",
                    "value": "age < 25"
                },
                "right": {
                    "type": "operand",
                    "value": "department = 'Marketing'"
                },
                "value": "AND"
            },
            "value": "OR"
        },
        "right": {
            "type": "operator",
            "left": {
                "type": "operand",
                "value": "salary > 50000"
            },
            "right": {
                "type": "operand",
                "value": "experience > 5"
            },
            "value": "OR"
        },
        "value": "AND"
    },
    "data": {
        "age": 35,
        "department": "Sales",
        "salary": 60000,
        "experience": 3
    }
  }
  ```

## Technologies Used

- Java Spring Boot for the backend.
- HTML, CSS, and JavaScript for the frontend.
- GitHub for version control.

## Installation

1. Clone this repository:
    ```bash
    git clone https://github.com/yourusername/your-repo-name.git
    ```
2. Navigate into the project directory:
    ```bash
    cd your-repo-name
    ```
3. Run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```
4. Open `index.html` in your web browser to access the application.

## Usage

1. **Create Rule**: Input a rule string in the designated area and click "Create Rule".
2. **Combine Rules**: Enter multiple rules separated by new lines and click "Combine Rules".
3. **Evaluate Rule**: Provide JSON data for evaluation and click "Evaluate Rule".

## Testing

Make sure to test all endpoints using Postman or through the provided UI.
# Example of testing creation of a rule using Postman
![create](https://github.com/user-attachments/assets/4c24d6ec-0730-40ef-97b8-8362c7427659)
# Example of testing combination of multiple rules using Postman
![combine](https://github.com/user-attachments/assets/748b0114-724e-4382-8871-8ca630d0d64e)
# Example of testing evaluation of a JSON user input to validate rules using Postman
![evaluate](https://github.com/user-attachments/assets/ff719a79-18ad-4f02-8fcd-706a819030e7)


## This is the description of how the rule engine works. Please don't hesitate to test the application as required. For more clarification please drop a mail to debdutta.basu.2020@gmail.com

# Thank you
