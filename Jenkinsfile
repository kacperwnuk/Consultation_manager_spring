pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Build backend') {
            steps {
				sh "cd server"
                sh "mvn -DskipTests clean install"
				sh "cd .."
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    cobertura coberturaReportFile: '**/coverage.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        stage('Deploy backend') {
            when {
                branch 'development'
            }
            steps {
                sh "/home/mzyzynsk/jenkins/scripts/deploy_backend.sh"
            }
        }
		stage('Deploy frontend') {
            when {
                branch 'development'
            }
            steps {
				sh "cd angular"
                sh "/home/mzyzynsk/jenkins/scripts/deploy_frontend.sh"
				sh "cd .."
            }
        }
    }
};
