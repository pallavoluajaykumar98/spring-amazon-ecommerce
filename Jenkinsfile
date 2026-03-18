pipeline {
    agent any
    tools {
        maven "Maven-3"
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
                   withDockerRegistry(credentialsId: 'cc13623d-3dd8-4f83-865f-cbde5e6fc529', toolName: 'docker') 
                   {
                sh "docker build -t Amazonmini:latest -f Dockerfile ."
                sh "docker tag Amazonmini:latest  ajkumar98/Amazonmini:latest"
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
