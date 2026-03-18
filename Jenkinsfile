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
				dir("${env.WORKSPACE}"){
				sh 'pwd'
				sh 'ls'
                sh 'mvn -B clean package -DskipTests'
				}	
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
                sh "docker build -t amazonmini:latest -f Dockerfile ."
                sh "docker tag amazonmini:latest  ajkumar98/amazonmini:latest"
				   }
                }
           }
	    }
                stage('Docker Deploy') {
            steps {
                script {
                   withDockerRegistry(credentialsId: 'cc13623d-3dd8-4f83-865f-cbde5e6fc529', toolName: 'docker') 
                   {
                sh "docker run -d  --name amazonmini-ecommerce -p 8070:8070 ajkumar98/amazonmini:latest"
				   }
                }
           }
	   }
    }
    post {
        always {
            cleanWs()
        }
        success {
            echo "Build succeeded"
        }
        failure {
            echo "Build Failed"
        }
    }
}
