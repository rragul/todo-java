pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout source code from Git
                git credentialsId: '3edf2796-9b71-4d2e-9adc-695135675845', url: 'https://github.com/rragul/todo-java'
            }
        }
         stage('Build Project') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                // Build Docker image
                script {
                    docker.build('ragul05/todoapp:latest', '.')
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                // Push Docker image to Docker Hub
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'e2123d0a-7c23-4141-9a17-caedcd1eb7cb') {
                        docker.image('ragul05/todoapp:latest').push('latest')
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                // Deploy the updated application using Docker Compose
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }
    }
}
