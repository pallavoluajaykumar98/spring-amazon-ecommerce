pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'docker run --rm -v $PWD:/app -v $HOME/.m2:/root/.m2 -w /app maven:3.9.4-eclipse-temurin-17 mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'docker run --rm -v $PWD:/app -v $HOME/.m2:/root/.m2 -w /app maven:3.9.4-eclipse-temurin-17 mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${env.BUILD_NUMBER} ."
                sh "docker tag ${IMAGE_NAME}:${env.BUILD_NUMBER} ${IMAGE_NAME}:latest"
            }
        }
    }
    post {
        always {
            cleanWs()
        }
        success {
            echo "Build succeeded: ${IMAGE_NAME}:${env.BUILD_NUMBER}"
        }
        failure {
            echo "Build Failed"
        }
    }
}
