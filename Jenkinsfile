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
				dir ('server') {
					sh "mvn -DskipTests clean install"
				}
            }
        }
        stage('Test') {
            steps {
				dir ('server') {
					sh "mvn test"
				}
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
				dir ('server') {
					sh "/home/mzyzynsk/jenkins/scripts/deploy_backend.sh"
				}
            }
        }
		stage('Deploy frontend') {
            when {
                branch 'development'
            }
            steps {
				dir ('angular') {
					sh "/home/mzyzynsk/jenkins/scripts/deploy_frontend.sh"
				}
            }
        }
    }
};
