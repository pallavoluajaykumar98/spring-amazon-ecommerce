pipeline {
    agent any
    tools {
        maven "Maven-3"
    }
    
    environment {
        IMAGE_NAME = credentials('docker-image-name') // or set a literal like 'amazon-mini-app'
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
                script {
                    if (!env.IMAGE_NAME?.trim()) {
                        error "IMAGE_NAME is not set. Configure a pipeline parameter, environment variable, or credentials."
                    }
                }
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
