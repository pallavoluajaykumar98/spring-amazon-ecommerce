pipeline {
    agent any
	options{
		skipDefaultCheckout(false)
	}
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
             dir('.') {   // current workspace
               sh 'pwd'
               sh 'ls'
               sh 'mvn clean package -DskipTests'
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
                   withDockerRegistry(credentialsId: '177155e7-d9cb-4b02-8992-179789356cc0', toolName: 'docker') 
                   {
                sh "docker build -t amazon-ecommerce:latest -f Dockerfile ."
                sh "docker tag amazon-ecommerce:latest  ajkumar98/amazon-ecommerce:latest"
                sh "docker push ajkumar98/amazon-ecommerce:latest"

				   }
                }
           }
	    }
        stage('Deploy to Kubernetes') {
            steps {
				withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]){
                sh 'kubectl apply -f k8s/deployment.yml'
                sh 'kubectl apply -f k8s/service.yml'
                sh 'kubectl rollout restart deployment amazon-deployment -n amazon-ecommerce'


                }
           }
        }
	}	
    post {
        success {
            echo "Build succeeded"
        }
        failure {
            echo "Build Failed"
        }
    }
}
