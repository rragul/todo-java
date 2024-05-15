pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: '3edf2796-9b71-4d2e-9adc-695135675845', url: 'https://github.com/rragul/todo-java'
            }
        }
        stages {
        stage('Test Maven') {
            steps {
                sh 'mvn --version'
            }
        }
         stage('Build Project') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build('ragul05/todoapp:latest', '.')
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'e2123d0a-7c23-4141-9a17-caedcd1eb7cb') {
                        docker.image('ragul05/todoapp:latest').push('latest')
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }
    }
}
