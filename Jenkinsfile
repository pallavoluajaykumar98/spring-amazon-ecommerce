pipeline {
    agent any
    tools {
        maven "M3"
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
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
