pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn -DskipTests clean install"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    cobertura coberturaReportFile: '**/coverage.xml'Ł
                }
            }
        }
        stage('Deliver') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
        stage('Deploy') {
            when {
                branch 'development'
            }
            steps {
                sh "/home/mzyzynsk/jenkins/scripts/deploy_master.sh"
            }
        }
    }
};
