pipeline {
    agent any

    environment {
        DIRECTORY_PATH = "Jenkins"
        TESTING_ENVIRONMENT = "Task6.1C"
        PRODUCTION_ENVIRONMENT = "EmmaGray"
    }

    stages {
        stage('Build') {
            steps {
                // Use Maven to build the code
                sh 'mvn clean package'
            }
        }

        stage('Unit and Integration Tests') {
            steps {
                // Run unit tests
                sh 'mvn test'
                
                // Run integration tests
                // This step depends on the specific tool you are using for integration tests
            }
            post {
                success {
                    // Send email notification on success
                    emailext body: "Unit and Integration Tests passed successfully.",
                             subject: "Unit and Integration Tests Success",
                             to: "emmagrayy@gmail.com"
                }
                failure {
                    // Send email notification on failure
                    emailext body: "Unit and Integration Tests failed. Please check the logs.",
                             subject: "Unit and Integration Tests Failure",
                             to: "emmagrayy@gmail.com"
                }
            }
        }

        stage('Code Analysis') {
            steps {
                echo "Checking the quality of the code"
                // Integrate code analysis tool (e.g., SonarQube)
                // This step depends on the specific tool you are using for code analysis
                // Placeholder: Implement code analysis tool integration here
            }
        }

        stage('Security Scan') {
            steps {
                echo "Performing security scan"
                // Perform security scan (e.g., using OWASP ZAP)
                // This step depends on the specific tool you are using for security scanning
                // Placeholder: Implement security scanning tool integration here
            }
            post {
                success {
                    // Send email notification on success
                    emailext body: "Security Scan passed successfully.",
                             subject: "Security Scan Success",
                             to: "emmagrayy@gmail.com"
                }
                failure {
                    // Send email notification on failure
                    emailext body: "Security Scan failed. Please check the logs.",
                             subject: "Security Scan Failure",
                             to: "emmagrayy@gmail.com"
                }
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo "Deploying the application to a testing environment specified by: ${TESTING_ENVIRONMENT}"
                // Deploy application to staging server (e.g., AWS EC2 instance)
                // This step depends on your deployment process (e.g., using AWS CLI)
                // Placeholder: Implement deployment to staging here
            }
        }

        stage('Integration Tests on Staging') {
            steps {
                echo "Running integration tests on staging"
                // Run integration tests on the staging environment
                // This step depends on the specific tool you are using for integration tests
                // Placeholder: Implement integration tests on staging here
            }
        }

        stage('Deploy to Production') {
            steps {
                echo "Deploying the code to the production environment: ${PRODUCTION_ENVIRONMENT}"
                // Deploy application to production server (e.g., AWS EC2 instance)
                // This step depends on your deployment process (e.g., using AWS CLI)
                // Placeholder: Implement deployment to production here
            }
        }
    }

    post {
        success {
            // Send email notification on success
            emailext body: "Pipeline executed successfully.",
                     subject: "Pipeline Success",
                     to: "emmagrayy@gmail.com"
        }
        failure {
            // Send email notification on failure
            emailext body: "Pipeline failed. Please check the logs.",
                     subject: "Pipeline Failure",
                     to: "emmagrayy@gmail.com"
        }
    }
}
